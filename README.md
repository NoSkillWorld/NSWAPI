# NSW API

## Introduction:

The NSWAPI is a database management & utility api for our Minecraft server.<br>
The API provides a database manager, the HonorRanks & rewards handlers, the report system & other utility stuff for our services.

## Getting started:

Add those to your pom.xml
#### The repository:
```xml
<repositories>
    <repository>
        <id>NSW</id>
        <url>https://nexus.noskillworld.fr/repository/NSWAPI/</url>
    </repository>
    ...
</repositories>
```

#### The dependency:
```xml
<dependencies>
    <dependency>
        <groupId>fr.noskillworld.api</groupId>
        <artifactId>nswapi</artifactId>
        <version>0.6.1</version>
        <scope>compile</scope>
    </dependency>
    ...
</dependencies>
```

## Using the API:

To use the api, simply call the create() method just like so:
```java
String user = "User"; //The user of the database
String password = "Pass"; //The password of the database
String name = "Name"; //The name of the database

Credentials credentials = new Credentials(user, password, name); //Creates credentials
NSWAPI api = NSWAPI.create(credentials); //Creates an instance of the API
```
