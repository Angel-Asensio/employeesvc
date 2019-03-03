## How to run
employeesvc runs on port 8080 and eventsvc on port 8090

Swagger for employee controller is on
http://localhost:8080/swagger-ui.html

Swagger for event controller is on
http://localhost:8090/swagger-ui.html

I have configured h2 so that both services use the same in-memory database. 

The web console for h2 is available on
http://localhost:8080/console

The application.yml assumes the following kafka configuration:

kafka:
  brokerAddress: localhost:9092
  topic: employee

So a kafka instance should work locally on port 9092. Of course, that is completely configurable.

How to execute for both services:
	mvn spring-boot:run

## Extra dependencies
I have used lombok for some boilerplate code. 
ModelMapper for Entity-Dtos Mapping
