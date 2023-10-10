package com.airiea.dao.manager;

import com.airiea.dao.orm.AbilityConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;
import java.util.Objects;

public class AbilityConfigDynamoDbManager extends AbstractDynamoDbManager<AbilityConfig> {

    private static final int QUERY_LIMIT = 1;
    private final DynamoDBMapper mapper;

    public AbilityConfigDynamoDbManager(final DynamoDBMapper mapper) {
        this.mapper = Objects.requireNonNull(mapper, "Mapper cannot be null");
    }

    public void create(final AbilityConfig item) {
        mapper.save(item);
    }

    public void update(final AbilityConfig item) {
        validateOnUpdate(item, mapper);
        mapper.save(item);
    }

    public void remove(final AbilityConfig item) {
        throw new IllegalArgumentException("AbilityConfig can't be deleted.");
    }

    public AbilityConfig get(final String abilityName) {
        return mapper.load(AbilityConfig.class, abilityName);
    }

    public List<AbilityConfig> queryWithLimit(DynamoDBQueryExpression<AbilityConfig> expression) {
        validateOnQuery(expression, QUERY_LIMIT);
        return mapper.query(AbilityConfig.class, expression);
    }

}
