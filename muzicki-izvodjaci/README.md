# 🎶 Muzicki Izvodjaci - Java konzolna aplikacija

Ova aplikacija omogućava upravljanje muzičkim izvođačima i njihovim pjesmama putem jednostavnog interaktivnog menija u terminalu. Razvijena je u Javi i koristi PostgreSQL bazu podataka uz EclipseLink JPA za rad sa podacima.

## 📦 Funkcionalnosti

- Dodavanje, ažuriranje i brisanje izvođača
- Dodavanje, prikazivanje, brisanje i puštanje pjesama
- Prikaz svih izvođača po ID-u
- Prikaz svih pjesama određenog izvođača
- Zaustavljanje trenutno puštene pjesme
- Povezanost pjesme sa izvođačem
- Docker podrška za pokretanje aplikacije i baze

## 🧑‍💻 Pokretanje lokalno (bez Dockera)

1. Instaliraj JDK 11+ i Maven
2. Pokreni sledeće komande:
   ```bash
   mvn clean package
   java -jar target/muzicki-izvodjaci-1.0-SNAPSHOT.jar
   ```

> Napomena: za puštanje pjesama koristi se `JLayer`, i `.mp3` fajlovi se očekuju u folderu `Songs/`.

## 🐳 Pokretanje sa Dockerom

### 1. Izgradi i pokreni aplikaciju

```bash
docker-compose up --build
```

### 2. Pokreni aplikaciju unutar kontejnera

```bash
docker run -it --network=muzicki-izvodjaci_default muzicki-app
```

> Napomena: Docker ne podržava puštanje zvuka unutar kontejnera, ali sve ostale funkcionalnosti rade.

## 🗃 Struktura baze

- **Tabela `izvodjac`**: sadrži informacije o izvođačima
- **Tabela `song`**: povezana sa izvođačem, sadrži naziv i putanju do pjesme

## 📂 Folder sa pjesmama

Pjesme treba da se nalaze u folderu `Songs/`. Ako koristiš Docker, možeš dodati u `docker-compose.yml`:

```yaml
volumes:
  - ./Songs:/app/Songs
```

## 📝 Tehnologije

- Java 17
- Maven
- PostgreSQL
- JPA (EclipseLink)
- JLayer (za audio)
- Docker + Docker Compose


