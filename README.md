# BookStoreApp

Simple Book Store webapp that consists of a <a href="https://github.com/CurtisNewbie/BookStoreApp/tree/master/backend">Jarkata 8 backend</a> and an <a href="https://github.com/CurtisNewbie/BookStoreApp/tree/master/frontend/frontend">Angular 8 frontend</a>.

**Dependencies:**

- Backend

  - Jarkata 8 or JavaEE 8 API
  - Microprofile (MP-JWT for authorisation) API

Actual implementation or the server your are using shouldn't really matter, but they should implements the API that is used, I am currently using Wildfly19 (Wildfly18 doesn't support MP-JWT).

- Frontend

  - Angular 8 (or lower or higher, I am not really sure about this)
  - Bootstrap 4

# Running Backend Server

## Backend Configuration

There are a few things you should configure (as a minimum) before deployment:

- Data source (i.e., DBMS/ Database) in `persistence.xml`

  - Create the database and user before deployment.

  - Set the JNDI name of the datasource in `<jta-data-source>changeToYourDSName</jta-data-source>`

- JWT public key and issuer (if you are using it) in `microprofile-config.properties`

  - Private key is used to generate the token, which is not needed in this backend server, we only need the public key to varify the signature on JWT.
  - So, you will need to set the public key in `mp.jwt.verify.publickey=ChangeToYourPublicKey` or set the location to your `.pem` file (which only contains the public key) in `mp.jwt.verify.publickey.location=/to/your/pem/file`.
  - You will also need to set the `issuer` name, which is specified in your token specification (`"iss"` property), in my case, the issuer is "bookstore".

    - More about JWT:
      - http://www.mastertheboss.com/javaee/eclipse-microservices/using-jwt-role-based-access-control-with-wildfly
      - https://www.tomitribe.com/blog/microprofile-json-web-token-jwt/
      - https://www.eclipse.org/community/eclipse_newsletter/2017/september/article2.php

* CORS's filter in `com/curtisnewbie/restApi/CorsFilter.java`
  - CORS is a security policy implemented in browsers, since the backend server and frontend server are in different "origin", you will need to explicitly specify which ip and host can view the response returned by the backend, or else the reponses will be blocked by the browser.
  - You simply set the ip in `headers.add("Access-Control-Allow-Origin", "http://localhost:4200");` to the ip of your frontend server, the "http://localhost:4200" is the default in Angular.

## Backend Deployment

The `War` file can be easily packaged using cmd below.

    mvn clean package

Once the .war file is packaged, deploy it to a Java EE compliant Servers (e.g., TomEE, Wildfly, OpenLiberty, etc). If you are using JWT, your server will also need to implement MP-JWT (which is part of the MicroProfile), you can remove the JWT annoation and Role-based security annotation if you don't want JWT (e.g., @RolesAllowed, @LoginConfig).

## Using The Backend Webapp

After the deployment, you can start using the server immediately. By default, the host name will be "localhost" , and port number is "8080". All REST api has a root path "/api".

e.g.,

    http://localhost:8080/api/book/all

**_A file that contains some of the `demo data and HTTP calls` is located at `/backend/RestDemo.md`, with which you can use to create some demo data._**

**_A simple Mysql script file that specifies some of the basic command for creating database and user is also available at `backend/db_script.sql`._**

# Running Frontend App

Running the angular app is very straightforward, no extra configuration is needed. Just use the cmd below:

    ng serve

or (open the tab when it runs):

    ng serve --open

or (run on specific ip and host):

    ng serve --host 0.0.0.0 --port 4200

# Remove the implmenetation of JWT

If you don't want to use JWT, you simply remove the following `annotations` in `com/curtisnewbie/restApi/`

- @RolesAllowed(SecurityRole.ADMIN)
- @LoginConfig(authMethod = "MP-JWT", realmName = "bookstore")

and set the following `param-value` in the `web.xml` to `false`

    <context-param>
        <param-name>resteasy.role.based.security</param-name>
        <param-value>true</param-value>
    </context-param>
