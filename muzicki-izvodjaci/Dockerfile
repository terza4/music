# Baza: Java 11
FROM openjdk:11

# Radni direktorijum u kontejneru
WORKDIR /app

# Kopiraj .jar fajl u kontejner
COPY target/muzicki-izvodjaci-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
COPY ./Songs /app/Songs

# Komanda koja se izvršava kad se kontejner pokrene
ENTRYPOINT ["java", "-jar", "app.jar"]