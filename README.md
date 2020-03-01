# Global Collateral and Margin Management
This is a java web application is for Global Collateral and Margin Management. 

## About the Application
* User will give PurchaseInformation which includes Price, Quantity, PurchaseType(Buy|Sell). Based on this, system will perform certain business calculations as per business requirement.
* Application produces calculation resulted output as a response for the REST endpoint.
* Application also performs to store the given PurchaseInformation to it's in-memory data store.

## Install & Running
 
### Prerequisites
* [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)  - Programming language
* [Maven 3.6.1](https://maven.apache.org/download.cgi) - Dependency Management | Build tool
* [Spring boot 1.5.19](https://projects.spring.io/spring-boot/) - Backed Framework
* [Lombok 1.16.22](https://projectlombok.org/) - Annotation Processor

### Build & run 

* Build the application
```
$ mvn clean install
```

* Run test
```
$ mvn test
```

* Run the web server on dev mode
```
$ mvn spring-boot:run
```

### API documentation
After running the project on dev environment and browse
[http://localhost:8083/swagger-ui.html](http://localhost:8083/swagger-ui.html)

REST Endpoints Execution Steps
* POST your PurchaseOrderInfo using "/gcmm/purchase-order"
* GET your Volume Weighted Oil Price using "/purchase-order/volume-weighted-oil-price"
* GET your Inventory Index using "/gcmm/inventory-index"

## Authors

* **Karthikeyan Nithiyanandam**

## License

This project is licensed under the MIT License