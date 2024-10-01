package elocindev.customentityattributes.event;

import java.util.UUID;

import elocindev.customentityattributes.CustomEntityAttributes;
import elocindev.customentityattributes.api.AdvancedEntityAttributeHolder;
import elocindev.customentityattributes.api.BasicEntityAttributeHolder;
import elocindev.customentityattributes.api.GenericAttribute;
import elocindev.customentityattributes.api.InvalidAttributeException;
import elocindev.customentityattributes.config.CEAAdvancedConfig;
import elocindev.customentityattributes.config.CEABasicConfig;
import elocindev.necronomicon.api.NecUtilsAPI;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.world.World;

public class EntityLoadCallback {
    public static void register() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
			if (entity instanceof LivingEntity livingEntity) {

                float old_hp = livingEntity.getMaxHealth();
                float old_percentage = livingEntity.getHealth() / old_hp;

                applyBasicConfig(livingEntity, old_hp, old_percentage);
                applyAdvancedConfig(livingEntity, old_hp, old_percentage);
            }
		});
    }

    public static void applyBasicConfig(LivingEntity livingEntity, float old_hp, float old_percentage) {
        CEABasicConfig basic = CustomEntityAttributes.BASIC_CONFIG;

        for (BasicEntityAttributeHolder property : basic.modifiers) {
            String ent_regex = property.entity_regex;

            if (isValid(NecUtilsAPI.getEntityId(livingEntity), ent_regex)) {
                for (GenericAttribute<?, ?> attribute : property.attributes) {
                    try {
                        if (property.apply_chance < 100 && Math.random() * 100 > property.apply_chance) continue;

                        UUID uuid = UUID.nameUUIDFromBytes(("cea"+attribute.toString()+attribute.getOperation().getId()+((int)attribute.getDouble())).getBytes());
                        EntityAttributeInstance instance = livingEntity.getAttributeInstance(attribute.getAttribute());
                        if (instance == null) continue;

                        instance.removeModifier(uuid);

                        instance.addTemporaryModifier(
                            new EntityAttributeModifier(
                                uuid.toString(),
                                attribute.getDouble(),
                                attribute.getOperation()
                            )
                        );

                    } catch (InvalidAttributeException e) {
                        e.printStackTrace();
                    }
                }

                float final_hp = livingEntity.getMaxHealth();
                if (old_hp != final_hp) {
                    livingEntity.setHealth(final_hp * old_percentage);
                }
            }
        }

    }

    public static void applyAdvancedConfig(LivingEntity livingEntity, float old_hp, float old_percentage) {
        CEAAdvancedConfig config = CustomEntityAttributes.ADVANCED_CONFIG;

        World world = livingEntity.getEntityWorld();
        String entityId = NecUtilsAPI.getEntityId(livingEntity);
        String dimensionId = world.getRegistryKey().getValue().toString();
        String biomeId = world.getBiome(livingEntity.getBlockPos()).getKey().get().getValue().toString();
        String difficulty = world.getDifficulty().getName();

        for (AdvancedEntityAttributeHolder property : config.advanced_modifiers) {
            if (isValid(entityId, property.entity_regex) &&
                isValid(dimensionId, property.dimension_regex) &&
                isValid(biomeId, property.biome_regex) &&
                isValidDifficulty(world, difficulty, property.difficulty_regex)) {
                
                if (!livingEntity.isBaby() && property.only_apply_to_babies) continue;

                for (GenericAttribute<?, ?> attribute : property.attributes) {
                    try {
                        if (property.apply_chance < 100 && Math.random() * 100 > property.apply_chance) continue;
                        if ((world.isDay() && property.time_regex.equals("night")) || world.isNight() && property.time_regex.equals("day")) continue;

                        UUID uuid = UUID.nameUUIDFromBytes(("cea" + attribute.toString() + attribute.getOperation().getId() + ((int) attribute.getDouble())).getBytes());
                        EntityAttributeInstance instance = livingEntity.getAttributeInstance(attribute.getAttribute());
                        if (instance == null) continue;

                        instance.removeModifier(uuid);

                        instance.addTemporaryModifier(
                            new EntityAttributeModifier(
                                uuid.toString(),
                                attribute.getDouble(),
                                attribute.getOperation()
                            )
                        );

                    } catch (InvalidAttributeException e) {
                        e.printStackTrace();
                    }
                }

                float final_hp = livingEntity.getMaxHealth();
                
                if (old_hp != final_hp && property.default_hp == -1) {
                    livingEntity.setHealth(final_hp * old_percentage);
                } else if (property.default_hp != -1) {
                    livingEntity.setHealth((float) property.default_hp);
                }
            }
        }
    }

    public static boolean isValidDifficulty(World world, String source, String regex) {
        if (("hard_only".equals(regex) || "hard_only".matches(regex)) && world.getLevelProperties().isHardcore()) return false;
        if ("hardcore".matches(regex) && world.getLevelProperties().isHardcore()) return true;

        return isValid(source, regex);
    }

    public static boolean isValid(String source, String regex) {
        if (regex.equals("*")) return true;

        return source.equals(regex) || source.matches(regex);
    }
}
