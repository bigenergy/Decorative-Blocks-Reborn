package lilypuree.decorative_blocks;

import lilypuree.decorative_blocks.fluid.ThatchFluid;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class CommonAPI {

    // TODO 1.21.11 port: re-add disable_thatch gamerule via NeoForge proper registration path or a config.
    // Vanilla GameRules registry is frozen before mod constructor in 1.21.11, so the old reflective path no longer works.

    public static Map<Block, Block> bonfireMap = new HashMap<>();

    public static Map<Block, ThatchFluid.FluidReferenceHolder> shearMap = new HashMap<>();

    public static void addThatchlikeFluid(ThatchFluid.FluidReferenceHolder referenceHolder) {
        shearMap.put(referenceHolder.getSourceBlock(), referenceHolder);
    }

    /** Kept as a no-op so existing entrypoint calls stay valid. */
    public static void bootstrap() {
    }
}
