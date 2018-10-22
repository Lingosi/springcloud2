#!/bin/bash
java -server -Xms1024m -Xmx1024m -jar ./config-server/target/config-server-0.0.1-SNAPSHOT.jar &
sleep 50
java -server -Xms1024m -Xmx1024m -jar ./npms-basic/target/npms-basic-0.0.1-SNAPSHOT.jar --spring.profiles.active=test &
sleep 10
java -server -Xms1024m -Xmx1024m -jar ./npms-buss/target/npms-buss-0.0.1-SNAPSHOT.jar --spring.profiles.active=test &
sleep 10
java -server -Xms1024m -Xmx1024m -jar ./npms-trade/target/npms-trade-0.0.1-SNAPSHOT.jar --spring.profiles.active=test &
sleep 10
java -server -Xms1024m -Xmx1024m -jar ./npms-report/target/npms-report-0.0.1-SNAPSHOT.jar --spring.profiles.active=test &
