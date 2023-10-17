package com.airiea.dao.publisher;

import com.airiea.model.event.CreateTaskPlanEvent;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateTaskPlanEventPublisher {
    private final AmazonSNS snsClient;
    private final ObjectMapper mapper;
    private final String snsTopicArn;

    public CreateTaskPlanEventPublisher(AmazonSNS snsClient, String snsTopicArn) {
        this.snsClient = snsClient;
        this.snsTopicArn = snsTopicArn;
        this.mapper = new ObjectMapper();
    }

    public void sendTaskPlanEventToSnsTopic(CreateTaskPlanEvent event) {
        try {
            String message = mapper.writeValueAsString(event);
            PublishRequest request = new PublishRequest()
                    .withTopicArn(snsTopicArn)
                    .withMessage(message);
            snsClient.publish(request);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to write create task plan event as string.");
        } catch (Exception e) {
            throw new IllegalStateException("Failed to send message: " + event +" to create task plan SNS Topic.");
        }
    }

}
