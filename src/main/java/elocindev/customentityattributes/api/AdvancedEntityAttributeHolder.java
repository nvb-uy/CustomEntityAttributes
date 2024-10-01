package elocindev.customentityattributes.api;

import java.util.List;

public class AdvancedEntityAttributeHolder {
    public String entity_regex;
    public String dimension_regex;
    public String biome_regex;
    public String difficulty_regex;
    public double default_hp;
    public List<GenericAttribute<String,?>> attributes;

    public AdvancedEntityAttributeHolder(
        String entity_regex,
        String dimension_regex,
        String biome_regex,
        String difficulty_regex,
        double default_hp,
        List<GenericAttribute<String,?>> attributes        
    ) {
        this.entity_regex = entity_regex;
        this.attributes = attributes;
        this.dimension_regex = dimension_regex;
        this.biome_regex = biome_regex;
        this.default_hp = default_hp;
        this.difficulty_regex = difficulty_regex;
    }
}
