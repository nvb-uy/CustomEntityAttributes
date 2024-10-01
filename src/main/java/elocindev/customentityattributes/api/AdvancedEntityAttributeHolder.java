package elocindev.customentityattributes.api;

import java.util.List;

public class AdvancedEntityAttributeHolder {
    public String entity_regex;
    public String dimension_regex;
    public String biome_regex;
    public double default_hp;
    public List<String> affected_difficulties;
    public List<GenericAttribute<String,?>> attributes;

    public AdvancedEntityAttributeHolder(
        String entity_regex,
        String dimension_regex,
        String biome_regex,
        double default_hp,
        List<String> affected_difficulties,
        List<GenericAttribute<String,?>> attributes        
    ) {
        this.entity_regex = entity_regex;
        this.attributes = attributes;
        this.dimension_regex = dimension_regex;
        this.biome_regex = biome_regex;
        this.default_hp = default_hp;
        this.affected_difficulties = affected_difficulties;
    }
}
