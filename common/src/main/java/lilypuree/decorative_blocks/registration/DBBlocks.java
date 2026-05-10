package lilypuree.decorative_blocks.registration;

import com.google.common.collect.ImmutableMap;
import lilypuree.decorative_blocks.Constants;
import lilypuree.decorative_blocks.blocks.*;
import lilypuree.decorative_blocks.blocks.types.VanillaWoodTypes;
import lilypuree.decorative_blocks.blocks.types.WoodDecorativeBlockTypes;
import lilypuree.decorative_blocks.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Supplier;

import static lilypuree.decorative_blocks.blocks.types.WoodDecorativeBlockTypes.*;

public class DBBlocks {

    public static final BlockWrapper<BonfireBlock> BONFIRE;
    public static final BlockWrapper<ChandelierBlock> CHANDELIER;
    public static final BlockWrapper<BrazierBlock> BRAZIER;
    public static final BlockWrapper<ChandelierBlock> SOUL_CHANDELIER;
    public static final BlockWrapper<BrazierBlock> SOUL_BRAZIER;
    public static final BlockWrapper<BonfireBlock> SOUL_BONFIRE;
    public static final BlockWrapper<BarPanelBlock> BAR_PANEL;
    public static final BlockWrapper<LatticeBlock> LATTICE;
    public static final BlockWrapper<ChainBlock> CHAIN;
    public static final BlockWrapper<PillarBlock> STONE_PILLAR;
    public static final BlockWrapper<RockyDirtBlock> ROCKY_DIRT;

    public static final ImmutableMap<WoodType, BlockWrapper<BeamBlock>> BEAMS;
    public static final ImmutableMap<WoodType, BlockWrapper<PalisadeBlock>> PALISADES;
    public static final ImmutableMap<WoodType, BlockWrapper<SupportBlock>> SUPPORTS;
    public static final ImmutableMap<WoodType, BlockWrapper<SeatBlock>> SEATS;

    public static ResourceKey<Block> blockKey(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    private static BlockBehaviour.Properties baseProps(String name) {
        return BlockBehaviour.Properties.of().setId(blockKey(name));
    }

    static {
        BONFIRE = registerBlock("bonfire", () -> new BonfireBlock(baseProps("bonfire")
                .sound(SoundType.WOOL).strength(0).mapColor(MapColor.FIRE)
                .pushReaction(PushReaction.DESTROY).replaceable().noCollision()
                .lightLevel(state -> 15).noLootTable()));
        SOUL_BONFIRE = registerBlock("soul_bonfire", () -> new BonfireBlock(baseProps("soul_bonfire")
                .sound(SoundType.WOOL).strength(0).mapColor(MapColor.COLOR_CYAN)
                .pushReaction(PushReaction.DESTROY).replaceable().noCollision()
                .lightLevel(state -> 14).noLootTable()));
        CHANDELIER = registerBlock("chandelier", () -> new ChandelierBlock(baseProps("chandelier")
                .sound(SoundType.WOOD).strength(0.3f).pushReaction(PushReaction.DESTROY)
                .replaceable().noCollision().noOcclusion().lightLevel(state -> 15), false));
        SOUL_CHANDELIER = registerBlock("soul_chandelier", () -> new ChandelierBlock(baseProps("soul_chandelier")
                .sound(SoundType.WOOD).strength(0.3f).pushReaction(PushReaction.DESTROY)
                .replaceable().noCollision().noOcclusion().lightLevel(state -> 11), true));
        BRAZIER = registerBlock("brazier", () -> new BrazierBlock(baseProps("brazier")
                .sound(SoundType.METAL).strength(3.0f).mapColor(MapColor.METAL).noOcclusion()
                .lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 15 : 0), false));
        SOUL_BRAZIER = registerBlock("soul_brazier", () -> new BrazierBlock(baseProps("soul_brazier")
                .sound(SoundType.METAL).strength(3.0f).mapColor(MapColor.METAL).noOcclusion()
                .lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 10 : 0), true));
        BAR_PANEL = registerBlock("bar_panel", () -> new BarPanelBlock(baseProps("bar_panel")
                .sound(SoundType.METAL).strength(5.0f).mapColor(MapColor.METAL).noOcclusion()));
        LATTICE = registerBlock("lattice", () -> new LatticeBlock(baseProps("lattice")
                .sound(SoundType.WOOD).strength(1.2f).mapColor(MapColor.WOOD).noOcclusion()));
        CHAIN = registerBlock("chain", () -> new ChainBlock(baseProps("chain")
                .mapColor(MapColor.METAL).strength(4.3F).sound(SoundType.METAL).noOcclusion()));
        STONE_PILLAR = registerBlock("stone_pillar", () -> new PillarBlock(baseProps("stone_pillar")
                .sound(SoundType.STONE).strength(1.5F, 6.5F).mapColor(MapColor.STONE)));
        ROCKY_DIRT = registerBlock("rocky_dirt", () -> new RockyDirtBlock(baseProps("rocky_dirt")));

        ImmutableMap.Builder<WoodType, BlockWrapper<BeamBlock>> beams = new ImmutableMap.Builder<>();
        ImmutableMap.Builder<WoodType, BlockWrapper<PalisadeBlock>> palisades = new ImmutableMap.Builder<>();
        ImmutableMap.Builder<WoodType, BlockWrapper<SupportBlock>> supports = new ImmutableMap.Builder<>();
        ImmutableMap.Builder<WoodType, BlockWrapper<SeatBlock>> seats = new ImmutableMap.Builder<>();

        for (WoodType woodType : VanillaWoodTypes.VANILLA) {
            MapColor mapColor = VanillaWoodTypes.getPlanks(woodType).defaultMapColor();
            if (woodType != WoodType.BAMBOO) {
                String beamName = DBNames.name(woodType, BEAM);
                beams.put(woodType, registerBlock(beamName, () -> (BeamBlock) createDecorativeBlock(beamName, woodType, mapColor, BEAM)));
            }
            String palisadeName = DBNames.name(woodType, PALISADE);
            palisades.put(woodType, registerBlock(palisadeName, () -> (PalisadeBlock) createDecorativeBlock(palisadeName, woodType, mapColor, PALISADE)));
            String supportName = DBNames.name(woodType, SUPPORT);
            supports.put(woodType, registerBlock(supportName, () -> (SupportBlock) createDecorativeBlock(supportName, woodType, mapColor, SUPPORT)));
            String seatName = DBNames.name(woodType, SEAT);
            seats.put(woodType, registerBlock(seatName, () -> (SeatBlock) createDecorativeBlock(seatName, woodType, mapColor, SEAT)));
        }
        BEAMS = beams.build();
        PALISADES = palisades.build();
        SUPPORTS = supports.build();
        SEATS = seats.build();

    }

    public static void init() {
    }

    public static IWoodenBlock createDecorativeBlock(String name, WoodType wood, MapColor mapColor, WoodDecorativeBlockTypes woodDecorativeBlockType) {
        BlockBehaviour.Properties properties = baseProps(name)
                .mapColor(mapColor)
                .sound(wood.soundType())
                .noOcclusion(); // non-full-cube — must not cull neighbor faces
        if (!VanillaWoodTypes.isNetherWood(wood)) {
            properties.ignitedByLava();
        }
        if (woodDecorativeBlockType == PALISADE) {
            properties.strength(2.0F, 4.0F);
        } else {
            properties.strength(1.2F);
        }
        return woodDecorativeBlockType.create(wood, properties);
    }

    private static <T extends Block> BlockWrapper<T> registerBlock(String name, Supplier<T> blockSupplier) {
        return new BlockWrapper<>(Services.PLATFORM.register(BuiltInRegistries.BLOCK, name, blockSupplier));
    }

}
