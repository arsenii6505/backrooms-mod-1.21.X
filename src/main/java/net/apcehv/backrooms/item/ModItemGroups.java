package net.apcehv.backrooms.item;

import net.apcehv.backrooms.BackroomsMod;
import net.apcehv.backrooms.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;




public class ModItemGroups {
    // для добавление предметов
    public static final RegistryKey<ItemGroup> BACKROOMS_ITEMS = getRegisterKeyItemGroup("backrooms_items");
    public static final RegistryKey<ItemGroup> BACKROOMS_BLOCKS = getRegisterKeyItemGroup("backrooms_blocks");

    // хз зачем
    public static final ItemGroup BACKROOMS_ITEMS_GROUP = registerItemGroup("backrooms_items",
            Text.translatable("itemgroup.backrooms-mod.backrooms_items"), new ItemStack(ModItems.AlmondWater),
            false, false, false, null);
    public static final ItemGroup BACKROOMS_BLOCKS_GROUP = registerItemGroup("backrooms_blocks",
            Text.translatable("itemgroup.backrooms-mod.backrooms_blocks"), new ItemStack(ModBlocks.LEVEL0_WALL),
            false, false, false, null);


    // регестрирует группу
    public static ItemGroup registerItemGroup(String name, Text displayName, ItemStack iconItem, boolean isSpecial,
                                         boolean isNoRenderName, boolean isNoScrollBar, Identifier texture) {
        var fabricItemGroupBuilder = FabricItemGroup.builder();
        fabricItemGroupBuilder.displayName(displayName);
        fabricItemGroupBuilder.icon(() -> iconItem);
        if (isSpecial) { fabricItemGroupBuilder.special();}
        if (isNoRenderName) {fabricItemGroupBuilder.noRenderedName();}
        if (isNoScrollBar) {fabricItemGroupBuilder.noScrollbar();}
        if (texture != null) {fabricItemGroupBuilder.texture(texture);}
        return Registry.register(Registries.ITEM_GROUP,
                Identifier.of(BackroomsMod.MOD_ID, name),
                fabricItemGroupBuilder.build());
    }

    // возвращает ключ по name группы, надо для добавления предметов в группу
    public static RegistryKey<ItemGroup> getRegisterKeyItemGroup(String name){
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(BackroomsMod.MOD_ID, name));
    }

    //бесполезная хуйня, не знаю зачем
    public static void registerItemGroups() {

    }
}
