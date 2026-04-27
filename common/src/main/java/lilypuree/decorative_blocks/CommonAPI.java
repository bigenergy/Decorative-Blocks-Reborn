package lilypuree.decorative_blocks;

import lilypuree.decorative_blocks.fluid.ThatchFluid;
import lilypuree.decorative_blocks.platform.Services;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class CommonAPI {

    public static GameRule<Boolean> RULE_DISABLE_THATCH = Services.PLATFORM.registerGameRule("disable_thatch", GameRuleCategory.MISC, false);

    public static Map<Block, Block> bonfireMap = new HashMap<>();

    public static Map<Block, ThatchFluid.FluidReferenceHolder> shearMap = new HashMap<>();

    public static void addThatchlikeFluid(ThatchFluid.FluidReferenceHolder referenceHolder) {
        shearMap.put(referenceHolder.getSourceBlock(), referenceHolder);
    }
}
