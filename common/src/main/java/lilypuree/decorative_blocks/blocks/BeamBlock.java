package lilypuree.decorative_blocks.blocks;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.WoodType;

public class BeamBlock extends RotatedPillarBlock implements IWoodenBlock {
    private WoodType woodType;

    public BeamBlock(WoodType woodType, Properties properties) {
        super(properties);
        this.woodType = woodType;
    }

    @Override
    public WoodType getWoodType() {
        return woodType;
    }
}
