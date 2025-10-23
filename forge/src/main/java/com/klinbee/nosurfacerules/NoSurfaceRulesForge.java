package com.klinbee.nosurfacerules;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(NoSurfaceRulesConstants.MOD_ID)
public class NoSurfaceRulesForge {

    public NoSurfaceRulesForge() {
        NoSurfaceRulesConstants.LOG.info("I sure hope you're in a mod pack designed for this...");
        MinecraftForge.EVENT_BUS.register(NoSurfaceRulesForge.class);
    }

    @SubscribeEvent
    public static void onServerAboutToStartEvent(ServerAboutToStartEvent event) {
        TaggedDimensions.initCache(event.getServer().registryAccess());
    }

}