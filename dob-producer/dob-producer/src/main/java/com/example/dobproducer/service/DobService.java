package com.example.dobproducer.service;

import com.example.dobproducer.enitity.EmployeeEntity;
import com.example.dobproducer.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Log4j2
public class DobService {

    private final EmployeeRepository employeeRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public DobService(EmployeeRepository employeeRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.employeeRepository = employeeRepository;

        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${spring.topic.name.producer}")
    private String topicName;

    @Scheduled(cron = "2 * * * * *")
    public void send() {
        LocalDate localDate = LocalDate.now();
        for (EmployeeEntity employeeEntity : employeeRepository.findAll()) {
            LocalDate date = LocalDate.parse(employeeEntity.getDob());
            if (date.getDayOfMonth() == localDate.getDayOfMonth() && date.getMonth() == localDate.getMonth()) {
                kafkaTemplate.send(topicName, employeeEntity.getEmail());
                log.info(employeeEntity);
            }


        }
    }
}
