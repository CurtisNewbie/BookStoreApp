# README

This script builds a bundled version for demo that includes:

1. Angular Frontend (for clients)
2. Angular Admin
3. Quarkus Backend
4. H2 In-Memory Database

The H2-Memory Database is good for demo purpose, since you will not need to setup any database to run this webapp. You can switch to another database by changing the datasource and pom dependency if you want to, no need to change this script for this.

## Use Guide

To access Angular Frontend (for clients):

    http://localhost:8080/frontend/index.html

To access Angular Admin:

    http://localhost:8080/admin/index.html

To access Quarkus Backend, you are basically sending REST requests, the OpenAPI documentation of REST endpoints is available at:

    http://localhost:8080/swagger-ui
