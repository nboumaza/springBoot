#  Demo Spring Boot

Sample springboot REST application 

## Swagger REST API Documentation
[Swagger API](https://app.swaggerhub.com/apis/nboumaza/springboot/1.0.0#/)


## Requirements
- Maven
- JDK 8

## Build Tool
This project uses maven as the build and packaging tool

### Building 
```sh
mvn install javadoc:javadoc 
```
### Java Doc

after completing the above command the generated java doc will be under
<projectRoot>/target/site/apidocs/


## Running
To start the server simply type from the root directory
spring boot application uses http://localhost:9090

```sh
mvn spring-boot:run
```

Alternatively if you have [Docker](https://www.docker.com/) installed you can start the application in 
a docker container which exposes port 9090

```sh
./start.sh
```
Note: will start a clean build and regenerate the java docs 
 

## Testing 

### Postman
A [Postman](https://www.getpostman.com/) collection is included under
<projectRoot>/src/tes/resources folder.


### cURL

Home controller command:
```sh
$ curl localhost:9090
```
Other commands

Each cURL command can be easily generated via the included Postman
collection 

1. locate the right label titled:  "Generate Code"
2. From the resulting pop up "Generate Code Snippet" window, locate on the
   left side a combo box and choose cURL option.

