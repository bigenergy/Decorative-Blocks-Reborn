package lilypuree.decorative_blocks.compat;

import net.minecraft.world.entity.Entity;

public class SoulFired {
    public static void setSecondsOnFire(Entity entity, int seconds, boolean isSoul) {
        // Soul Fire'd compat not yet ported to 1.21.11; fall back to normal fire.
        entity.igniteForSeconds(seconds);
    }
}
