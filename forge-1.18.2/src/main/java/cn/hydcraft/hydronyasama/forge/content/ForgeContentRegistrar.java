package cn.hydcraft.hydronyasama.forge.content;

import cn.hydcraft.hydronyasama.BeaconProviderMod;
import cn.hydcraft.hydronyasama.core.content.ModContent;
import cn.hydcraft.hydronyasama.core.registry.ContentId;
import cn.hydcraft.hydronyasama.core.registry.ContentRegistrar;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ForgeContentRegistrar implements ContentRegistrar {

    private final DeferredRegister<Block> blocks;
    private final DeferredRegister<Item> items;

    public static final CreativeModeTab TAB_CORE = new CreativeModeTab(ModContent.MOD_GROUP_ID + ".core") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModContent.MOD_GROUP_ID, "nsdn_logo")));
        }
    };

    public static final CreativeModeTab TAB_BUILDING = new CreativeModeTab(ModContent.MOD_GROUP_ID + ".building") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModContent.MOD_GROUP_ID, "anti_static_floor_block")));
        }
    };

    private final Map<ContentId, RegistryObject<Block>> blockIndex = new HashMap<>();

    public ForgeContentRegistrar(IEventBus modBus) {
        blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, BeaconProviderMod.MOD_ID);
        items = DeferredRegister.create(ForgeRegistries.ITEMS, BeaconProviderMod.MOD_ID);
        blocks.register(modBus);
        items.register(modBus);
    }

    @Override
    public Object registerBlock(BlockDefinition definition) {
        ResourceLocation id = new ResourceLocation(definition.id.namespace(), definition.id.path());
        RegistryObject<Block> handle = blocks.register(id.getPath(), () -> createBlock(definition));
        blockIndex.put(definition.id, handle);
        return handle;
    }

    @Override
    public Object registerItem(ItemDefinition definition) {
        if (!"block_item".equals(definition.kind)) {
            throw new IllegalArgumentException("Unsupported item kind: " + definition.kind);
        }
        if (definition.blockId == null) {
            throw new IllegalArgumentException("blockId is required for block_item");
        }
        RegistryObject<Block> blockHandle = blockIndex.get(definition.blockId);
        if (blockHandle == null) {
            throw new IllegalStateException("Block not registered: " + definition.blockId);
        }
        ResourceLocation id = new ResourceLocation(definition.id.namespace(), definition.id.path());
        return items.register(id.getPath(), () -> {
            CreativeModeTab tab;
            if ("core".equals(definition.contentGroup)) {
                tab = TAB_CORE;
            } else if ("building".equals(definition.contentGroup)) {
                tab = TAB_BUILDING;
            } else {
                tab = TAB_CORE; // Fallback
            }
            return new BlockItem(blockHandle.get(), new Item.Properties().tab(tab));
        });
    }

    private Block createBlock(BlockDefinition definition) {
        Block baseBlock = Blocks.STONE;
        if (definition.baseBlockId != null) {
            RegistryObject<Block> ro = blockIndex.get(definition.baseBlockId);
            if (ro != null) {
                try {
                    baseBlock = ro.get();
                } catch (Exception e) {
                    // Ignore
                }
            }
        }

        BlockBehaviour.Properties props = BlockBehaviour.Properties.copy(baseBlock);
        if ("glass".equals(definition.material)) {
            props = BlockBehaviour.Properties.copy(Blocks.GLASS).lightLevel((state) -> definition.lightLevel);
        } else if ("iron".equals(definition.material)) {
            props = BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK);
        }

        switch (definition.kind) {
            case "cube":
            case "simple_block":
                return new Block(props);
            case "simple_glass_block":
                return new GlassBlock(props);
            case "slab":
            case "carpet":
            case "edge":
            case "roof":
            case "strip":
            case "v_slab":
            case "v_strip":
                return new SlabBlock(props);
            case "stairs":
                final Block finalBaseBlock = baseBlock;
                return new StairBlock(() -> finalBaseBlock.defaultBlockState(), props);
            case "wall":
                return new WallBlock(props);
            case "fence":
            case "railing":
                return new FenceBlock(props);
            case "fence_gate":
                return new FenceGateBlock(props);
            case "pane":
                return new IronBarsBlock(props);
            default:
                return new Block(props);
        }
    }
}
