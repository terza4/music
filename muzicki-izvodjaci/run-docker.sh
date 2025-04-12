#!/bin/bash

echo "🛠 Gradim Java aplikaciju sa Maven-om..."
mvn clean package

echo "🐳 Pokrećem Docker kontejner u interaktivnom režimu..."
docker run -it --rm \
  --network=muzicki-izvodjaci_default \
  muzicki-app
