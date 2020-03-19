# BookStoreApp

This is a simple Book Store webapp that consists of:

- a Quarkus backend (in <a href="https://github.com/CurtisNewbie/BookStoreApp/tree/master/backend/backend">./backend/backend</a>),
- an Angular 8 frontend for clients (in <a href="https://github.com/CurtisNewbie/BookStoreApp/tree/master/frontend/frontend">./frontend/frontend</a>), and
- an Angular 8 frontend for admins (in <a href="https://github.com/CurtisNewbie/BookStoreApp/tree/master/admin/admin">./admin/admin</a>).

### Prerequisite

- Java 11 (MUST)
- GraalVM (OPTIONAL, for native-build)
- Maven (OPTIONAL)
- NodeJS (OPTIONAL)
- JWT Authentication/Distribution Server (MUST, or else you will need to disable it)
  - The backend server uses JWT for role-based authorisation, you must have a JWT in hand to undertake certain operations. You will need to use the <a href="https://github.com/CurtisNewbie/JwtDistributionApp">JwtDistributionApp</a> that I have developed specifically for this backend server. In general, this webapp authenticates administrators using HTTP BASIC and returns a generated JWT for the role-based authorisation. If you only want to send requests to backend server, you can modify the public key in `microprofile-config.properties` and generate a JWT with your own private key.
  - The private/public keys of this BookstoreApp and JwtDistributionApp are matched intentionally, which works out-of-the-box for demonstration purpose. Once you deploy both repositories, the _admin frontend_ is able to retrieve the JWT from the JwtDistributionApp using the correct credentials.
  - If you are not familiar with Asymmetric Cryptography and Digital Signature, have a look at <a href="https://en.wikipedia.org/wiki/Public-key_cryptography">here on Wikipedia</a>.

## Running The DEMO

A bundled version is built for demonstration purpose, which includes the Angular frontend, Angular admin, Quarkus backend and H2 In-memory database. It is available in release. All you need to do is to execute following command to start the application:

    java -jar bundled-demo-1.0.1.jar

As the JWT authorisation mechanism is used in this BookstoreApp, you will need to either generate and provide your own JWT in HTTP Header (admin app won't work, as it needs the JwtDistributionApp),

    e.g.,

    curl -H "Authorization: Bearer asdfasvja;sldkjnbwoej;laksdnvoiahpoewfwer..." http://localhost:8080/api/order/all

or download my <a href="https://github.com/CurtisNewbie/JwtDistributionApp/releases/tag/V1.0.0">JwtDistributionApp</a> and run it as follows:

    java -jar jwttokendistrib-1.0.0-demo.jar

The OpenAPI documentation of backend REST endpoints is available at:

    http://localhost:8080/swagger-ui

The Angular frontend is accessible at:

    http://localhost:8080/frontend/index.html

The Angular admin is accessible at:

    http://localhost:8080/admin/index.html

When the JwtDistributionApp is up and running, you can login in Angular admin using the credential below:

    username: apple
    password: juice

## Custom Configuration

The demo, bundled version in release is configured in such a way that all functionalities are demonstrated without changing anything. You may want to customise it or explore it a little bit.

The following list is what you may change:

For the backend:

- In `microprofile-config.properties`:

  - Enable/disable JWT
  - JWT publickey and issuer
  - Datasource for database connection
  - Backend root path
  - CORS filter, allowed origins
  - Packaging fat-jar or thin-jar
  - Always show swagger-ui

- In `pom.xml`:
  - Dependency for jdbc driver (if you change to another DBMS)

## Deployment

### Script for Building Bundled Version

This is only recommended for convenience and demo purpose. This script is available at `./build/bundled_build.sh`:

### Running and Building Standalone Quarkus Backend

To run the standalone Quarkus application in development mode. Navigate to `./backend/backend` and execute the following command:

    mvn clean quarkus:dev

To build the jar package:

    mvn clean package

To build the native-image:

    mvn clean package -Pnative

### Running and Building Standalone Angular Apps

Running the angular app is very straightforward, no extra configuration is needed. Just use the commands below:

    ng serve

or (open the tab when it runs):

    ng serve --open

or (run on specific ip and host):

    ng serve --host 0.0.0.0 --port 4200

To build the Angular app and host it in your preferred server:

    ng build --prod

and then copy all the files in `/dist` into your server.

### Extra Info About JWT

- Private key is used by the authentication server to generate the token (e.g., <a href="https://github.com/CurtisNewbie/JwtDistributionApp">JwtDistributionApp</a>), i.e., for digital signature.
- Public key is used by the backend server to verify the integrity of the JWT, so the backend server does not contain the private key.

  - More about JWT:
    - http://www.mastertheboss.com/javaee/eclipse-microservices/using-jwt-role-based-access-control-with-wildfly
    - https://www.tomitribe.com/blog/microprofile-json-web-token-jwt/
    - https://www.eclipse.org/community/eclipse_newsletter/2017/september/article2.php
    
 ## Demo
 
 Note: <a href="https://github.com/CurtisNewbie/JwtDistributionApp/releases/tag/V1.0.0">JwtDistributionApp</a> is used in this demo.
 
 ### Angular Frontend for Clients
 
 <img src="https://user-images.githubusercontent.com/45169791/77087235-cb31cb00-69fa-11ea-9c54-2ec7336e3fe4.gif">
 
 ### Angular Frontend for Administrators
 
 <img src="https://user-images.githubusercontent.com/45169791/77087403-ff0cf080-69fa-11ea-88c0-096ee899dc67.gif">
