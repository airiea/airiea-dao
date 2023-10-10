package com.airiea.dao.manager;

import com.airiea.dao.orm.AgentConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;
import java.util.Objects;

public class AgentConfigDynamoDbManager extends AbstractDynamoDbManager<AgentConfig> {

    private static final int QUERY_LIMIT = 1;
    private final DynamoDBMapper mapper;

    public AgentConfigDynamoDbManager(final DynamoDBMapper mapper) {
        this.mapper = Objects.requireNonNull(mapper, "Mapper cannot be null");
    }

    public void create(final AgentConfig item) {
        mapper.save(item);
    }

    public void update(final AgentConfig item) {
        validateOnUpdate(item, mapper);
        mapper.save(item);
    }

    public void remove(final AgentConfig item) {
        throw new IllegalArgumentException("Training knowledge base can't be deleted.");
    }

    public AgentConfig getAgentByName(final String agentName) {
        return mapper.load(AgentConfig.class, agentName);
    }

    public List<AgentConfig> queryWithLimit(DynamoDBQueryExpression<AgentConfig> expression) {
        validateOnQuery(expression, QUERY_LIMIT);
        return mapper.query(AgentConfig.class, expression);
    }

}
