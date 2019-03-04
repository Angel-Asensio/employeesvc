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
  
A topic 'employee' should be created on Kafka.

So a kafka instance should work locally on port 9092. Of course, that is completely configurable.

How to execute (both services):
	mvn spring-boot:run

Please, start first the employeesvc, as this is the service where the H2 database is defined.

## Extra dependencies
I have used lombok for some boilerplate code. 
ModelMapper for Entity-Dtos Mapping

## Extra notes
Unit tests have been not created, because the time to develop everything was too much for a "test". I would not deliver a real, production application without unit tests, but this is not a commercial application. The whole system has been tested locally and works as it is expected. There could be corner cases that have been not covered, but again I consider this to be only a test. 
