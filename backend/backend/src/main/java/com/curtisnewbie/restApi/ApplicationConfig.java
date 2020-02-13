package com.curtisnewbie.restApi;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.auth.LoginConfig;

/** Root path of RESTful api */
@LoginConfig(authMethod = "MP-JWT", realmName = "bookstore")
@ApplicationPath("api")
public class ApplicationConfig extends Application {
}