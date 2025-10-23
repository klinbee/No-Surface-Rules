package com.klinbee.nosurfacerules;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoSurfaceRulesConstants {

    public static final String MOD_ID = "nosurfacerules";
    public static final String MOD_NAME = "NoSurfaceRules";
    public static final String MOD_NAMESPACE = "no_surface_rules";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static ResourceLocation DIM_TAG_LOC = new ResourceLocation(MOD_NAMESPACE, "affected_dimensions");

}