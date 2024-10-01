package elocindev.customentityattributes.event;

import java.util.UUID;

import elocindev.customentityattributes.CustomEntityAttributes;
import elocindev.customentityattributes.api.BasicEntityAttributeHolder;
import elocindev.customentityattributes.api.GenericAttribute;
import elocindev.customentityattributes.api.InvalidAttributeException;
import elocindev.customentityattributes.config.CEABasicConfig;
import elocindev.necronomicon.api.NecUtilsAPI;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;

public class EntityLoadCallback {
    public static void register() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
			if (entity instanceof LivingEntity livingEntity) {
                CEABasicConfig basic = CustomEntityAttributes.BASIC_CONFIG;

                for (BasicEntityAttributeHolder property : basic.modifiers) {
                    String ent_regex = property.entity_regex;

                    if (NecUtilsAPI.getEntityId(livingEntity).matches(ent_regex)) {
                        float old_hp = livingEntity.getMaxHealth();
                        float old_percentage = livingEntity.getHealth() / old_hp;
                        
                        for (GenericAttribute<?, ?> attribute : property.attributes) {
                            try {
                            UUID uuid = UUID.nameUUIDFromBytes(("cea"+attribute.toString()+attribute.getOperation().getId()+((int)attribute.getDouble())).getBytes());
                            EntityAttributeInstance instance = livingEntity.getAttributeInstance(attribute.getAttribute());
                            if (instance == null) continue;

                            instance.removeModifier(uuid);

                            instance.addPersistentModifier(
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
		});
    }
}
