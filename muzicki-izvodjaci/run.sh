#!/bin/bash

echo "🛠️ Gradim aplikaciju..."
mvn clean package

echo "🚀 Pokrećem aplikaciju..."
java -jar target/muzicki-izvodjaci-1.0-SNAPSHOT-jar-with-dependencies.jar