package lilypuree.decorative_blocks.fluid;

import lilypuree.decorative_blocks.client.FogHelper;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;
import org.joml.Vector4f;

public class DBFluidType extends FluidType {
    private final int fogColor;

    public DBFluidType(Properties properties, int fogColor) {
        super(properties);
        this.fogColor = fogColor;
    }

    public int getFogColor() {
        return fogColor;
    }

    /**
     * 26.1: textures moved to {@code RegisterFluidModelsEvent}; only fog tweaks remain here.
     * Registered from {@code ClientEventHandler} via {@code RegisterClientExtensionsEvent}.
     */
    public IClientFluidTypeExtensions createClientExtensions() {
        return new IClientFluidTypeExtensions() {
            @Override
            public void modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
                FogHelper.Info colorInfo = FogHelper.decodeColor(fogColor);
                fluidFogColor.set(colorInfo.fogRed(), colorInfo.fogGreen(), colorInfo.fogBlue(), fluidFogColor.w());
            }
        };
    }
}
