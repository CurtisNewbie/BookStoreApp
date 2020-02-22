# BookStoreApp

This is a simple Book Store webapp that consists of:

- a Jarkata 8 backend (in <a href="https://github.com/CurtisNewbie/BookStoreApp/tree/master/backend">./backend</a>),
- an Angular 8 frontend for clients (in <a href="https://github.com/CurtisNewbie/BookStoreApp/tree/master/frontend/frontend">./frontend</a>), and
- an Angular 8 frontend for admins (in <a href="https://github.com/CurtisNewbie/BookStoreApp/tree/master/admin/admin">./admin</a>).

### Dependencies

- **Backend**

  - <a href="https://jakarta.ee/release/">Jarkata 8 or JavaEE 8 API</a>
  - Microprofile API (<a href="https://github.com/eclipse/microprofile-jwt-auth">MP-JWT</a>, <a href="https://github.com/eclipse/microprofile-open-api">MP-OpenAPI</a> and )

Actual implementation or the server your are using shouldn't really matter, but they should implement the API that is used, I am currently using Wildfly19 (Wildfly18 doesn't support MP-JWT).

- **Frontend**

  - <a href="https://angular.io/">Angular 8</a>
  - <a href="https://getbootstrap.com/docs/4.0/getting-started/introduction/">Bootstrap 4</a>

- **(Optional) JWT Authentication/Distribution Server** (if you want to use the JWT authorisation mechanism)

  - I have developed a JWT distribution webapp specifically for this backend server. It is availble in my another repo (<a href="https://github.com/CurtisNewbie/JwtDistributionApp">JwtDistributionApp</a>). Generally speaking, this webapp authenticates administrators using HTTP BASIC and returns a generated JWT for the role-based authorisation.
  - The private/public keys of this BookstoreApp and JwtDistributionApp are matched intentionally, which works out-of-the-box for demonstration purpose. Once you deploy both repositories, the _admin frontend_ is able to retrieve the JWT from the JwtDistributionApp using the correct credentials.
  - If you are not familiar with Asymmetric Cryptography and Digital Signature, have a look at <a href="https://en.wikipedia.org/wiki/Public-key_cryptography">here on Wikipedia</a>.

## Running Backend Server

### Backend Configuration

There are a few things you should configure (as a minimum) before deployment:

- Data source (i.e., DBMS/ Database) in **`persistence.xml`**

  - Create the database in your preferred DBMS (tables are created when the backend is deployed).

  - Set the JNDI name of the datasource in **`<jta-data-source>changeToYourDSName</jta-data-source>`**

- JWT public key and issuer in **`microprofile-config.properties`** (if you are using JWT but not my <a href="https://github.com/CurtisNewbie/JwtDistributionApp">JwtDistributionApp</a>)

  - Private key is used by the authentication server to generate the token (e.g., <a href="https://github.com/CurtisNewbie/JwtDistributionApp">JwtDistributionApp</a>). The backend server only needs the public key to verify the signature on JWT.
  - You will need to set the public key in **`mp.jwt.verify.publickey=ChangeToYourPublicKey`**, or you can set the location to your **`.pem`** file (which only contains the public key) in **`mp.jwt.verify.publickey.location=/to/your/pem/file`**.
  - You will also need to set the **`issuer`** name, which is specified in your JWT (**`"iss"`** property), in my case, the issuer is _"bookstore"_.

    - More about JWT:
      - http://www.mastertheboss.com/javaee/eclipse-microservices/using-jwt-role-based-access-control-with-wildfly
      - https://www.tomitribe.com/blog/microprofile-json-web-token-jwt/
      - https://www.eclipse.org/community/eclipse_newsletter/2017/september/article2.php

* CORS's filter in **`com/curtisnewbie/restApi/CorsFilter.java`**
  - CORS is a security policy implemented by web browsers. Since the backend server and frontend servers are in different _"origin"_, you will need to explicitly specify which ip and host can view the response returned by the backend, or else the reponses will be blocked by the browser.
  - You simply set the ip in **`headers.add("Access-Control-Allow-Origin", "http://localhost:4200");`** to the ip of your frontend server, the _"http://localhost:4200"_ is the default in Angular.

### Backend Deployment

The `War` file can be easily packaged using cmd below.

    mvn clean package

Once the .war file is packaged, deploy it to a Java EE 8 compliant Servers (e.g., TomEE, Wildfly, OpenLiberty, etc). If you are using JWT, your server will also need to implement MP-JWT and MP-OpenAPI (which are part of the MicroProfile). You can remove the JWT annoations and Role-based security annotations if you don't want JWT (e.g., @RolesAllowed, @LoginConfig).

### Using The Backend Webapp

After the deployment, you can start using the server immediately. By default, the host name will be "localhost" , and port number is "8080". All REST endpoints have a root path "/api".

e.g.,

    http://localhost:8080/api/book/all

**_The generated OpenAPI documentation (.yaml and .html) for all the REST endpoints is available in <a href="https://github.com/CurtisNewbie/BookStoreApp/tree/master/backend/openapi">./backend/openapi</a>. The html version of it is generated using <a href="https://swagger.io/">Swagger Editor</a>_**

**_A file that contains some of the demo data and HTTP calls is available in <a href="https://github.com/CurtisNewbie/BookStoreApp/blob/master/backend/RestDemo.md">./backend/RestDemo.md</a>, with which you can use to create some demo data.(without removing the JWT authrization annotations, you will need to generate a JWT to POST, PUT or DELETE resources.)_**

**_A simple MySQL script file that specifies some of the basic command for creating database and user is also available in <a href="https://github.com/CurtisNewbie/BookStoreApp/blob/master/backend/db_script.sql">./backend/db_script.sql</a>._**

## Running Frontend App

Running the angular app is very straightforward, no extra configuration is needed. Just use the cmd below:

    ng serve

or (open the tab when it runs):

    ng serve --open

or (run on specific ip and host):

    ng serve --host 0.0.0.0 --port 4200

## Removing The Implmenetation of JWT

If you don't want to use JWT, you simply remove the following `annotations` in `com/curtisnewbie/restApi/`

- @RolesAllowed(SecurityRole.ADMIN)
- @LoginConfig(authMethod = "MP-JWT", realmName = "bookstore")

and set the following **`param-value`** in the **`web.xml`** to **`false`**

    <context-param>
        <param-name>resteasy.role.based.security</param-name>
        <param-value>true</param-value>
    </context-param>
