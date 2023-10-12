package com.airiea.dao.impl;

import com.airiea.dao.AbilityDao;
import com.airiea.dao.AbilityDao;
import com.airiea.dao.orm.AbilityConfig;
import com.airiea.dao.orm.AbilityConfig;
import com.airiea.dao.orm.AgentConfig;
import com.airiea.dao.transform.AbilityConfigUnmarshaller;
import com.airiea.dao.transform.AbilityUnmarshaller;
import com.airiea.dao.transform.AgentUnmarshaller;
import com.airiea.model.resource.Ability;
import com.airiea.model.resource.Agent;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import java.util.List;
import java.util.Objects;

public class AbilityDaoImpl implements AbilityDao {
    private final DynamoDBMapper mapper;

    private final AbilityUnmarshaller abilityUnmarshaller;
    private final AbilityConfigUnmarshaller abilityConfigUnmarshaller;

    public AbilityDaoImpl(final DynamoDBMapper mapper) {
        this.mapper = Objects.requireNonNull(mapper, "Mapper cannot be null");
        this.abilityUnmarshaller = AbilityUnmarshaller.getInstance();
        this.abilityConfigUnmarshaller = AbilityConfigUnmarshaller.getInstance();
    }

    @Override
    public void createAbility(Ability ability) {
        final AbilityConfig abilityConfig = abilityConfigUnmarshaller.unmarshallFromAbility(ability);
        final AbilityConfig abilityConfigFromDdb = mapper.load(abilityConfig);

        if (abilityConfigFromDdb != null) {
            throw new IllegalArgumentException("Ability already exist.");
        }

        mapper.save(abilityConfig);
    }

    @Override
    public Ability getAbilityByName(String AbilityName) {
        final AbilityConfig AbilityConfig = mapper.load(AbilityConfig.class, AbilityName);
        return abilityUnmarshaller.unmarshallFromAbilityConfig(AbilityConfig);
    }

    @Override
    public List<Ability> getAllAbilities() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<AbilityConfig> AbilityConfigList = mapper.scan(AbilityConfig.class, scanExpression);
        return abilityUnmarshaller.unmarshallFromAbilityConfigList(AbilityConfigList);
    }
}
