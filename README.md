# Events reader system
###

[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)

> Spring REST API.

Overview
---
A scheduled system that takes an XML file in a resource folder and puts data into a database, by model.

Technology
---
The following are the key technologies used in the project:

- Spring Boot
- Java 8
- Apache Tomcat ( http server for the Spring boot app)
- Springfox
- Spring Data JPA
- H2 Database
- XML parser
- Spring Framework - Scheduler
- Build tool – maven

System Requirements
----

1. Rest API to present products by InsuredId as following:
2. Result must be in JSON format
3. Result should include all products related to same InsuredId grouped by SourceCompany
4. A scheduled process to read data each hour from folder


Running the Project
---

1. Compile the project with the following command:
```
mvn clean install
```
2. The project is a Spring Boot Application, so you can run inside of your ide or from terminal with the following command:
```
mvn spring-boot:run
```

H2 Database
---
If you prefer you can use any database client, else, you can access from the following URL:
1. Go to: http://localhost:8080/contacts/h2-console
2. Setting the following parameters:
```
 Driver Class :  org.h2.Driver
 JDBC URL     :  jdbc:h2:file:./data/reader_system_db
 User Name    :  sa
 Password     : 
```

API Endpoints
---
###Product
```

- /products
- Methods: **GET**
- Description: Get all products.

- /products
- Methods: **POST**
- Description: Add product to database.

- /products/{id}
- Methods: **GET**
- Description: Get product by id.

- /products/{id}
- Methods: **DELETE**
- Description: Delete product by id.

- /products/{id}
- Methods: **PUT**
- Description: Update product by id.
```
### Event
```
- /ProductEvent
- Methods: **GET**
- Description: Get all events.

- /ProductEvent
- Methods: **POST**
- Description: Add event to database.

- /ProductEvent/{id}
- Methods: **GET**
- Description: Get event by id.

- /ProductEvent/{id}
- Methods: **DELETE**
- Description: Delete event by id.

- /ProductEvent/{id}
- Methods: **PUT**
- Description: Update event by id.

- /ProductEvent/{eventId}/products/{productId}/add
- Methods: **POST**
- Description: Add product to event by event id and product id.

- /ProductEvent/{eventId}/products/{productId}/remove
- Methods: **DELETE**
- Description: Delete product from event by event id and product id.

```

### Scheduler
```
- /schedule/stop
- Methods: **GET**
- Description: Stop scheduler.

- /schedule/start
- Methods: **GET**
- Description: Start scheduler xml file.

- /schedule/deleteAll
- Methods: **GET**
- Description: Delete all scheduler workers.

- /schedule/list
- Methods: **GET**
- Description: List of scheduler workers.
```


### XML
```
- /xml-file/read
- Methods: **GET**
- Description: Start read XML file.

- /xml-file/fileExist
- Methods: **GET**
- Description: CHeck if file exist.

```

EventSchema
---
```
    event_id     (Integer) -> PRIMARY KEY
    event_type   (String)
    insured_id   (String)   
    products     List<Product> 
```

ProductSchema
---
```
    product_id       (Integer) -> PRIMARY KEY
    product_type     (String)
    price            (Double)
    startDate        (DATE)
    endDate          (DATE)
    product_event_id (INTEGER)
```

License
---
MIT [@RefaelBeker](https://github.com/RefaelBeker7)