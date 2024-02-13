# NSW API

## Introduction:

The NSWAPI is a database management & utility api for our Minecraft server. <br>
The API provides a database connector, a request sender & the HonorRanks handler

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
        <version>0.3.0</version>
        <scope>compile</scope>
    </dependency>
    ...
</dependencies>
```

## Using the API:

To use the api, simply call the create() method just like so:
```java
String user = "User"; //The database user
String password = "Pass"; //The database password
String name = "Name"; //The database name

Credentials credentials = new Credentials(user, password, name); //Creates credentials
NSWAPI api = NSWAPI.create(credentials); //Creates an instance of the API
```
