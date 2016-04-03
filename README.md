# public-contracts

## About project
This project is dedicated to extracting data about public contracts in Czech Republic.

On this [page](https://vestnikverejnychzakazek.cz/cs/Searching/ShowPublicPublisherProfiles) can be found urls to every valid submitter of public contracts.

Adding "/XMLdataVZ?od=DDMMYYY&do=DDMMYYY" will result in xml representation of submitter and his public contracts in selected period.
[Here](https://vestnikverejnychzakazek.cz/cs/PublishAForm/XMLInterfaceForISVZUS) is xsd schema for previous xml.

## For users
####Before you run this app, you need to make following steps:

1. You need to have postgres installed
2. create new database in postgres and run init.sql on it
3. change values in public-contracts.properties to your values:
 * db.target.database=database name
 * db.target.user=database user
 * db.target.pass=user password

####How to run app:
Either you build your own jar using maven and this project or you get jar from someone else (you can always write me).

After you have your jar file, navigate to your file in command line and run:
* java -jar public-contracts.jar reload
 * to reload submitters from list of all submitters as mentioned above
* java -jar public-contracts.jar year
 * write actual year [2015] to get and save all data about public contracts for that year (note that the app runs for about 8 hours, so you probably want to do it before you go to sleep)

## For developers
The project is quite simple, it uses maven for building and dependencies and spring for dependency injection.
Classes for rest api are generated using JAXB.

Only services are:

1. DatabaseService
 * which is handling all communication with db
2. ISVZCrawlerService
 * which is getting all the adresses to access api of each submitter
3. ISVZService
 * which is getting data in xml and parsing them into java objects

##HTTPS problems
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

Another problem I am currently still trying to solve. It may have to do something with intermidiate/chain certificate [as explained here](https://www.sslshopper.com/ssl-checker.html#hostname=https://veza.cz/Contracts.aspx/1087/XMLdataVZ?od=01012015&do=01012016), but I can be wrong. Nevertheless I am getting:

java.security.cert.CertPathBuilderException: No issuer certificate for certificate in certification path found.

and

java.security.cert.CertPathBuilderException: Unable to find certificate chain.
on several pages


 


