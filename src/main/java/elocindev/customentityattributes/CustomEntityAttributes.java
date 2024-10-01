package elocindev.customentityattributes;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.customentityattributes.config.CEABasicConfig;
import elocindev.customentityattributes.event.EntityLoadCallback;
import elocindev.necronomicon.api.config.v1.NecConfigAPI;

public class CustomEntityAttributes implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("customentityattributes");

	public static final String MOD_ID = "customentityattributes";
	public static CEABasicConfig BASIC_CONFIG;

	@Override
	public void onInitialize() {
		NecConfigAPI.registerConfig(CEABasicConfig.class);
		BASIC_CONFIG = CEABasicConfig.INSTANCE;

		EntityLoadCallback.register();
	}
}