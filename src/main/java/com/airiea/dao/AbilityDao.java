package com.airiea.dao;

import com.airiea.model.resource.Ability;
import com.airiea.model.resource.Agent;

import java.util.List;

public interface AbilityDao {
    void createAbility(Ability ability);
    void updateAbility(Ability ability);
    Ability getAbilityByName(String abilityName);
    List<Ability> getAllAbilities();
}
