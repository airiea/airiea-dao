package com.airiea.dao.transform;

import com.airiea.dao.orm.AgentConfig;
import com.airiea.model.resource.Agent;

public class AgentConfigUnmarshaller {
    public AgentConfig unmarshall(final Agent agent) {
        if (agent == null) {
            return null;
        }

        return AgentConfig.builder()
                .agentName(agent.getAgentName())
                .abilityName(agent.getAbilityName())
                .agentGoal(agent.getAgentGoal())
                .agentRole(agent.getAgentRole())
                .build();
    }

    private static AgentConfigUnmarshaller instance;

    public static AgentConfigUnmarshaller getInstance() {
        if (instance == null)
            instance = new AgentConfigUnmarshaller();
        return instance;
    }
}
