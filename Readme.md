#  Demo Spring Boot

Sample Spring Boot REST application which includes the following
spring based implementation.

## Swagger REST API Documentation
[Swagger API](https://app.swaggerhub.com/apis/nboumaza/springboot/1.0.0#/)


## Requirements
- Maven
- JDK 8

## Build Tool
This project uses maven as the build and packaging tool

### Building the artifact and java doc generation
```sh
mvn install javadoc:javadoc 
```

## Running
To start the server simply type from the root directory
spring boot application uses http://localhost:9090

```sh
mvn spring-boot:run
```

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

