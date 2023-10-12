package com.airiea.dao.impl;

import com.airiea.dao.AgentDao;
import com.airiea.dao.orm.AbilityConfig;
import com.airiea.dao.orm.AgentConfig;
import com.airiea.dao.transform.AgentConfigUnmarshaller;
import com.airiea.dao.transform.AgentUnmarshaller;
import com.airiea.model.resource.Agent;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import java.util.List;
import java.util.Objects;

public class AgentDaoImpl implements AgentDao {
    private final DynamoDBMapper mapper;
    private final AgentUnmarshaller agentUnmarshaller;
    private final AgentConfigUnmarshaller agentConfigUnmarshaller;

    public AgentDaoImpl(final DynamoDBMapper mapper) {
        this.mapper = Objects.requireNonNull(mapper, "Mapper cannot be null");
        this.agentUnmarshaller = AgentUnmarshaller.getInstance();
        this.agentConfigUnmarshaller = AgentConfigUnmarshaller.getInstance();
    }

    @Override
    public void createAgent(Agent agent) {
        final AgentConfig agentConfig = agentConfigUnmarshaller.unmarshall(agent);
        final AgentConfig agentConfigFromDdb = mapper.load(agentConfig);

        if (agentConfigFromDdb != null) {
            throw new IllegalArgumentException("Agent already exist.");
        }

        final String abilityName = agent.getAbilityName();
        final AbilityConfig abilityConfigFromDdb = mapper.load(AbilityConfig.class, abilityName);

        if (abilityConfigFromDdb == null) {
            throw new IllegalArgumentException("Agent ability no found.");
        }

        mapper.save(agentConfig);
    }

    @Override
    public Agent getAgentByName(String agentName) {
        final AgentConfig agentConfig = mapper.load(AgentConfig.class, agentName);
        return agentUnmarshaller.unmarshallFromAgentConfig(agentConfig);
    }

    @Override
    public List<Agent> getAllAgents() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<AgentConfig> agentConfigList = mapper.scan(AgentConfig.class, scanExpression);
        return agentUnmarshaller.unmarshallFromAgentConfigList(agentConfigList);
    }
}
