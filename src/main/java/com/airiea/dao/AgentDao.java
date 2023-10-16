package com.airiea.dao;

import com.airiea.model.resource.Agent;

import java.util.List;

public interface AgentDao {
    void createAgent(Agent agent);
    void updateAgent(Agent agent);
    Agent getAgentByName(String agentName);
    List<Agent> getAllAgents();
}
