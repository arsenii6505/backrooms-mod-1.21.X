package net.apcehv.backrooms.block;

import net.apcehv.backrooms.BackroomsMod;
import net.apcehv.backrooms.block.custom.Level0Lamp;
import net.apcehv.backrooms.item.ModItemGroups;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block LEVEL0_WALL = registerBlock("level0_wall", new PillarBlock(
            AbstractBlock.Settings.create().strength(3.0f).requiresTool().sounds(BlockSoundGroup.STONE).requiresTool()
    ));
    public static final Block LEVELO_LAMP = registerBlock("level0_lamp", new Level0Lamp(
            AbstractBlock.Settings.create()));

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
            entries.add(LEVELO_LAMP);
        });
    }
}
