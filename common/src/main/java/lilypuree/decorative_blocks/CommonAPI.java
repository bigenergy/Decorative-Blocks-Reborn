package lilypuree.decorative_blocks;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;
import lilypuree.decorative_blocks.fluid.ThatchFluid;
import lilypuree.decorative_blocks.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRuleType;
import net.minecraft.world.level.gamerules.GameRuleTypeVisitor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CommonAPI {

    // Game rules are a regular registry since 1.21.11, so the rule goes through the normal registration path.
    // Command: /gamerule decorative_blocks:disable_thatch true
    public static final Supplier<GameRule<Boolean>> RULE_DISABLE_THATCH = Services.PLATFORM.register(BuiltInRegistries.GAME_RULE, "disable_thatch",
            () -> new GameRule<>(GameRuleCategory.MISC, GameRuleType.BOOL, BoolArgumentType.bool(), GameRuleTypeVisitor::visitBoolean,
                    Codec.BOOL, value -> value ? 1 : 0, false, FeatureFlagSet.of()));

    public static Map<Block, Block> bonfireMap = new HashMap<>();

    public static Map<Block, ThatchFluid.FluidReferenceHolder> shearMap = new HashMap<>();

    public static void addThatchlikeFluid(ThatchFluid.FluidReferenceHolder referenceHolder) {
        shearMap.put(referenceHolder.getSourceBlock(), referenceHolder);
    }

    /**
     * Called first thing from the mod entrypoints so the static registrations above
     * are queued before the registries are frozen.
     */
    public static void bootstrap() {
    }
}
