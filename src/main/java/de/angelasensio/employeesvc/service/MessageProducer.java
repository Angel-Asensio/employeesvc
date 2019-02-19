package de.angelasensio.employeesvc.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import de.angelasensio.employeesvc.config.ConfigProperties;
import de.angelasensio.employeesvc.event.EmployeeEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageProducer {

    private final ConfigProperties configProperties;
    private final KafkaTemplate<String, EmployeeEvent> employeeKafkaTemplate;

    public void sendEvent(final EmployeeEvent event) {

        ListenableFuture<SendResult<String, EmployeeEvent>> future = employeeKafkaTemplate.send(configProperties.getTopic(), event);

        future.addCallback(new ListenableFutureCallback<SendResult<String, EmployeeEvent>>() {

            @Override
            public void onSuccess(SendResult<String, EmployeeEvent> result) {
                LOG.debug("Sent message=[ {} ] with offset=[ {} ]", event.toString(), result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable throwable) {
                LOG.warn("Unable to send message=[" + event.toString() + "] due to : " + throwable.getMessage());
            }

        });
    }
}
