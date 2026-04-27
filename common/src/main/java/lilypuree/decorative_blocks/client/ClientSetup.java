package lilypuree.decorative_blocks.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;

public class ClientSetup {

    public static final KeyMapping switchItemState = new KeyMapping("key.decorative_blocks.switch_item_state", InputConstants.Type.KEYSYM, -1, KeyMapping.Category.INVENTORY);

    // 1.21.11: block render types are declared in block model JSON via "render_type" field.
    public static void initRenderLayers() {
    }

    public static void initItemPropertyFunctions() {
        // 1.21.4+: item model property predicates moved to JSON (select dispatcher).
        // See assets/decorative_blocks/items/*.json for the post/rope toggle.
    }


}
