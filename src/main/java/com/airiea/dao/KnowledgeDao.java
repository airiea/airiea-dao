package com.airiea.dao;

import com.airiea.model.resource.Knowledge;

import java.util.List;

public interface KnowledgeDao {
    List<Knowledge> getKnowledgeByAgentName(String agentName);

    Knowledge getKnowledgeById(String knowledgeId);
}
