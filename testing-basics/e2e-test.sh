#!/usr/bin/env bash

mvn clean install

cd ./user-service/

mvn spring-boot:run -Dserver.port=8081 -Dspring.datasource.platform=e2e

cd ../account-service/
mvn spring-boot:run -Dserver.port=8080 -Dspring.datasource.platform=e2e