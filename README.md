# Welcome to the WestMarches repository.

We Aim to create a dynamic platform to allow westmarches payers to easily manage events on a hexmap.  
More features to come!

## Kickstart to run the project locally:
### 1. Cloning
First you need to run: 
```sh
git clone https://github.com/FlorczakDavid/WestMarches.git
```

### 2. Configure the database
The DBMS we use is called [PostgreSQL](https://www.postgresql.org/)  
After installing it, You'll have to create the database to store the users data.  
First, Access the DBMS like so:
```sh
psql -U postgres
```
once inside the Postgres terminal, create the database like so:
```SQL
CREATE DATABASE westmarches;
```
then, you can connect to your newly created database like so:  
```SQL
\connect westmarches
```
Now, we execute the SQL files to create the tables in the database like so:  
```SQL
\i ./wm-data/Scripts/schema.ddl.sql
```
Finally, we fill the tables with the testing data with this command:  
```SQL
\i ./wm-data/Scripts/data.dml.sql
```

### 3. Configure the API

### 4. Configure the front
