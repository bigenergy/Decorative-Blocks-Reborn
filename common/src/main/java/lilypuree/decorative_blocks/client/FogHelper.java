package lilypuree.decorative_blocks.client;

import lilypuree.decorative_blocks.fluid.ThatchFluid;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector4f;

public class FogHelper {

    /**
     * Returns the thatch fluid the camera is submerged in, or {@code null}.
     * Same check NeoForge uses before calling the fluid type's fog extensions.
     */
    @Nullable
    public static ThatchFluid getThatchAtCamera(Camera camera, BlockGetter level) {
        BlockPos pos = camera.blockPosition();
        FluidState state = level.getFluidState(pos);
        if (state.getType() instanceof ThatchFluid thatchFluid && camera.position().y < pos.getY() + state.getHeight(level, pos)) {
            return thatchFluid;
        }
        return null;
    }

    public static void applyFogColor(int color, Vector4f fogColor) {
        fogColor.set(ARGB.redFloat(color), ARGB.greenFloat(color), ARGB.blueFloat(color), fogColor.w());
    }

    /**
     * Thick fog like lava, so being buried in thatch actually hides the world.
     */
    public static void applyFogDistance(Camera camera, float renderDistanceInBlocks, FogData fog) {
        if (camera.entity().isSpectator()) {
            fog.environmentalStart = -8.0F;
            fog.environmentalEnd = renderDistanceInBlocks * 0.5F;
        } else {
            fog.environmentalStart = 0.25F;
            fog.environmentalEnd = 1.0F;
        }
        fog.skyEnd = fog.environmentalEnd;
        fog.cloudEnd = fog.environmentalEnd;
    }
}
