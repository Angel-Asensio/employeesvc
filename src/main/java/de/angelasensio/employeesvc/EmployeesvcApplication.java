package de.angelasensio.employeesvc;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class EmployeesvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeesvcApplication.class, args);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9091");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
