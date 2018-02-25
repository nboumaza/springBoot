#!/usr/bin/env bash

mvn clean install javadoc:javadoc && docker-compose up --build