package com.airiea.dao.transform;

import com.airiea.dao.orm.KnowledgeEntry;
import com.airiea.model.resource.Knowledge;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeUnmarshaller {
    public Knowledge unmarshallFromKnowledgeEntry(final KnowledgeEntry knowledgeEntry) {
        if (knowledgeEntry == null) {
            return null;
        }
        return Knowledge.builder()
                .knowledgeId(knowledgeEntry.getKnowledgeId())
                .entityId(knowledgeEntry.getEntityId())
                .agentName(knowledgeEntry.getAgentName())
                .content(knowledgeEntry.getContent())
                .updatedDate(knowledgeEntry.getUpdatedDate())
                .createdDate(knowledgeEntry.getCreatedDate())
                .build();
    }

    public List<Knowledge> unmarshallFromKnowledgeEntryList(final List<KnowledgeEntry> knowledgeEntryList) {
        List<Knowledge> knowledgeList = new ArrayList<>();
        for (KnowledgeEntry entry : knowledgeEntryList) {
            knowledgeList.add(unmarshallFromKnowledgeEntry(entry));
        }
        return knowledgeList;
    }

    private static KnowledgeUnmarshaller instance;

    public static KnowledgeUnmarshaller getInstance() {
        if (instance == null)
            instance = new KnowledgeUnmarshaller();
        return instance;
    }
}
