package lilypuree.decorative_blocks.client;

import com.mojang.blaze3d.platform.InputConstants;
import lilypuree.decorative_blocks.fluid.ThatchFluid;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;

/*
 * Things that used to be set up here in code and are now data driven in 26.1:
 * - block render layers are picked per quad from texture transparency (no more cutout registration);
 * - the support/seat item variants are chosen by assets/decorative_blocks/items/*.json
 *   (minecraft:select on the minecraft:block_state component).
 */
public class ClientSetup {

    public static final KeyMapping switchItemState = new KeyMapping("key.decorative_blocks.switch_item_state", InputConstants.Type.KEYBOARD, InputConstants.UNKNOWN.getValue(), KeyMapping.Category.INVENTORY);

    /**
     * Fluid textures are no longer supplied by loader-specific fluid handlers,
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
