# TS - Triangle Challenge

## Prerequisite

This project requires:

- Java 8
    * [Download page](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
    * [Installation overview](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
        
- Maven 3.3
    * [Download page](https://maven.apache.org/download.cgi)
    * [Installation guide](https://maven.apache.org/install.html)

## Requirements

Write a program that will determine the type of a triangle. It should take the lengths of the triangle's three sides as input, and return whether the triangle is equilateral, isosceles or scalene.

We are looking for solutions that showcase problem solving skills and structural considerations that can be applied to larger and potentially more complex problem domains. Pay special attention to tests, readability of code and error cases.

The way you reflect upon your decisions is important to us, why we ask you to include a brief discussion of your design decisions and implementation choices. 

The resulting code and discussion is vital for us and will be used as a way for us to validate your engineering skills. After having reviewed your code, we’ll decide on next step.

## Installation guide

Clone or download the repository and run the following command within the root directory:

    > $ git clone https://github.com/ewessel/ts-triangle.git; cd ts-triangle
    > ts-triangle $ mvn clean install

## User guide

### RESTful service

Running the RESTful server from the root directory

    > ts-triangle $ java -jar rest/target/triangle-service-1.0.0-jar-with-dependencies.jar
    nov. 02, 2016 11:53:59 AM org.glassfish.grizzly.http.server.NetworkListener start
    INFOS: Started listener bound to [localhost:8080]
    nov. 02, 2016 11:53:59 AM org.glassfish.grizzly.http.server.HttpServer start
    INFOS: [HttpServer] Started.
    Jersey app started with WADL available at http://localhost:8080/application.wadl
    Hit enter to stop it...

Open your preferred browser and try [http://localhost:8080/triangle/operation/typeBySideLengths/5/5/5](http://localhost:8080/triangle/operation/typeBySideLengths/5/5/5).

## Design and implementation

The proposition consists in a project, whose build is managed by Maven, with two modules: core and rest.

### Core

This module contains the core entity - a triangle - and handles the business operation - returning the type of a triangle.

The business operation computing triangle type is handled by interface <code>TriangleOperation</code> through its implementation class <code>TriangleOperationImpl</code>. It has one operation <code>getTriangleTypeBySideLengths</code>, which takes the three side lengths and take care of the triangle instance creation before computing the type.

The triangle entity is represented by the class <code>Triangle</code>. A constructor takes the three side lengths into account and checks if they are positive integers. A triangle itself is able to return its compute type, from a sub-enum class <code>Type</code>.

Depending on the problem domain complexity, the business operations can be handled by one or more instances of <code>TriangleOperation</code>.

At this stage of the development, it is not interesting to add more modularity as it would add more complexity.

Unit tests (<code>TriangleOperationTest</code>) are performed at package build time, ensuring that there is no regression.

### Rest

This module contains a basic RESTful service handling the required business operation, by implementing class TriangleOperationService. It uses instances of TriangleFactory and TriangleOperation, initialized and retrieved through the Spring injection of dependency framework.

The requests with not well-formed URIs will end up with status "not found" (404). The requests with well-formed URIs (parameters representing integer) but any negative or zero value will end up with status "bad request" (400). The requests with well-formed URIs and all positive integer will end up with status "ok" (200) and entity value "SCALENE", "ISOSCELES" or "EQUILATERAL" depending on the type.

Class <code>Main</code> is launching a Grizzly server running the predefined triangle operation service. Class <code>AppResourceConfig</code> defines the way Jersey can find its resources and dependencies.

The server can be launched through two different ways:
* Running the jar as described in the User guide
* Running a Maven command under the rest module directory

    > ts-triangle $ cd rest
    > rest $ mvn exec:java

Unit tests (<code>TriangleOperationServiceTest</code>) are performed at package build time, ensuring that there is no regression.

## Next steps

I'll be happy to follow the discussion during the forthcoming interview.

Erik Wessel

erik DOT wessel AT gmail DOT com