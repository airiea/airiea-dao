package com.airiea.dao.transform;

import com.airiea.dao.orm.AbilityConfig;
import com.airiea.dao.orm.AgentConfig;
import com.airiea.model.resource.Ability;
import com.airiea.model.resource.Agent;

import java.util.ArrayList;
import java.util.List;

public class AbilityUnmarshaller {

    public Ability unmarshallFromAbilityConfig(final AbilityConfig abilityConfig) {
        if (abilityConfig == null) {
            return null;
        }
        return Ability.builder()
                .abilityName(abilityConfig.getAbilityName())
                .model(abilityConfig.getModel())
                .modelObject(abilityConfig.getModelObject())
                .maxTokens(abilityConfig.getMaxTokens())
                .temperature(abilityConfig.getTemperature())
                .description(abilityConfig.getDescription())
                .responseRequirement(abilityConfig.getResponseRequirement())
                .responseDelimiter(abilityConfig.getResponseDelimiter())
                .exampleInput(abilityConfig.getExampleInput())
                .exampleOutput(abilityConfig.getExampleOutput())
                .promptFormat(abilityConfig.getPromptFormat())
                .updateType(abilityConfig.getUpdateType())
                .updateDelimiter(abilityConfig.getUpdateDelimiter())
                .build();
    }

    public List<Ability> unmarshallFromAbilityConfigList(final List<AbilityConfig> abilityConfigList) {
        List<Ability> abilityList = new ArrayList<>();
        for (AbilityConfig abilityConfig : abilityConfigList) {
            abilityList.add(unmarshallFromAbilityConfig(abilityConfig));
        }
        return abilityList;
    }

    private static AbilityUnmarshaller instance;

    public static AbilityUnmarshaller getInstance() {
        if (instance == null)
            instance = new AbilityUnmarshaller();
        return instance;
    }
}
