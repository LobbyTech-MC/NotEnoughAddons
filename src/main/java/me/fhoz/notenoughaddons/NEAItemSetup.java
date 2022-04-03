package me.fhoz.notenoughaddons;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.fhoz.notenoughaddons.machines.BudgetDustFabricator;
import me.fhoz.notenoughaddons.items.AngelBlock;
import me.fhoz.notenoughaddons.items.backpacks.MinerBackpack;
import me.fhoz.notenoughaddons.items.electric.FlyingBubble;
import me.fhoz.notenoughaddons.utils.NEAItems;

import javax.annotation.Nonnull;


import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public final class NEAItemSetup {
        private NEAItemSetup() {}
        // ItemGroup
        private static final NestedItemGroup notenoughaddons = new NestedItemGroup(
                new NamespacedKey(NotEnoughAddons.getInstance(), "notenoughaddons"),
                new CustomItemStack(Material.COMMAND_BLOCK, "&2&n&lN&r&aot&2&l&nE&r&anough&2&l&nA&r&addons")
        );

        private static final ItemGroup machines = new SubItemGroup(
                new NamespacedKey(NotEnoughAddons.getInstance(), "machines"), notenoughaddons,
                new CustomItemStack(Material.DEAD_BUSH, "&bMachines"), 1
        );

        private static final ItemGroup items = new SubItemGroup(
                new NamespacedKey(NotEnoughAddons.getInstance(), "items"), notenoughaddons,
                new CustomItemStack(Material.LEAD, "&bItems"), 2
        );


        public static void setup(@Nonnull NotEnoughAddons plugin) {
                
                // Machines
                new BudgetDustFabricator(machines, NEAItems.BUDGET_DUST_FABRICATOR,
                        RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                        new ItemStack(Material.GRINDSTONE), new ItemStack(Material.GRINDSTONE), new ItemStack(Material.GRINDSTONE),
                        SlimefunItems.GOLD_PAN, new ItemStack(Material.DIAMOND), SlimefunItems.GOLD_PAN,
                        SlimefunItems.MAGNET, new ItemStack(Material.WATER_BUCKET), SlimefunItems.MAGNET
                }).register(plugin);

                new FlyingBubble(machines, NEAItems.FLYING_BUBBLE, RecipeType.ENHANCED_CRAFTING_TABLE, 
                new ItemStack[] {
                        SlimefunItems.BLISTERING_INGOT_3, new ItemStack(Material.DIAMOND_BLOCK), SlimefunItems.BLISTERING_INGOT_3,
                        new ItemStack(Material.PHANTOM_MEMBRANE), SlimefunItems.BIG_CAPACITOR , new ItemStack(Material.PHANTOM_MEMBRANE),
                        SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT
                })
                .setEnergyCapacity(1024)
                .setEnergyConsumption(128)
                .setProcessingSpeed(1)
                .register(plugin);
                
                // Items
                new AngelBlock(items, NEAItems.ANGEL_BLOCK,
                        RecipeType.MAGIC_WORKBENCH, new ItemStack[]{
                        new ItemStack(Material.FEATHER), SlimefunItems.MAGIC_LUMP_2,  new ItemStack(Material.FEATHER),
                        SlimefunItems.MAGIC_LUMP_2, new ItemStack(Material.COBBLESTONE), SlimefunItems.MAGIC_LUMP_2,
                        new ItemStack(Material.FEATHER), SlimefunItems.MAGIC_LUMP_2, new ItemStack(Material.FEATHER)
                }).register(plugin);

                new MinerBackpack(54, items, NEAItems.MINER_BACKPACK, RecipeType.MAGIC_WORKBENCH,
                new ItemStack[] {
                        SlimefunItems.EARTH_RUNE, SlimefunItems.TALISMAN_MINER, SlimefunItems.EARTH_RUNE, 
                        SlimefunItems.ENDER_LUMP_3, SlimefunItems.BACKPACK_SMALL, SlimefunItems.ENDER_LUMP_3, 
                        SlimefunItems.EARTH_RUNE, SlimefunItems.TALISMAN_MINER, SlimefunItems.EARTH_RUNE
                }).register(plugin);
    }
}
