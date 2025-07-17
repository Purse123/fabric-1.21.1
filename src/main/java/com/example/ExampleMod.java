package com.example;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;

import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

import net.minecraft.registry.RegistryKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.item.Items.register;

public class ExampleMod implements ModInitializer {
	public static final String MOD_ID = "modid";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// [SOURCE](https://docs.fabricmc.net/develop/items/food)
	public static final ConsumableComponent BURGER_CONSUMABLE_COMPONENT = ConsumableComponents.food()
			// The duration is in ticks, 20 ticks = 1 second
			.consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 15 * 20, 1), 0.5f))
			.build();

	public static final FoodComponent BURGER_FOOD_COMPONENT = new FoodComponent.Builder()
			.nutrition(6)
			.saturationModifier(1.0f)
			.alwaysEdible()
			.build();

	public static final Item BURGER = register(
			"burger",
			Item::new,
			new Item.Settings().food(BURGER_FOOD_COMPONENT, BURGER_CONSUMABLE_COMPONENT)
	);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		ItemGroupEvents
				.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
				.register(entries -> {
					entries.add(BURGER);
				});
	}
}