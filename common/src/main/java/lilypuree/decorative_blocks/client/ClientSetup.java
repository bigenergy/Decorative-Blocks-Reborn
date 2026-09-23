package lilypuree.decorative_blocks.client;

import com.mojang.blaze3d.platform.InputConstants;
import lilypuree.decorative_blocks.fluid.ThatchFluid;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;

public class ClientSetup {

    public static final KeyMapping switchItemState = new KeyMapping("key.decorative_blocks.switch_item_state", InputConstants.Type.KEYSYM, -1, KeyMapping.Category.INVENTORY);

    // 1.21.11+: block render types are declared in block model JSON via "render_type" field.
    public static void initRenderLayers() {
    }

    public static void initItemPropertyFunctions() {
        // 1.21.4+: item model property predicates moved to JSON (select dispatcher).
        // See assets/decorative_blocks/items/*.json for the post/rope toggle.
    }

    /**
     * 26.1: fluid textures are no longer supplied by the loader-specific fluid handlers,
     * both loaders register a vanilla {@link FluidModel.Unbaked} instead.
     */
    public static FluidModel.Unbaked thatchFluidModel(ThatchFluid.FluidReferenceHolder referenceHolder) {
        return new FluidModel.Unbaked(
                new Material(referenceHolder.thatchStillTexture()),
                new Material(referenceHolder.thatchFlowingTexture()),
                new Material(referenceHolder.thatchStillTexture()),
                (BlockTintSource) null);
    }
}
