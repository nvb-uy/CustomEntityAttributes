package elocindev.customentityattributes.api;

import java.util.List;

public class BasicEntityAttributeHolder {
    public String entity_regex;
    public List<GenericAttribute<String,?>> attributes;

    public BasicEntityAttributeHolder(
        String entity_regex,
        List<GenericAttribute<String,?>> attribute_overrides        
    ) {
        this.entity_regex = entity_regex;
        this.attributes = attribute_overrides;
    }
}
