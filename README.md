# NSW API

## Getting started:
Add those to your pom.xml
<br>
<br>
The repository :
```xml
<repositories>
    <repository> <!--The URL is temporary-->
        <id>NSW</id>
        <url>https://nexus.noskillworld.fr/repository/NSWAPI/</url>
    </repository>
    ...
</repositories>
```

The dependency :
```xml
<dependencies>
    <dependency>
        <groupId>fr.noskillworld.api</groupId>
        <artifactId>nswapi</artifactId>
        <version>0.2.1</version>
        <scope>compile</scope>
    </dependency>
    ...
</dependencies>
```

## Using the API:

To use the api, simply create a new instance just like so :
```java
String user = "User"; //The database user
String password = "Pass"; //The database password
String name = "Name"; //The database name

Credentials credentials = new Credentials(user, password, name); //Creates credentials
NSWAPI api = new NSWAPI(credentials); //Creates an instance of the API
```
