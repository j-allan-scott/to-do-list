#!/bin/sh

# build
mvn clean install tomee:exec

# run in background and save pid
java -jar target/ROOT-exec.jar