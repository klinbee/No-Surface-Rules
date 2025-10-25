package com.klinbee.nosurfacerules;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.HashSet;
import java.util.Set;

public final class TaggedDimensions {

    private TaggedDimensions() {
    }

    private static final Set<ResourceKey<DimensionType>> DISABLED_SURFACE = new HashSet<>();
    private static boolean VALID = false;

    public static boolean shouldDisableOrThrow(ResourceKey<DimensionType> dimType) {
        if (VALID) {
            return DISABLED_SURFACE.contains(dimType);
        }
        throw new IllegalStateException("Attempted to access TaggedDimension cache while invalidated");
    }

    public static void resetCache() {
        DISABLED_SURFACE.clear();
        VALID = false;
    }

    public static void initCache(RegistryAccess registryAccess) {
        resetCache();
        Registry<DimensionType> dimTypeRegistry = registryAccess.registryOrThrow(Registries.DIMENSION_TYPE);

        TagKey<DimensionType> tag = TagKey.create(Registries.DIMENSION_TYPE,
                NoSurfaceRulesConstants.DIM_TAG_LOC
        );

        dimTypeRegistry.getTag(tag).ifPresent(holders ->
                holders.forEach(holder ->
                        dimTypeRegistry.getResourceKey(holder.value())
                                .ifPresent(DISABLED_SURFACE::add)
                )
        );
        VALID = true;
        // Case for empty tag or missing tag
        if (DISABLED_SURFACE.isEmpty()) {
            NoSurfaceRulesConstants.LOG.info("No dimensions marked to disable surface rules for! No tag: " + tag);
        }
        StringBuilder disabledDimensionsMessage = new StringBuilder("Marked dimensions to disable surface rules for: ");
        DISABLED_SURFACE.forEach(dimKey -> disabledDimensionsMessage.append(dimKey).append(" "));
        NoSurfaceRulesConstants.LOG.info(disabledDimensionsMessage.toString());
    }

}
