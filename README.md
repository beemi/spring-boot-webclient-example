# Spring Boot WebClient Example 🚀

This is a simple Spring Boot service that utilizes WebClient to call external APIs and return the responses.

## 📋 Requirements

- ☕ Java 17
- 🏗️ Maven
- 🐘 Spring Boot
- 📬 Postman

### Run the Application Locally

```shell
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

The application will be available at [http://localhost:8090](http://localhost:8090).

### 📦 Create Jar File

```shell
./mvnw clean package -DskipTests
```
```Bash
./mvnw clean install -Dspring-boot.run.profiles=local
```

_Note: If you want to set custom version, use `-Drevision=1.0.0`_
Example:
```shell
./mvnw --batch-mode versions:set -DskipTests \
  -DnewVersion=1.0.0 \
  -DprocessAllModules \
  -DgenerateBackupPoms=false
```

Run the jar file:

```shell
java -jar target/webclients-spring-boot-example-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```


## :book: WebClient Configuration

This configuration class (`WebClientConfig`) sets up instances of WebClient to interact with external APIs. It provides methods to create WebClient instances for different services, such as Star Wars API and Postcode.io API.

### Configuration Properties

- **starwars.api.base-url**: The base URL of the Star Wars API.
- **postcodeIo.api.url**: The base URL of the Postcode.io API.

### WebClient Instances

#### postcodeIoWebClient

This bean creates a WebClient instance configured to interact with the Postcode.io API. It includes:

- **Exchange Strategies**: Configured to handle large payloads efficiently.
- **Default Headers**: Sets the content type to JSON.
- **Logging Filters**: Log outgoing requests and incoming responses for debugging purposes.

#### starWarsWebClient

This bean creates a WebClient instance configured to interact with the Star Wars API. It includes:

- **Exchange Strategies**: Configured to handle large payloads efficiently.
- **Default Headers**: Sets the content type to JSON.
- **Logging Filters**: Log outgoing requests and incoming responses for debugging purposes.

### Logging

The logging filters in this configuration class log the details of outgoing requests and incoming responses at INFO level, providing insights into the interactions with external APIs. This aids in troubleshooting and monitoring API calls.

### Usage

To use these WebClient instances in your application, simply inject them as dependencies where needed. For example:

```java
@Autowired
@Qualifier("starWarsWebClient")
private WebClient starWarsWebClient;

@Autowired
@Qualifier("postcodeIoWebClient")
private WebClient postcodeIoWebClient;
```

Then, you can use these WebClient instances to make requests to the respective APIs in your application logic.

### 📖 Swagger API Documentation

🔍 Access the OpenAPI documentation to explore and test the API endpoints:

- Swagger UI: [http://localhost:8090/swagger-ui/index.html](http://localhost:8090/swagger-ui/index.html)

### Actuator Info Endpoint :thumbsup:

Actuator health endpoint:

- [http://localhost:8090/actuator/health](http://localhost:8090/actuator/health)
