package com.klinbee.nosurfacerules.mixin;

import com.klinbee.nosurfacerules.TaggedDimensions;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NoiseBasedChunkGenerator.class)
public class NoiseBasedChunkGeneratorMixin {

    @Inject(method = "buildSurface(Lnet/minecraft/server/level/WorldGenRegion;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/levelgen/RandomState;Lnet/minecraft/world/level/chunk/ChunkAccess;)V", at = @At("HEAD"), cancellable = true)
    private void no_surface_rules$buildSurface$disableSurfaceRulesIfTagged(WorldGenRegion worldGenRegion, StructureManager structureManager, RandomState randomState, ChunkAccess chunkAccess, CallbackInfo ci) {
        if (TaggedDimensions.shouldDisableOrThrow(worldGenRegion.getLevel().dimensionTypeId())) {
            ci.cancel();
        }
    }

}
