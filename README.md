# public-contracts

## About project
This project is dedicated to extracting data about public contracts in Czech Republic.

On this [page](https://vestnikverejnychzakazek.cz/SearchProfile/Search?Status=Active&PageSize=50&Page=1) can be found urls to every valid submitter of public contracts.

Adding "/XMLdataVZ?od=DDMMYYY&do=DDMMYYY" will result in xml representation of submitter and his public contracts in selected period.
[Here](https://vestnikverejnychzakazek.cz/cs/PublishAForm/XMLInterfaceForISVZUS) is xsd schema for previous xml.

## Before you run this app, you need to make following steps:

1. You need to have postgres installed
2. create new database in postgres and run init.sql on it
3. Change values in public-contracts.properties to the values of your equired database:
 * db.target.server=server
 * db.target.port=port
 * db.target.database=database name
 * db.target.user=database user
 * db.target.pass=user password
4. You can set the value public-contract.thread.number in public-contract.properties to whatever you want. It means in how many threads will the app devide list of submitters (there is around 14 000 of them). One thread takes 8 hours to load one year, 1000 threads take about 20 - 30 minutes. Maybe there is a better number, feel free to experiment

## How to run app:
You have to run the app with following arguments:
 * reload-db - drops all tables from database and creates schema (Use this only to init db or you can loose collected data)
 * reload-sources - deletes and reloads urls of submitters (ETA 20 minutes)
 * delete-collected-data [yyyy] - delete all collected data except sources with urls, [yyyy] is optional and is used to delete data only for that year
 * reload-errors yyyy - tries to collect data that failed before
 * yyyy [ico] - e.g. '2015' or '2015 28119169' - search and save data for all submitters for 2015, [ico] is optional and is used if previous attempt fail, so you can start after last saved submitter (ETA 8 hours)
 

## HTTPS problems
I have run to several problems with https and java.

First of them "Prime size must be multiple of 64, and can only range from 512 to 2048"
From my understanding the problem is, that the web page is using public key bigger than 2048 bits and thats not something that default java security setting tollerates.
I found only one solution to this problem [here](http://stackoverflow.com/questions/6851461/java-why-does-ssl-handshake-give-could-not-generate-dh-keypair-exception)

1. Download these jars:
 * bcprov-jdk15on-152.jar
 * bcprov-ext-jdk15on-152.jar
2. move these jars to $JAVA_HOME/lib/ext
3. edit $JAVA_HOME/lib/security/java.security as follows: security.provider.1=org.bouncycastle.jce.provider.BouncyCastleProvider
restart app using JRE and give it a try
(note, if you are running app in ide, you are probably using jre inside of your jdk - I had no idea jdk has its own jre)

Another problem was something with intermidiate/chain certificate [as explained here](https://www.sslshopper.com/ssl-checker.html#hostname=https://veza.cz/Contracts.aspx/1087/XMLdataVZ?od=01012015&do=01012016)

As I understand it, there is one or more invalid certificates in certificate chain, so java security doesn't acces the pages, because they are not trusted.
I solved this problem by setting this app to trust all certificates (check this file: CustomHttpClientFactory). However, it is NOT recommended solution. Better way is to add desired sources to your trusted certificates, but there are around 15 000 sources here, so I felt I didn't have any other choice.

These were the exceptions:
java.security.cert.CertPathBuilderException: No issuer certificate for certificate in certification path found.
java.security.cert.CertPathBuilderException: Unable to find certificate chain.


