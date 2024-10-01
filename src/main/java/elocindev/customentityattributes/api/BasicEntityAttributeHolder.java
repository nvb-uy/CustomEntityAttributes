package elocindev.customentityattributes.api;

import java.util.List;

public class BasicEntityAttributeHolder {
    public String entity_regex;
    public float apply_chance;
    public List<GenericAttribute<String,?>> attributes;

    public BasicEntityAttributeHolder(
        String entity_regex,
        float apply_chance,
        List<GenericAttribute<String,?>> attribute_overrides        
    ) {
        this.entity_regex = entity_regex;
        this.apply_chance = apply_chance;
        this.attributes = attribute_overrides;
    }
}
