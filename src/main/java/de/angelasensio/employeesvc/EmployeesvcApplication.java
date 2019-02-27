package de.angelasensio.employeesvc;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class EmployeesvcApplication {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(EmployeesvcApplication.class, args);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9091");
    }

//    @PostConstruct
//    private void initDb() {
//        String sqlStatements[] = {
//                "drop table hobby if exists",
//                "create table hobby(id serial, name varchar(255))"
//                ,
//                "insert into hobby(name) values('soccer')",
//                "insert into hobby(name) values('music')"
//        };
//
//        Arrays.asList(sqlStatements).stream().forEach(sql -> {
//            System.out.println(sql);
//            jdbcTemplate.execute(sql);
//        });
//    }

}
