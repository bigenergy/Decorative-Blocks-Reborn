package lilypuree.decorative_blocks.client;

import lilypuree.decorative_blocks.fluid.ThatchFluid;
import lilypuree.decorative_blocks.registration.DBTags;
import net.minecraft.client.Camera;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;

public class FogHelper {
    public static Info onColorFog(Camera camera) {
        Entity entity = camera.entity();
        if (entity != null && entity.isEyeInFluid(DBTags.Fluids.THATCH)) {
            Level level = entity.level();
            FluidState state = level.getFluidState(BlockPos.containing(entity.getEyePosition()));
            if (state.getType() instanceof ThatchFluid thatchFluid) {
                int color = thatchFluid.getReferenceHolder().color();
                return decodeColor(color);
            }
        }
        return null;
    }

    public static Info decodeColor(int color) {
        float fogRed = ((float) (color >> 16 & 0xFF) / 0xFF);
        float fogGreen = ((float) ((color >> 8) & 0xFF) / 0xFF);
        float fogBlue = ((float) (color & 0xFF) / 0xFF);
        long biomeChangedTime = -1L;

        return new Info(fogRed, fogGreen, fogBlue, biomeChangedTime);
    }

    public static record Info(float fogRed, float fogGreen, float fogBlue, long time) {

    }

    public static void onFogSetup(Entity entity, float farPlaneDist) {
        // 1.21.11: fog near/far are now driven by GpuBufferSlice; custom thatch fog visuals stubbed.
    }
}
