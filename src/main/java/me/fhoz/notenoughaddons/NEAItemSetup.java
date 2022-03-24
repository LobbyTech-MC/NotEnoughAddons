package me.fhoz.notenoughaddons;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.fhoz.notenoughaddons.machines.BudgetDustFabricator;
import me.fhoz.notenoughaddons.utils.NEAItems;

import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public final class NEAItemSetup {
    // ItemGroup
    private static final NestedItemGroup notenoughaddons = new NestedItemGroup(
            new NamespacedKey(NotEnoughAddons.getInstance(), "notenoughaddons"),
            new CustomItemStack(Material.COMMAND_BLOCK, "&2&n&lN&r&aot&2&l&nE&r&anough&2&l&nA&r&addons")
    );

    private static final ItemGroup machines = new SubItemGroup(
        new NamespacedKey(NotEnoughAddons.getInstance(), "machines"), notenoughaddons,
        new CustomItemStack(Material.DEAD_BUSH, "&bMachines"), 1
    );

    // private static final ItemGroup items = new SubItemGroup(
    //     new NamespacedKey(NotEnoughAddons.getInstance(), "items"), notenoughaddons,
    //     new CustomItemStack(Material.DIAMOND_PICKAXE, "&bItems"), 2
    // );

    private NEAItemSetup() {
    }

    public static void setup(@Nonnull NotEnoughAddons plugin) {
        new BudgetDustFabricator(machines, NEAItems.BUDGET_DUST_FABRICATOR,
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                new ItemStack(Material.GRINDSTONE), new ItemStack(Material.GRINDSTONE), new ItemStack(Material.GRINDSTONE),
                SlimefunItems.GOLD_PAN, new ItemStack(Material.DIAMOND), SlimefunItems.GOLD_PAN,
                SlimefunItems.MAGNET, new ItemStack(Material.WATER_BUCKET), SlimefunItems.MAGNET
        }).register(plugin);
    }

}