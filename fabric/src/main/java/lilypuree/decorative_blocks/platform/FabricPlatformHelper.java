package lilypuree.decorative_blocks.platform;

import lilypuree.decorative_blocks.Constants;
import lilypuree.decorative_blocks.FabricThatchFluidBlock;
import lilypuree.decorative_blocks.entity.DummyEntityForSitting;
import lilypuree.decorative_blocks.fluid.ThatchFluid;
import lilypuree.decorative_blocks.platform.services.IPlatformHelper;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public boolean isModLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    @Override
    public <I, T extends I> Supplier<T> register(Registry<I> registry, String name, Supplier<T> sup) {
        T object = Registry.register(registry, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), sup.get());
        return () -> object;
    }

    @Override
    public GameRule<Boolean> registerGameRule(String name, GameRuleCategory category, boolean defaultValue) {
        return GameRuleBuilder.forBoolean(defaultValue).category(category)
                .buildAndRegister(Identifier.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    @Override
    public DummyEntityForSitting createDummyEntity(EntityType<DummyEntityForSitting> type, Level level) {
        return new DummyEntityForSitting(type, level);
    }

    @Override
    public LiquidBlock createThatchFluidBlock(Supplier<ThatchFluid.Source> fluid, BlockBehaviour.Properties properties) {
        return new FabricThatchFluidBlock(fluid.get(), properties);
    }

//    @Override
//    public ThatchFluid.Flowing createThatchFlowingFluid(ThatchFluid.FluidReferenceHolder referenceHolder) {
//        return new ThatchFluid.Flowing(referenceHolder);
//    }
//
//    @Override
//    public ThatchFluid.Source createThatchStillFluid(ThatchFluid.FluidReferenceHolder referenceHolder) {
//        return new ThatchFluid.Source(referenceHolder);
//    }

    @Override
    public CreativeModeTab.Builder createModTab() {
        return FabricItemGroup.builder();
    }

    @Override
    public TagKey<Item> getShearTag() {
        return ConventionalItemTags.SHEARS;
    }
}
