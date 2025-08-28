package net.apcehv.backrooms.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.apcehv.backrooms.BackroomsMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;


public class ModItems {
    public static final Item AlmondWater = registerItem("almond_water", new Item(new Item.Settings()));

    // вспомогательный метод для регистрации объекта
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(BackroomsMod.MOD_ID, name), item);
    }

    // добавить на вкладку креатива
    public static void registerModItems(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries ->{
            entries.add(AlmondWater);
        });
    }
}
