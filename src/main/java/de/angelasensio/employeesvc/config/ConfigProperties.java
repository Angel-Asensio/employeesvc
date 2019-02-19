package de.angelasensio.employeesvc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "kafka")
@Getter
@Setter
public class ConfigProperties {

    private String brokerAddress;

    private String topic;

}