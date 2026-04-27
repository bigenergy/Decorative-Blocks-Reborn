package lilypuree.decorative_blocks.platform;

import lilypuree.decorative_blocks.Constants;
import lilypuree.decorative_blocks.entity.DummyEntityForSitting;
import lilypuree.decorative_blocks.fluid.ForgeThatchFluid;
import lilypuree.decorative_blocks.fluid.ForgeThatchFluidBlock;
import lilypuree.decorative_blocks.fluid.ThatchFluid;
import lilypuree.decorative_blocks.platform.services.IPlatformHelper;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class NeoForgePlatformHelper implements IPlatformHelper {
    public static Map<Registry<?>, DeferredRegister<?>> registries = new HashMap<>();

    @Override
    public boolean isModLoaded(String modid) {
        return ModList.get().isLoaded(modid);
    }


    @Override
    public <I, T extends I> Supplier<T> register(Registry<I> registry, String name, Supplier<T> sup) {
        DeferredRegister<I> deferredRegister = (DeferredRegister<I>) registries.computeIfAbsent(registry, reg -> DeferredRegister.create(reg.key(), Constants.MOD_ID));
        return deferredRegister.register(name, sup);
    }



    @Override
    @SuppressWarnings("unchecked")
    public GameRule<Boolean> registerGameRule(String name, GameRuleCategory category, boolean defaultValue) {
        try {
            java.lang.reflect.Method m = net.minecraft.world.level.gamerules.GameRules.class
                    .getDeclaredMethod("registerBoolean", String.class, GameRuleCategory.class, boolean.class);
            m.setAccessible(true);
            return (GameRule<Boolean>) m.invoke(null, name, category, defaultValue);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to register game rule " + name, e);
        }
    }

    @Override
    public DummyEntityForSitting createDummyEntity(EntityType<DummyEntityForSitting> type, Level level) {
        return new DummyEntityForSitting(type, level);
    }

    @Override
    public LiquidBlock createThatchFluidBlock(Supplier<ThatchFluid.Source> fluid, BlockBehaviour.Properties properties) {
        return new ForgeThatchFluidBlock(fluid.get(), properties);
    }

    @Override
    public ThatchFluid.Flowing createThatchFlowingFluid(ThatchFluid.FluidReferenceHolder referenceHolder) {
        return new ForgeThatchFluid.Flowing(referenceHolder);
    }

    @Override
    public ThatchFluid.Source createThatchStillFluid(ThatchFluid.FluidReferenceHolder referenceHolder) {
        return new ForgeThatchFluid.Source(referenceHolder);
    }

    @Override
    public CreativeModeTab.Builder createModTab() {
        return CreativeModeTab.builder();
    }

    @Override
    public TagKey<Item> getShearTag() {
        return Tags.Items.TOOLS_SHEAR;
    }


}
