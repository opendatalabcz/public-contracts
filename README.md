# public-contracts

## About project
This project is dedicated to extracting data about public contracts in Czech Republic.

On this [page](https://vestnikverejnychzakazek.cz/SearchProfile/Search?Status=Active&PageSize=50&Page=1) can be found urls to every valid submitter of public contracts.

Adding "/XMLdataVZ?od=DDMMYYY&do=DDMMYYY" will result in xml representation of submitter and his public contracts in selected period.
[Here](http://www.isvz.cz/ISVZ/VZ/ProfilyZadavatelu_134_2016.aspx) is xsd schema for previous xml, which replaced [old](https://vestnikverejnychzakazek.cz/cs/PublishAForm/XMLInterfaceForISVZUS) schema in 2016.

## Before you run this app, you need to make following steps:

1. You need to have postgres installed
2. Database is dependent on _opendata_ project. This project extends _opendata_ database, so begin with initialization [here](https://github.com/opendatalabcz/opendata/blob/master/docs/install.md).
3. Continue _public-contracts_ initialization with [init.sql](https://github.com/opendatalabcz/public-contracts/blob/master/src/main/resources/sql/init.sql) and [data.sql](https://github.com/opendatalabcz/public-contracts/blob/master/src/main/resources/sql/data.sql).
4. Change values in public-contracts.properties to the values of your equired database:
 * db.target.server=server
 * db.target.port=port
 * db.target.database=database name
 * db.target.user=database user
 * db.target.pass=user password
5. You can set the value public-contract.thread.number in public-contract.properties to whatever you want. It means in how many threads will the app devide list of submitters (there is around 14 000 of them). One thread takes 8 hours to load one year, 1000 threads take about 20 - 30 minutes. Maybe there is a better number, feel free to experiment

## How to run app:
You have to run the app with following arguments:
 * init - runs the [init.sql](https://github.com/opendatalabcz/public-contracts/blob/master/src/main/resources/sql/init.sql) and [data.sql](https://github.com/opendatalabcz/public-contracts/blob/master/src/main/resources/sql/data.sql) scripts. Cannot be executed multiple times.
 * reload-sources - deletes and reloads urls of submitters (ETA 20 minutes). When the sources are loaded, use table _source.active_ to filter which ones are to be processed.
 * reload-errors yyyy [-skipFetching]- tries to collect data that failed before
 * fetch-ico ico [-skipFetching]- retrieves data for a single specific ICO number
 * mmyyyy mmyyyy [-skipFetching]- e.g. '012017 062017' - search and save data for active submitters for time interval from the first day of the first given month to the last day of the second given month (both sides included, eg. 1.1.2017-30.6.2017). Use table _parameter_ to filter the document types to be processed.
  * process-documents - processes (downloads and extracts) documents saved in the database to be processed by the _document.to_process_ flag
  
Optional parameters:
 * -skipFetching - suppresses the processing of document (downloading and extraction), so only the documents links are scrapped and saved to the database with the flag _document.to_process_


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


