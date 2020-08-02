# Trade App #

## Version ##

`0.0.1`

## Summary ##

This application stores trade details.

* [Prerequisites](#markdown-header-prerequisites)
* [Build](#markdown-header-build)
* [Test](#markdown-header-test)
* [Run](#markdown-header-run)
* [Author](#markdown-header-author)

## Prerequisites ##

Ensure local installation of following softwares/tools:

* JDK - 1.8
* Git (2.9.0 or higher) - only for cloning project from repository
* Apache Maven (3.3.9 or higher)


## Build

Open your command/shell terminal

Clone locally, an appropriate version of sample-test from [git](https://github.com/sammeraj1/db).

Execute standard maven command to build and install library:

~~~bash
# CD into project folder

# Build package(downloading dependencies)
mvn clean install
~~~
Verify that it generates below artifacts:

* ./target/db-test-*.jar

---

## Test

This api health endpoint can be tested using below url.

`http://localhost:8080/api/db/trade/actuator/health'


---

## Run

Local

```
# java -jar <JAR_NAME>.jar

java -jar db-test/target/db-test-0.0.1-SNAPSHOT.jar

```

## Author ##

Meraj Ahmad
