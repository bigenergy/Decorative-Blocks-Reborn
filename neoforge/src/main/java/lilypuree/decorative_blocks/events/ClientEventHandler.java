package lilypuree.decorative_blocks.events;

import lilypuree.decorative_blocks.Constants;
import lilypuree.decorative_blocks.client.ClientSetup;
import lilypuree.decorative_blocks.entity.EmptyRenderer;
import lilypuree.decorative_blocks.fluid.DBFluidType;
import lilypuree.decorative_blocks.registration.Registration;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterFluidModelsEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.fluids.FluidType;

@EventBusSubscriber(value = Dist.CLIENT, modid = Constants.MOD_ID)
public class ClientEventHandler {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent e) {
        e.enqueueWork(() -> {
            ClientSetup.initRenderLayers();
            ClientSetup.initItemPropertyFunctions();
        });
    }

    @SubscribeEvent
    public static void onEntityRendererRegistry(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Registration.DUMMY_ENTITY_TYPE.get(), EmptyRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterFluidModels(RegisterFluidModelsEvent event) {
        event.register(ClientSetup.thatchFluidModel(Registration.referenceHolder),
                Registration.STILL_THATCH.get(), Registration.FLOWING_THATCH.get());
    }

    @SubscribeEvent
    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        registerFluidTypeExtensions(event, Registration.STILL_THATCH.get().getFluidType());
        registerFluidTypeExtensions(event, Registration.FLOWING_THATCH.get().getFluidType());
    }

    private static void registerFluidTypeExtensions(RegisterClientExtensionsEvent event, FluidType fluidType) {
        if (fluidType instanceof DBFluidType dbFluidType) {
            event.registerFluidType(dbFluidType.createClientExtensions(), dbFluidType);
        }
    }

    @SubscribeEvent
    public static void onKeyMappingRegistry(RegisterKeyMappingsEvent event) {
//        event.register(ClientSetup.switchItemState);
    }
}
