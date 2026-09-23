package lilypuree.decorative_blocks.items;

import lilypuree.decorative_blocks.Constants;
import lilypuree.decorative_blocks.blocks.state.ModBlockProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.function.Consumer;

public class SeatItem extends SwitchableBlockItem {
    public static final Identifier OVERRIDE_TAG = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "post");

    public SeatItem(Block blockIn, Properties builder) {
        super(blockIn, builder, ModBlockProperties.POST, false);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag tooltipFlag) {
        if (Minecraft.getInstance().hasShiftDown()) {
            tooltip.accept(Component.translatable("wiki.decorative_blocks.seat"));
        }
        super.appendHoverText(stack, ctx, display, tooltip, tooltipFlag);
    }
}
