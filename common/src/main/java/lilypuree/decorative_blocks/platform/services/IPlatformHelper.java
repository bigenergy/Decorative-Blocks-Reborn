package lilypuree.decorative_blocks.platform.services;

import lilypuree.decorative_blocks.entity.DummyEntityForSitting;
import lilypuree.decorative_blocks.fluid.ThatchFluid;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public interface IPlatformHelper {

    boolean isModLoaded(String modid);

    <I, T extends I> Supplier<T> register(Registry<I> registry, String name, Supplier<T> sup);

    GameRule<Boolean> registerGameRule(String name, GameRuleCategory category, boolean defaultValue);

    DummyEntityForSitting createDummyEntity(EntityType<DummyEntityForSitting> type, Level level);

    LiquidBlock createThatchFluidBlock(Supplier<ThatchFluid.Source> fluid, BlockBehaviour.Properties properties);

    default ThatchFluid.Flowing createThatchFlowingFluid(ThatchFluid.FluidReferenceHolder referenceHolder) {
        return new ThatchFluid.Flowing(referenceHolder);
    }

    default ThatchFluid.Source createThatchStillFluid(ThatchFluid.FluidReferenceHolder referenceHolder) {
        return new ThatchFluid.Source(referenceHolder);
    }

    CreativeModeTab.Builder createModTab();

    TagKey<Item> getShearTag();
}
