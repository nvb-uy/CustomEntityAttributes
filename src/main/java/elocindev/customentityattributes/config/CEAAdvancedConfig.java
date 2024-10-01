package elocindev.customentityattributes.config;

import java.nio.file.Path;
import java.util.List;

import elocindev.customentityattributes.api.BasicEntityAttributeHolder;
import elocindev.customentityattributes.api.GenericAttribute;
import elocindev.necronomicon.api.config.v1.NecConfigAPI;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;

public class CEAAdvancedConfig {
    public static final String FOLDER = "custom_entity_attributes";
    public static final String FILE_NAME = "basic.json5";
    public static final int CURRENT_CONFIG_VERSION = 2;

    @NecConfig
    public static CEABasicConfig INSTANCE;

    public static String getFile() {
        Path folder = Path.of(NecConfigAPI.getFile(FOLDER));

        if (!folder.toFile().exists())
            folder.toFile().mkdirs();
          
        return folder.toString()+"/"+FILE_NAME;
    }

    @Comment("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    @Comment("                                                            Custom Entity Attributes (CEA) by ElocinDev")
    @Comment("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    @Comment("                                                                            Basic Config ")
    @Comment("                                                Reloaded via datapack reload (/reload) or by restarting the game")
    @Comment("                                        This config allows you to add attribute modifiers globally to specific entities.")
    @Comment("                           For more customization, such as per-dimension, per-biome or per-difficulty, please use the advanced config.")
    @Comment("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    @Comment("Option Index:")
    @Comment("  entity_regex     :   Entity's ID (Supports regex). For example: minecraft:zombie (to affect zombies) or minecraft:.* (to affect all minecraft entities)")
    @Comment("  dimnension_regex :   The dimension ID (Supports regex). For example: minecraft:overworld (to affect the overworld) or minecraft:.* (to affect all minecraft dimensions)")
    @Comment("  attribute_overrides :   A List of attribute modifiers to add, you can add as many as you want.")
    @Comment("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    @Comment("The example showcases a config to add +10 attack damage and +20% attack speed to an example_entity.")
    @Comment("You can use this example as a base to edit what you want, for example if you want to make zombies have 20 HP, just set the attribute to minecraft:generic.max_health and")
    @Comment("set the value to 20.0, finally use ADDITION for operation, so it adds +20 HP, leaving 40 HP total.")
    @Comment("You can also use MULTIPLY_BASE at 0.20 to give +20% max health.")
    @Comment(" ")
    @Comment(" * If you modify the max HP of an entity, it'll automatically recalculate the HP the mob spawns to keep the same percentage.")
    @Comment(" this means that entities that spawn with a set percentage (like the wither) will work as expected.")
    @Comment(" * To modify the default HP an entity spawns with, use the advanced config.")
    @Comment("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")
    @Comment("Operations:")
    @Comment("  ADDITION       :   Adds the value to the base value.")
    @Comment("  MULTIPLY_BASE  :   Multiplies the base value by the value.")
    @Comment("  MULTIPLY_TOTAL :   Multiplies the total value by the value.")
    @Comment("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")

    public List<BasicEntityAttributeHolder> modifiers = List.of(
        new BasicEntityAttributeHolder(
            "examplemod:example_entity",
            List.of(
                new GenericAttribute<>("minecraft:generic.attack_damage", 10.0, "ADDITION"),
                new GenericAttribute<>("minecraft:generic.attack_speed", 0.20, "MULTIPLY_BASE")
            )
        )
    );

    @Comment("Don't touch this!")
    public int CONFIG_VERSION = 1;
}
