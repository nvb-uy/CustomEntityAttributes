package elocindev.customentityattributes.config;

import java.nio.file.Path;
import java.util.List;

import elocindev.customentityattributes.api.AdvancedEntityAttributeHolder;
import elocindev.customentityattributes.api.GenericAttribute;
import elocindev.necronomicon.api.config.v1.NecConfigAPI;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;

public class CEAAdvancedConfig {
    public static final String FOLDER = "custom_entity_attributes";
    public static final String FILE_NAME = "advanced.json5";
    public static final int CURRENT_CONFIG_VERSION = 1;

    @NecConfig
    public static CEAAdvancedConfig INSTANCE;

    public static String getFile() {
        Path folder = Path.of(NecConfigAPI.getFile(FOLDER));

        if (!folder.toFile().exists())
            folder.toFile().mkdirs();
          
        return folder.toString()+"/"+FILE_NAME;
    }

    @Comment("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    @Comment("                                                                Custom Entity Attributes (CEA) by ElocinDev")
    @Comment("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    @Comment("                                                                             Advanced Config ")
    @Comment("                                                    Reloaded via datapack reload (/reload) or by restarting the game")
    @Comment("                                            This config allows you to add attribute modifiers globally to specific entities.")
    @Comment("                               For more customization, such as per-dimension, per-biome or per-difficulty, please use the advanced config.")
    @Comment("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    @Comment("Option Index:")
    @Comment("  entity_regex        :   Entity's ID (Supports regex). For example: minecraft:zombie (to affect zombies) or minecraft:.* (to affect all minecraft entities)")
    @Comment("  apply_chance        :   The chance the modifier will be applied to the entity. 100.0 is 100% chance, 50.0 is 50% chance, etc.")
    @Comment("  time_regex          :   The time it needs to be to apply it. For example: day (to apply it during the day) or night (to apply it during the night) or *) for both.")
    @Comment("  dimnension_regex    :   The dimension ID (Supports regex). For example: minecraft:overworld (to affect the overworld) or minecraft:.* (to affect all minecraft dimensions)")
    @Comment("  biome_regex         :   The biome ID (Supports regex). For example: minecraft:plains (to affect plains) or minecraft:.* (to affect all minecraft biomes)")
    @Comment("  difficulty_regex    :   A List of difficulties to affect. For example: easy (to affect easy difficulty) or (hard|hardcore) (to affect only to specific difficulties)")
    @Comment("  default_hp          :   The default HP the entity spawns with. If this is set to -1, it'll spawn the entity at the same percentage as it was before applying.")
    @Comment("  only_apply_to_babies:   If true, it'll only apply to baby entities, such as baby zombies or baby villagers.")
    @Comment("  attribute_overrides :   A List of attribute modifiers to add, you can add as many as you want.")
    @Comment("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    @Comment("Difficulty Index:")
    @Comment("  peaceful        :   Peaceful difficulty")
    @Comment("  easy            :   Easy difficulty")
    @Comment("  normal          :   Normal difficulty")
    @Comment("  hard            :   Hard (and hardcore) difficulty")
    @Comment("  hard_only       :   Hard difficulty, excluding hardcore")
    @Comment("  hardcore        :   Only when hardcore is enabled")
    @Comment("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    @Comment("The example showcases a config to add +10 attack damage and +20% attack speed to an example_entity.")
    @Comment("If the entity spawns in the overworld or nether, in a plains, ocean, warped forest or any hills biome, and in easy, normal or hard difficulty.")
    @Comment("You can use this example as a base to edit what you want, for example if you want to make zombies have 20 HP, just set the attribute to minecraft:generic.max_health and")
    @Comment("set the value to 20.0, finally use ADDITION for operation, so it adds +20 HP, leaving 40 HP total.")
    @Comment("You can also use MULTIPLY_BASE at 0.20 to give +20% max health.")
    @Comment(" ")
    @Comment(" * If you modify the max HP of an entity, it'll automatically recalculate the HP the mob spawns to keep the same percentage.")
    @Comment(" this means that entities that spawn with a set percentage (like the wither) will work as expected.")
    @Comment(" * To modify the default HP an entity spawns with, use the default_hp option, you can set any value you want, do keep in mind of maximum health though.")
    @Comment("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    @Comment("Operations:")
    @Comment("  ADDITION       :   Adds the value to the base value.")
    @Comment("  MULTIPLY_BASE  :   Multiplies the base value by the value.")
    @Comment("  MULTIPLY_TOTAL :   Multiplies the total value by the value.")
    @Comment("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")

    public List<AdvancedEntityAttributeHolder> advanced_modifiers = List.of(
        new AdvancedEntityAttributeHolder(
            "examplemod:example_entity",
            "*",
            "(minecraft:overworld|minecraft:the_nether)",
            "(minecraft:plains|minecraft:ocean|minecraft:.*_hills|minecraft:warped_forest)",
            "(easy|normal|hard)",
            -1,
            100.0f,
            false,
            List.of(
                new GenericAttribute<>("minecraft:generic.attack_damage", 10.0, "ADDITION"),
                new GenericAttribute<>("minecraft:generic.attack_speed", 0.20, "MULTIPLY_BASE")
            )
        )
    );

    @Comment("Don't touch this!")
    public int CONFIG_VERSION = 1;
}
