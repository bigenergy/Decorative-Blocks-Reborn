package lilypuree.decorative_blocks.compat;

import net.minecraft.world.entity.Entity;

public class SoulFired {
    public static void setSecondsOnFire(Entity entity, int seconds, boolean isSoul) {
        // Soul Fire'd compat is disabled for now (dependency removed from the build); fall back to normal fire.
        entity.igniteForSeconds(seconds);
    }
}
