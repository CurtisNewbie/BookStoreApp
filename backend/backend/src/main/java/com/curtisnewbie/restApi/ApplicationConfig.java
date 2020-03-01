package com.curtisnewbie.restApi;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

/** Root path of RESTful api */
@Server(description = "BookStore Backend Server", url = "http://localhost:8080/")
@LoginConfig(authMethod = "MP-JWT", realmName = "bookstore")
@ApplicationPath("/api")
public class ApplicationConfig extends Application {
}