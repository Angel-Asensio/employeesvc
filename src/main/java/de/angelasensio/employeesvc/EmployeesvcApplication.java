package de.angelasensio.employeesvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class EmployeesvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeesvcApplication.class, args);
    }

}
