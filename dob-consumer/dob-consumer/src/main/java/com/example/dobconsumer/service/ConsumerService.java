package com.example.dobconsumer.service;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.sendgrid.SendGrid;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import java.io.IOException;

@Service
@Log4j2
public class ConsumerService {
    @Value("${spring.kafka.topic.name}")
    private String topicName;
    @Value("${sendGrid.Key}")
    private String sendKey;
    @Autowired
    SendGrid sendGrid;
    @KafkaListener(topics = "${spring.kafka.topic.name}"
            , groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(ConsumerRecord<String, String> payload) {
        log.info("Topic: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partition: {}", payload.partition());
        log.info("Order: {}", payload.value());
        String value = payload.value();
        sendemail(value);
    }

    public Response sendemail(String  emailrequest)
    {

        Mail mail = new Mail(new Email("saurabhkanawade30@gmail.com"), "Hey Happy Birthday !", new Email(emailrequest),
                new Content(
                "text/plain", "Wish you many many Happy returns of the day !"));
        mail.setReplyTo(new Email("saurabhkandekar66@gmail.com"));
        Request request = new Request();
        Response response = null;
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response=this.sendGrid.api(request);
            log.info("success");
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

        return response;
    }
}

