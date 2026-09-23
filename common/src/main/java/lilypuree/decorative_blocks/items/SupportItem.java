package lilypuree.decorative_blocks.items;

import lilypuree.decorative_blocks.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.function.Consumer;

public class SupportItem extends SwitchableBlockItem {
    public static final Identifier OVERRIDE_TAG = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "up");

    public SupportItem(Block blockIn, Properties builder) {
        super(blockIn, builder, BlockStateProperties.UP, true);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag tooltipFlag) {
        if (Minecraft.getInstance().hasShiftDown()) {
            tooltip.accept(Component.translatable("wiki.decorative_blocks.support.condition1"));
            tooltip.accept(Component.translatable("wiki.decorative_blocks.support.behavior1"));
            tooltip.accept(Component.literal(""));
            tooltip.accept(Component.translatable("wiki.decorative_blocks.support.condition2"));
            tooltip.accept(Component.translatable("wiki.decorative_blocks.support.behavior2"));
            tooltip.accept(Component.literal(""));
            tooltip.accept(Component.translatable("wiki.decorative_blocks.support.condition3"));
            tooltip.accept(Component.translatable("wiki.decorative_blocks.support.behavior3"));
        }
        super.appendHoverText(stack, ctx, display, tooltip, tooltipFlag);
    }
}
