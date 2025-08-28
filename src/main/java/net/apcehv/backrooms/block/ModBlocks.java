package net.apcehv.backrooms.block;

import net.apcehv.backrooms.BackroomsMod;
import net.apcehv.backrooms.item.ModItemGroups;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block LEVEL0_WALL = registerBlock("level0_wall", new Block(
            AbstractBlock.Settings.create().strength(3.0f).requiresTool().sounds(BlockSoundGroup.STONE).requiresTool()
    ));

    // зарегать блок
    public static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(BackroomsMod.MOD_ID, name), block);
    }

    //зарегать предмет блока
    public static void registerBlockItem(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(BackroomsMod.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }

    // добавить в креатив таб
    public static void registerModBlocks(){
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.BACKROOMS_BLOCKS).
                register(entries -> {
            entries.add(LEVEL0_WALL);
        });
    }
}
