package cn.hydcraft.hydronyasama.forge.content;

import cn.hydcraft.hydronyasama.BeaconProviderMod;
import cn.hydcraft.hydronyasama.core.content.ModContent;
import cn.hydcraft.hydronyasama.core.registry.ContentId;
import cn.hydcraft.hydronyasama.core.registry.ContentRegistrar;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
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
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ForgeContentRegistrar implements ContentRegistrar {

    private final DeferredRegister<Block> blocks;
    private final DeferredRegister<Item> items;
    private final DeferredRegister<CreativeModeTab> tabs;

    private final RegistryObject<CreativeModeTab> tabCore;
    private final RegistryObject<CreativeModeTab> tabBuilding;

    private final Map<ContentId, RegistryObject<Block>> blockIndex = new HashMap<>();
    private final Map<RegistryObject<Item>, String> itemGroups = new HashMap<>();

    public ForgeContentRegistrar(IEventBus modBus) {
        blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, BeaconProviderMod.MOD_ID);
        items = DeferredRegister.create(ForgeRegistries.ITEMS, BeaconProviderMod.MOD_ID);
        tabs = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BeaconProviderMod.MOD_ID);

        tabCore = tabs.register("core", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup." + ModContent.MOD_GROUP_ID + ".core"))
                .icon(() -> new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModContent.MOD_GROUP_ID, "nsdn_logo"))))
                .build());

        tabBuilding = tabs.register("building", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup." + ModContent.MOD_GROUP_ID + ".building"))
                .icon(() -> new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModContent.MOD_GROUP_ID, "anti_static_floor_block"))))
                .build());

        blocks.register(modBus);
        items.register(modBus);
        tabs.register(modBus);
        modBus.addListener(this::buildContents);
    }

    private void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == tabCore.getKey()) {
            for (Map.Entry<RegistryObject<Item>, String> entry : itemGroups.entrySet()) {
                if ("core".equals(entry.getValue())) {
                    event.accept(entry.getKey());
                }
            }
        } else if (event.getTabKey() == tabBuilding.getKey()) {
            for (Map.Entry<RegistryObject<Item>, String> entry : itemGroups.entrySet()) {
                if ("building".equals(entry.getValue())) {
                    event.accept(entry.getKey());
                }
            }
        }
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
        RegistryObject<Item> item = items.register(id.getPath(), () -> new BlockItem(blockHandle.get(), new Item.Properties()));
        itemGroups.put(item, definition.contentGroup);
        return item;
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
            case "vslab":
            case "vstrip":
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
                return new FenceGateBlock(props, net.minecraft.world.level.block.state.properties.WoodType.OAK);
            case "pane":
                return new IronBarsBlock(props);
            default:
                return new Block(props);
        }
    }
}
