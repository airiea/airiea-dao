package com.airiea.dao.orm;

import com.airiea.model.enums.Model;
import com.airiea.model.enums.ModelObject;
import com.airiea.model.enums.ModelOwner;
import com.airiea.model.enums.UpdateType;
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
@DynamoDBTable(tableName = "agent-configs")
public class AgentConfig {
    private static final Double DEFAULT_TEMPERATURE = 0.2;
    private static final Boolean DEFAULT_STREAM = false;
    private static final String NULL_KNOWLEDGE_CONTENT = "NULL";
    private static final Integer DEFAULT_MAX_TOKEN = 2048;

    @JsonProperty("agent_name")
    @DynamoDBHashKey(attributeName = "agent_name")
    String agentName;

    @JsonProperty("task_name")
    @DynamoDBAttribute(attributeName = "task_name")
    String taskName;

    @DynamoDBAttribute(attributeName = "ownership")
    String ownership;

    /**
     * Describe the background of the task executor
     */
    @DynamoDBAttribute(attributeName = "agent_role")
    @JsonProperty("agent_role")
    String agentRole;

    @DynamoDBAttribute(attributeName = "agent_goal")
    @JsonProperty("agent_goal")
    String agentGoal;

    @DynamoDBAttribute(attributeName = "ability_name")
    @JsonProperty("ability_name")
    String abilityName;

    @DynamoDBAttribute(attributeName = "requirement")
    String requirement;

    @DynamoDBAttribute(attributeName = "prompt_format")
    String promptFormat;

    /**
     * Only used when initializing a new knowledge entry for a task entity
     */
    @Builder.Default
    @DynamoDBAttribute(attributeName = "background_knowledge")
    String backgroundKnowledge = NULL_KNOWLEDGE_CONTENT;

    @Builder.Default
    @DynamoDBAttribute(attributeName = "temperature")
    Double temperature = DEFAULT_TEMPERATURE;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "model")
    Model model;

    @JsonProperty("update_type")
    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "update_type")
    UpdateType updateType;

    @JsonProperty("update_delimiter")
    @DynamoDBAttribute(attributeName = "update_delimiter")
    String updateDelimiter;

    @JsonProperty("owned_by")
    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "owned_by")
    ModelOwner ownedBy;

    @JsonProperty("object")
    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "model_object")
    ModelObject modelObject;

    @Builder.Default
    @JsonProperty("max_tokens")
    @DynamoDBAttribute(attributeName = "max_tokens")
    Integer maxTokens = DEFAULT_MAX_TOKEN;

    @JsonProperty("created_date")
    @DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.CREATE)
    @DynamoDBAttribute(attributeName = "created_date")
    Date createdDate;
}
