package com.airiea.dao.transform;

import com.airiea.dao.orm.AgentConfig;
import com.airiea.model.resource.Agent;

import java.util.ArrayList;
import java.util.List;

public class AgentUnmarshaller {

    public Agent unmarshallFromAgentConfig(final AgentConfig agentConfig) {
        if (agentConfig == null) {
            return null;
        }
        return Agent.builder()
                .agentName(agentConfig.getAgentName())
                .abilityName(agentConfig.getAbilityName())
                .agentGoal(agentConfig.getAgentGoal())
                .agentRole(agentConfig.getAgentRole())
                .createdDate(agentConfig.getCreatedDate())
                .updatedDate(agentConfig.getUpdatedDate())
                .build();
    }

    public List<Agent> unmarshallFromAgentConfigList(final List<AgentConfig> agentConfigList) {
        List<Agent> agentList = new ArrayList<>();
        for (AgentConfig agentConfig : agentConfigList) {
            agentList.add(unmarshallFromAgentConfig(agentConfig));
        }
        return agentList;
    }

    private static AgentUnmarshaller instance;

    public static AgentUnmarshaller getInstance() {
        if (instance == null)
            instance = new AgentUnmarshaller();
        return instance;
    }
}
