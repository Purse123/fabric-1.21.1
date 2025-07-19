package com.example.item;

import com.example.ExampleMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.minecraft.item.Items.register;

import java.util.function.Function;

public class ModItems {
    public static final ConsumableComponent POISON_FOOD_CONSUMABLE_COMPONENT = ConsumableComponents.food()
            // The duration is in ticks, 20 ticks = 1 second
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            new StatusEffectInstance(StatusEffects.POISON, 6 * 20, 1), 1.0f)
            )
            .build();

    public static final FoodComponent POISON_FOOD_COMPONENT = new FoodComponent.Builder()
            .alwaysEdible()
            .build();

    /*
    public static final Item BANANA = register(
            "banana",
            Item::new,
            new Item.Settings().food(POISON_FOOD_COMPONENT, POISON_FOOD_CONSUMABLE_COMPONENT)
    );
    */

    public static final Item BANANA = registerItem("banana", setting -> new Item(setting
            .food(POISON_FOOD_COMPONENT, POISON_FOOD_CONSUMABLE_COMPONENT)));

    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(
                Registries.ITEM, Identifier.of(ExampleMod.MOD_ID, name),
                function.apply(
                        new Item.Settings()
                                .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ExampleMod.MOD_ID, name)))
                )
        );
    }


    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(BANANA);
        });
        ExampleMod.LOGGER.info(ExampleMod.MOD_ID + " is Working GGGGGGGG");
    }
}