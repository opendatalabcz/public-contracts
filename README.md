# public-contracts

## About project
This project is dedicated to extracting data about public contracts in Czech Republic.

On this [page](https://vestnikverejnychzakazek.cz/cs/Searching/ShowPublicPublisherProfiles) can be found urls to every valid submitter of public contracts.

Adding "/XMLdataVZ?od=DDMMYYY&do=DDMMYYY" will result in xml representation of submitter and his public contracts in selected period.
[Here](https://vestnikverejnychzakazek.cz/cs/PublishAForm/XMLInterfaceForISVZUS) is xsd schema for previous xml.

## Before you run this app, you need to make following steps:

1. You need to have postgres installed
2. Get existing database from "you know who"
3. change values in public-contracts.properties to the values of your equired database:
 * db.target.server=server
 * db.target.port=port
 * db.target.database=database name
 * db.target.user=database user
 * db.target.pass=user password

## How to run app:

1) init

2) reload-sources

3) After that you load data with command yyyy (as explained below) and you can reload failed companies for given year multiple times

 * init - update existing db from other project to public contract (run only once)
 * reload-sources - deletes and reloads urls of submitters (ETA 20 minutes)
 * yyyy - e.g. '2015' - search and save data for all submitters for 2015
 * reload-errors yyyy - tries to collect data that failed for the year yyyy (e.g. 2015) before
 


