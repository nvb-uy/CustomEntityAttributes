package elocindev.customentityattributes.api;

import java.util.List;

public class AdvancedEntityAttributeHolder {
    public String entity_regex;
    public float apply_chance;
    public String time_regex;
    public String dimension_regex;
    public String biome_regex;
    public String difficulty_regex;
    public double default_hp;
    public boolean only_apply_to_babies;
    public List<GenericAttribute<String,?>> attributes;

    public AdvancedEntityAttributeHolder(
        String entity_regex,
        String time_regex,
        String dimension_regex,
        String biome_regex,
        String difficulty_regex,
        double default_hp,
        float apply_chance,
        boolean only_apply_to_babies,
        List<GenericAttribute<String,?>> attributes        
    ) {
        this.entity_regex = entity_regex;
        this.attributes = attributes;
        this.dimension_regex = dimension_regex;
        this.biome_regex = biome_regex;
        this.default_hp = default_hp;
        this.apply_chance = apply_chance;
        this.time_regex = time_regex;
        this.only_apply_to_babies = only_apply_to_babies;
        this.difficulty_regex = difficulty_regex;
    }
}
