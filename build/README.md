# README

Before you run any script, you may need to undertake certain changes.

## Using H2-Memory Database

You may want to use H2-Memory Database for demo purpose, since you will not need to setup any database to run this webapp.

You will need to undertake two modifications:

1. Replace MySql dependency with the one below in `pom.xml`:

e.g.,

    <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-jdbc-h2</artifactId>
    </dependency>

2. Replace the "datasource configuration" section in `microprofile-config.properties` completely with the section below:

e.g.,

    # ----------------------------------
    #   datasource configuration
    # ----------------------------------
    quarkus.datasource.url=jdbc:h2:mem:test
    quarkus.datasource.driver=org.h2.Driver
    quarkus.hibernate-orm.database.generation=create

Then you will be using H2 database without any extra configuration.
