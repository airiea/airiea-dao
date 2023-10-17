package com.airiea.dao.publisher;

import com.airiea.model.event.InputTaskEvent;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InputTaskEventPublisher {
    private final AmazonSNS snsClient;
    private final ObjectMapper mapper;
    private final String snsTopicArn;

    public InputTaskEventPublisher(AmazonSNS snsClient, String snsTopicArn) {
        this.snsClient = snsClient;
        this.snsTopicArn = snsTopicArn;
        this.mapper = new ObjectMapper();
    }

    public void sendInputTaskEventToSnsTopic(InputTaskEvent event) {
        try {
            String message = mapper.writeValueAsString(event);
            PublishRequest request = new PublishRequest()
                    .withTopicArn(snsTopicArn)
                    .withMessage(message);
            snsClient.publish(request);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to write task input event as string.");
        } catch (Exception e) {
            throw new IllegalStateException("Failed to send message: " + event +" to task input event SNS Topic.");
        }
    }

}
