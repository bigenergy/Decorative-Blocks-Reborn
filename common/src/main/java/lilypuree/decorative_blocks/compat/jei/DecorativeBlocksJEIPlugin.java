package lilypuree.decorative_blocks.compat.jei;

import lilypuree.decorative_blocks.Constants;
import lilypuree.decorative_blocks.registration.Registration;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IPlatformFluidHelper;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.material.Fluid;

/**
 * Loader independent: NeoForge finds it through {@link JeiPlugin},
 * Fabric through the {@code jei_mod_plugin} entrypoint in fabric.mod.json.
 */
@JeiPlugin
public class DecorativeBlocksJEIPlugin implements IModPlugin {
    private static final Identifier UID = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "main");

    @Override
    public Identifier getPluginUid() {
        return UID;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        addFluidInfo(registration, registration.getJeiHelpers().getPlatformFluidHelper(), Registration.STILL_THATCH.get(),
                Component.translatable("wiki.decorative_blocks.thatch"));
    }

    private static <T> void addFluidInfo(IRecipeRegistration registration, IPlatformFluidHelper<T> fluidHelper, Fluid fluid, Component... description) {
        T fluidStack = fluidHelper.create(fluid.builtInRegistryHolder(), fluidHelper.bucketVolume());
        registration.addIngredientInfo(fluidStack, fluidHelper.getFluidIngredientType(), description);
    }
}
