# Spring Boot Blog API

## Start a mysql db

Start a db with docker using command:
```
docker run --rm --name postgresdb -e POSTGRES_PASSWORD=password -d postgres
docker run --rm --name mysqldb -e MYSQL_ROOT_PASSWORD=password -d mysql
```

Connect the db with mysql cli
```
docker run -it --rm --link postgresdb:postgres postgres psql -h postgres -U postgres
docker run -it --link mysqldb:mysql --rm mysql sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'
```

## Create DB Table
```sql
CREATE DATABASE restapi;
USE restapi;
CREATE TABLE blog (
  id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(500) NOT NULL,
  content VARCHAR(5000) NOT NULL
);
```

## Remote debug 
Set IDEA remoete debug port to 8001