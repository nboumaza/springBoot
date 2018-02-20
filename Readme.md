# CRUD App with Spring Boot

Sample Spring Boot REST application using the following
[Swagger API](https://virtserver.swaggerhub.com/nboumaza/springboot/1.0.0)


### Requirements

- Maven
- JDK 8

### Running

To build and start the server simply type

```sh
$ mvn spring-boot:run
```

from the root directory.

### Using

You can see what urls are available using curl:

```sh
$ curl localhost:9090
```

You can view existing people objects using a similar request:

```sh
$ curl localhost:9090/users
```

and can create new ones using a POST:

```sh
$ curl -X POST -H "Content-Type:application/json" -d '{ "userId" : "user1", "email" : "user1@acme.com" }' localhost:9090/users
```