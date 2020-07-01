# XTM rent-a-car

XTM Java QA Staż - zadanie rekrutacyjne

## Instalacja

Do uruchomienia projektu wymagany jest [Docker](https://www.docker.com/get-started) z uruchomionym obrazem bazy danych [Postgres](https://hub.docker.com/_/postgres)

```bash
docker pull postgres:alpine
docker run --name postgres_db -e POSTGRES_PASSWORD=bezhasla1 -d -p 5432:5432 postgres:alpine
```
Po utworzeniu kontenera logujemy się do psql
```bash
docker exec -it postgres_db bash
psql -U postgres
```
Następnie należy dodać użytkownika oraz utworzyć bazę danych
```sql
CREATE USER drlf WITH PASSWORD 'bezhasla1';
\du
CREATE DATABASE xtm;
\l
```


## Użycie
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/f014309c9c59c62b269a)
