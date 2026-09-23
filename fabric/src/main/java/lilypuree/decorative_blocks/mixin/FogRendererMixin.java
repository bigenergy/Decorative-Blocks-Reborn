package lilypuree.decorative_blocks.mixin;

import lilypuree.decorative_blocks.client.FogHelper;
import lilypuree.decorative_blocks.fluid.ThatchFluid;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.FogRenderer;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Fabric counterpart of NeoForge's fluid type fog extensions (see {@code DBFluidType}).
 */
@Mixin(FogRenderer.class)
public class FogRendererMixin {

    @Inject(method = "computeFogColor", at = @At("TAIL"))
    private void decorative_blocks$thatchFogColor(Camera camera, float partialTicks, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f dest, CallbackInfo ci) {
        ThatchFluid thatch = FogHelper.getThatchAtCamera(camera, level);
        if (thatch != null) {
            FogHelper.applyFogColor(thatch.getReferenceHolder().color(), dest);
        }
    }

    @Inject(method = "setupFog", at = @At("RETURN"))
    private void decorative_blocks$thatchFog(Camera camera, int renderDistanceInChunks, DeltaTracker deltaTracker, float darkenWorldAmount, ClientLevel level, CallbackInfoReturnable<FogData> cir) {
        if (FogHelper.getThatchAtCamera(camera, level) != null) {
            FogHelper.applyFogDistance(camera, renderDistanceInChunks * 16.0F, cir.getReturnValue());
        }
    }
}
