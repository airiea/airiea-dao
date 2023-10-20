package com.airiea.dao.impl;

import com.airiea.dao.KnowledgeDao;
import com.airiea.dao.orm.KnowledgeEntry;
import com.airiea.dao.transform.KnowledgeUnmarshaller;
import com.airiea.model.resource.Knowledge;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;
import java.util.Objects;

public class KnowledgeDaoImpl implements KnowledgeDao {
    private final DynamoDBMapper mapper;

    private final KnowledgeUnmarshaller knowledgeUnmarshaller;

    public KnowledgeDaoImpl(final DynamoDBMapper mapper) {
        this.mapper = Objects.requireNonNull(mapper, "Mapper cannot be null");
        this.knowledgeUnmarshaller = KnowledgeUnmarshaller.getInstance();
    }

    @Override
    public List<Knowledge> getKnowledgeByAgentName(String agentName) {
        KnowledgeEntry gsiKeys = new KnowledgeEntry();
        gsiKeys.setAgentName(agentName);

        DynamoDBQueryExpression<KnowledgeEntry> queryExpression = new DynamoDBQueryExpression<KnowledgeEntry>()
                .withIndexName("agent_name-entity_id")
                .withHashKeyValues(gsiKeys)
                .withConsistentRead(false);

        final List<KnowledgeEntry> knowledgeEntryList = mapper.query(KnowledgeEntry.class, queryExpression);
        return knowledgeUnmarshaller.unmarshallFromKnowledgeEntryList(knowledgeEntryList);
    }

    @Override
    public Knowledge getKnowledgeById(String knowledgeId) {
        final KnowledgeEntry knowledgeEntry = mapper.load(KnowledgeEntry.class, knowledgeId);
        return knowledgeUnmarshaller.unmarshallFromKnowledgeEntry(knowledgeEntry);
    }
}
