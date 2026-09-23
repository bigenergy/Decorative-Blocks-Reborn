package lilypuree.decorative_blocks.fluid;

import lilypuree.decorative_blocks.client.FogHelper;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;
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
     * Textures are registered through {@code RegisterFluidModelsEvent}; only the fog lives here.
     * Registered from {@code ClientEventHandler} via {@code RegisterClientExtensionsEvent}.
     */
    public IClientFluidTypeExtensions createClientExtensions() {
        return new IClientFluidTypeExtensions() {
            @Override
            public void modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
                FogHelper.applyFogColor(fogColor, fluidFogColor);
            }

            @Override
            public void modifyFogRender(Camera camera, @Nullable FogEnvironment environment, float renderDistance, float partialTick, FogData fogData) {
                // NeoForge passes the render distance in chunks here
                FogHelper.applyFogDistance(camera, renderDistance * 16.0F, fogData);
            }
        };
    }
}
