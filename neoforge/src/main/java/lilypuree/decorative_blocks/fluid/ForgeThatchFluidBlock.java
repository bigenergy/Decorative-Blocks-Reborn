package lilypuree.decorative_blocks.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import org.jetbrains.annotations.Nullable;

public class ForgeThatchFluidBlock extends LiquidBlock implements ThatchBlock {
    public ForgeThatchFluidBlock(FlowingFluid fluid, Properties properties) {
        super(fluid, properties);
    }

    @Override
    protected void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn, InsideBlockEffectApplier applier, boolean flag) {
        if (entityIn instanceof Player player) {
            playSoundIfMoving(player, worldIn, pos);
        }
    }

    @Override
    public ItemStack pickupBlock(@Nullable LivingEntity entity, LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
        return ItemStack.EMPTY;
    }
}
