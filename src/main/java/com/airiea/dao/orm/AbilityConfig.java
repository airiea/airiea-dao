package com.airiea.dao.orm;

import com.airiea.model.enums.Model;
import com.airiea.model.enums.ModelObject;
import com.airiea.model.enums.UpdateType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "ability-configs")
public class AbilityConfig {
    @DynamoDBHashKey(attributeName = "ability_name")
    @JsonProperty("ability_name")
    String abilityName;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "model_object")
    @JsonProperty("model_object")
    ModelObject modelObject;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "model")
    @JsonProperty("model")
    Model model;

    @DynamoDBAttribute(attributeName = "ability_type")
    @JsonProperty("max_tokens")
    Integer maxTokens;

    @DynamoDBAttribute(attributeName = "temperature")
    Double temperature;

    //Your task is to devise up to 5 highly effective goals and an appropriate role-based name (_GPT) for an autonomous agent,
    //ensuring that the goals are optimally aligned with the successful completion of its assigned task.
    @DynamoDBAttribute(attributeName = "description")
    String description;

    //The user will provide the task, you will provide only the output in the exact format specified below with no explanation or conversation.
    @DynamoDBAttribute(attributeName = "response_requirement")
    @JsonProperty("response_requirement")
    String responseRequirement;

    @DynamoDBAttribute(attributeName = "response_delimiter")
    @JsonProperty("response_delimiter")
    String responseDelimiter;

    @DynamoDBAttribute(attributeName = "example_input")
    @JsonProperty("example_input")
    String exampleInput;

    @DynamoDBAttribute(attributeName = "example_output")
    @JsonProperty("example_output")
    String exampleOutput;

    @DynamoDBAttribute(attributeName = "prompt_format")
    @JsonProperty("prompt_format")
    String promptFormat;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "update_type")
    @JsonProperty("update_type")
    UpdateType updateType;

    @DynamoDBAttribute(attributeName = "update_delimiter")
    @JsonProperty("update_delimiter")
    String updateDelimiter;
}
