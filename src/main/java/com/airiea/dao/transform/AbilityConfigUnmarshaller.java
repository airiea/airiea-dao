package com.airiea.dao.transform;

import com.airiea.dao.orm.AbilityConfig;
import com.airiea.model.resource.Ability;

import java.util.ArrayList;
import java.util.List;

public class AbilityConfigUnmarshaller {

    public AbilityConfig unmarshallFromAbility(final Ability ability) {
        if (ability == null) {
            return null;
        }
        return AbilityConfig.builder()
                .abilityName(ability.getAbilityName())
                .model(ability.getModel())
                .modelObject(ability.getModelObject())
                .maxTokens(ability.getMaxTokens())
                .temperature(ability.getTemperature())
                .description(ability.getDescription())
                .responseRequirement(ability.getResponseRequirement())
                .exampleInput(ability.getExampleInput())
                .exampleOutput(ability.getExampleOutput())
                .inputFormat(ability.getInputFormat())
                .updateType(ability.getUpdateType())
                .updateDelimiter(ability.getUpdateDelimiter())
                .build();
    }

    public List<AbilityConfig> unmarshallFromAbilityList(final List<Ability> abilityList) {
        List<AbilityConfig> abilityConfigList = new ArrayList<>();
        for (Ability ability : abilityList) {
            abilityConfigList.add(unmarshallFromAbility(ability));
        }
        return abilityConfigList;
    }

    private static AbilityConfigUnmarshaller instance;

    public static AbilityConfigUnmarshaller getInstance() {
        if (instance == null)
            instance = new AbilityConfigUnmarshaller();
        return instance;
    }
}
