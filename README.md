# public-contracts

## About project
This project is dedicated to extracting data about public contracts in Czech Republic.

On this [page](https://vestnikverejnychzakazek.cz/SearchProfile/Search?Status=Active&PageSize=50&Page=1) can be found urls to every valid submitter of public contracts.

Adding "/XMLdataVZ?od=DDMMYYY&do=DDMMYYY" will result in xml representation of submitter and his public contracts in selected period.
[Here](https://vestnikverejnychzakazek.cz/cs/PublishAForm/XMLInterfaceForISVZUS) is xsd schema for previous xml.

## Before you run this app, you need to make following steps:

1. You need to have postgres installed
2. create new database in postgres and run init.sql on it (instead of running init.sql, you can use command reload-db when starting app)
3. change values in public-contracts.properties to your values:
 * db.target.server=server
 * db.target.port=port
 * db.target.database=database name
 * db.target.user=database user
 * db.target.pass=user password

## How to run app:
You have to run the app with following arguments:
 * reload-db - drops all tables from database and creates schema (Use this only to init db or you can loose collected data)
 * reload-sources - deletes and reloads urls of submitters (ETA 20 minutes)
 * delete-collected-data [yyyy] - delete all collected data except sources with urls, [yyyy] is optional and is used to delete data only for that year
 * yyyy - e.g. '2015' - search and save data for all submitters for 2015
 * reload-errors yyyy - tries to collect data that failed before
 
