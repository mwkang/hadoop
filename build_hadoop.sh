#!/bin/bash
mvn -T 1.5C clean package -DskipTests -P os.mac,jdk1.8,tests-on