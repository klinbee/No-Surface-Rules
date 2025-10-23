package com.klinbee.nosurfacerules;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class NoSurfaceRulesFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        NoSurfaceRulesConstants.LOG.info("I sure hope you're in a mod pack designed for this...");
        ServerLifecycleEvents.SERVER_STARTING.register(server -> TaggedDimensions.initCache(server.registryAccess()));
    }

}
