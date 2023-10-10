package com.airiea.dao.manager;

import com.airiea.dao.orm.KnowledgeEntry;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;
import java.util.Objects;

public class KnowledgeEntryDynamoDbManager extends AbstractDynamoDbManager<KnowledgeEntry> {
    private static final int QUERY_LIMIT = 100;
    private final DynamoDBMapper mapper;

    public KnowledgeEntryDynamoDbManager(final DynamoDBMapper mapper) {
        this.mapper = Objects.requireNonNull(mapper, "Mapper cannot be null");
    }

    public void create(final KnowledgeEntry item) {
        validateOnCreate(item, mapper);
        mapper.save(item);
    }

    public void update(final KnowledgeEntry item) {
        validateOnUpdate(item, mapper);
        mapper.save(item);
    }

    public void remove(final KnowledgeEntry item) {
        mapper.delete(item);
    }

    public KnowledgeEntry getKnowledgeEntryById(final String knowledgeId) {
        return mapper.load(KnowledgeEntry.class, knowledgeId);
    }

    public List<KnowledgeEntry> queryWithLimit(DynamoDBQueryExpression<KnowledgeEntry> expression) {
        validateOnQuery(expression, QUERY_LIMIT);
        return mapper.query(KnowledgeEntry.class, expression);
    }

}
