package lilypuree.decorative_blocks;

import lilypuree.decorative_blocks.registration.DBBlocks;
import lilypuree.decorative_blocks.registration.DBTags;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;

public class FuelRegistration {
    public static void init() {
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(DBTags.Items.BEAMS_THAT_BURN, 300);
            builder.add(DBTags.Items.PALISADES_THAT_BURN, 300);
            builder.add(DBTags.Items.SEATS_THAT_BURN, 300);
            builder.add(DBTags.Items.SUPPORTS_THAT_BURN, 300);
            builder.add(DBTags.Items.CHANDELIERS, 1600);
            builder.add(DBBlocks.LATTICE, 100);
        });
    }
}