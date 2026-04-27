package lilypuree.decorative_blocks.fluid;

import lilypuree.decorative_blocks.client.FogHelper;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.function.Consumer;

public class DBFluidType extends FluidType {
    private final Identifier flowingTexture;
    private final Identifier stillTexture;

    private final Identifier overlayTexture;

    private final int fogColor;

    /**
     * Default constructor.
     *
     * @param properties the general properties of the fluid type
     */
    public DBFluidType(Properties properties, Identifier stillTexture, Identifier flowingTexture, Identifier overlayTexture, int fogColor) {
        super(properties);
        this.flowingTexture = flowingTexture;
        this.stillTexture = stillTexture;
        this.overlayTexture = overlayTexture;
        this.fogColor = fogColor;
    }

    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public Identifier getFlowingTexture() {
                return flowingTexture;
            }

            @Override
            public Identifier getStillTexture() {
                return stillTexture;
            }

            @Override
            public @Nullable Identifier getOverlayTexture() {
                return overlayTexture;
            }

            @Override
            public @NotNull org.joml.Vector4f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, org.joml.Vector4f fluidFogColor) {
                FogHelper.Info colorInfo = FogHelper.decodeColor(fogColor);
                return new org.joml.Vector4f(colorInfo.fogRed(), colorInfo.fogGreen(), colorInfo.fogBlue(), fluidFogColor.w());
            }

            // 1.21.11: FogRenderer/FogShape removed; fog distances driven by GpuBufferSlice.
        });
    }
}
