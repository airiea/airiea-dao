package com.airiea.dao.orm;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "task-entities")
public class TaskEntity {
    @JsonProperty("entity_id")
    @DynamoDBHashKey(attributeName = "entity_id")
    String entityId;

    @JsonProperty("agent_name")
    @DynamoDBRangeKey(attributeName = "agent_name")
    String agentName;

    @JsonProperty("task_count")
    @DynamoDBAttribute(attributeName = "task_count")
    Integer taskCount;

    @JsonProperty("created_date")
    @DynamoDBAttribute(attributeName = "created_date")
    @DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.CREATE)
    Date createdDate;

    @JsonProperty("updated_date")
    @DynamoDBAttribute(attributeName = "updated_date")
    @DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.ALWAYS)
    Date updatedDate;
}
