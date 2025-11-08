package net.hothlica.writhe.registry;

import net.hothlica.writhe.Writhe;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {

    //Make itemgroup (literally the tab thing you see at the top of your inventory)
    public static final RegistryKey<ItemGroup> WRITHE_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Writhe.id("writhe_group"));
    public static final ItemGroup WRITHE_GROUP = FabricItemGroup.builder()
            .icon(() -> ModItems.BERRYSHARD.getDefaultStack())
            .displayName(Text.translatable("itemGroup.writhe"))
            .build();

    //INITIALIZE ITEMS HERE
    public static Item BERRYSHARD = register("berryshard", new Item(new Item.Settings()
            .food(new FoodComponent.Builder().nutrition(3).saturationModifier(50.0f).snack().build())));
    public static Item STEPPING_STONE = register("stepping_stone", new Item(new Item.Settings()));

    public static void init() {
        Registry.register(Registries.ITEM_GROUP, WRITHE_GROUP_KEY, WRITHE_GROUP);
    }

    public static Item registerBlockItem(Block block) {
        return register(Registries.BLOCK.getId(block), new BlockItem(block, new Item.Settings()));
    }

    public static Item register(String id, Item item) {
        return register(Writhe.id(id), item);
    }

    public static Item register(Identifier id, Item item) {
        Item returnItem = Registry.register(Registries.ITEM, id, item);
        ItemGroupEvents.modifyEntriesEvent(WRITHE_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(returnItem.getDefaultStack());
        });
        return returnItem;
    }
}
