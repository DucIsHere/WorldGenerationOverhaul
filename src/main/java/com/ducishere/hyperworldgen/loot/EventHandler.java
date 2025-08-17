package com.ducishere.hyperworldgen.loot;

import com.ducishere.hyperworldgen.item.ModItems;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "hyperworldgen")
public class LootHandler {

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        String path = event.getName().getPath();
        if(event.getName().getNamespace().equals("minecraft") && path.startsWith("chests/")) {
            LootPool pool = LootPool.builder()
                    .with(ItemEntry.builder(ModItems.FREE_LUCK_DISC_ITEM))
                    .rolls(ConstantLootNumberProvider.create(1))
                    .weight(1)
                    .build();
            event.getTable().addPool(pool);
        }
    }
}
