package de.angelasensio.employeesvc.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import de.angelasensio.employeesvc.event.EmployeeEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageProducer {

    private final KafkaTemplate<String, ?> employeeKafkaTemplate;

    public void sendEvent(final EmployeeEvent event) {
        employeeKafkaTemplate.send(new GenericMessage<>(event));
    }
}
