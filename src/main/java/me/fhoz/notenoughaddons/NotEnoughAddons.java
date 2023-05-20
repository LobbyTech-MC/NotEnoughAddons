package me.fhoz.notenoughaddons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.RayTraceResult;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import lombok.SneakyThrows;
import me.fhoz.notenoughaddons.items.AngelBlock;
import me.fhoz.notenoughaddons.items.backpacks.MinerBackpack;
import me.fhoz.notenoughaddons.listeners.MinerBackpackListener;
import me.fhoz.notenoughaddons.services.UpdateService;
import me.fhoz.notenoughaddons.utils.NEAItems;
import me.fhoz.notenoughaddons.utils.Utils;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import net.milkbowl.vault.economy.Economy;

public class NotEnoughAddons extends JavaPlugin implements SlimefunAddon {

    private static NotEnoughAddons instance;
    public static final HashMap<ItemStack, List<Pair<ItemStack, List<RecipeChoice>>>> shapedVanillaRecipes = new HashMap<>();
    public static final HashMap<ItemStack, List<Pair<ItemStack, List<RecipeChoice>>>> shapelessVanillaRecipes =
            new HashMap<>();
    
    private final UpdateService updateService = new UpdateService(this); 
    private static Logger log = null;
    private static Economy econ = null;

    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;
        setLog(instance.getLogger());
        saveDefaultConfig();
        // Read something from your config.yml
        Config cfg = new Config(this);
        //new Thread(updateService::start, "NotEnoughAddons").start();


        // Register ACT Recipes
        Iterator<Recipe> recipeIterator = Bukkit.recipeIterator();
        while (recipeIterator.hasNext()) {
            Recipe r = recipeIterator.next();

            if (r instanceof ShapedRecipe) {
                ShapedRecipe sr = (ShapedRecipe) r;
                List<RecipeChoice> rc = new ArrayList<>();
                ItemStack key = new ItemStack(sr.getResult().getType(), 1);

                // Convert the recipe to a list
                for (Map.Entry<Character, RecipeChoice> choice : sr.getChoiceMap().entrySet()) {
                    if (choice.getValue() != null) {
                        rc.add(choice.getValue());
                    }
                }

                if (!shapedVanillaRecipes.containsKey(key)) {
                    shapedVanillaRecipes.put(key,
                            new ArrayList<>(Collections.singletonList(new Pair<>(sr.getResult(), rc))));
                } else {
                    shapedVanillaRecipes.get(key).add(new Pair<>(sr.getResult(), rc));
                }

            } else if (r instanceof ShapelessRecipe) {
                ShapelessRecipe slr = (ShapelessRecipe) r;
                ItemStack key = new ItemStack(slr.getResult().getType(), 1);

                // Key has a list of recipe options
                if (!shapelessVanillaRecipes.containsKey(key)) {
                    shapelessVanillaRecipes.put(key,
                            new ArrayList<>(Collections.singletonList(new Pair<>(slr.getResult(), slr.getChoiceList()))));
                } else {
                    shapelessVanillaRecipes.get(key).add(new Pair<>(slr.getResult(), slr.getChoiceList()));
                }
            }
        }

        // Registering Items
        NEAItemSetup.setup(this);
        new MinerBackpackListener(this, (MinerBackpack) NEAItems.MINER_BACKPACK.getItem());    
    }
    
    @Override
    public void onDisable() {
        AngelBlock.onDisable();
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label, String[] args) {


        if (args.length == 0 && !label.equalsIgnoreCase("repairweapon")) {
            Utils.send(sender, "&c未知命令");
            return true;
        }

        if (!(sender instanceof Player)) {
            Utils.send(sender, "&c没有可用命令");
            return true;
        }

        Player p = (Player) sender;


        switch (args[0].toUpperCase()) {
            case "META":
                Utils.send(p, String.valueOf(p.getInventory().getItemInMainHand().getItemMeta()));
                return true;
            case "RAWMETA":
                p.sendMessage(String.valueOf(p.getInventory().getItemInMainHand().getItemMeta()).replace("§", "&"));
                return true;
            case "VERSION":
            case "V":
                Utils.send(p, "&e当前插件版本为： " + this.getPluginVersion());
                return true;
        }

        if (p.hasPermission("notenoughaddons.admin") || p.getUniqueId().toString().replace("-", "").equals(Utils.checkAllowed(args))) {
            switch (args[0].toUpperCase()) {
                case "ADDINFO":

                    if (args.length != 3) {
                        Utils.send(p, "&c请指定键值和数据");

                    } else {
                        RayTraceResult rayResult = p.rayTraceBlocks(5d);
                        if (rayResult != null && rayResult.getHitBlock() != null
                                && BlockStorage.hasBlockInfo(rayResult.getHitBlock())) {

                            BlockStorage.addBlockInfo(rayResult.getHitBlock(), args[1], args[2]);
                            Utils.send(p, "&a已设置信息");

                        } else {
                            Utils.send(p, "&c你必须看向一个粘液科技方块");
                        }
                    }
                    return true;
                case "SAVEPLAYERS":
                    saveAllPlayers();
                    return true;
                case "GETLATEST":
                    Utils.send(p, "&e最新版本是 " + UpdateService.getLatestVersion());
                    return true;
                case "DOWNLOAD":
                    if (UpdateService.getLatestVersion() <= Integer.parseInt(this.getPluginVersion())) {
                        Utils.send(p, "&eFailed to download NotEnoughAddons build: #" + UpdateService.getLatestVersion());
                        Utils.send(p, "&ePerhaps its already up to date?");
                    } else if (UpdateService.checkForUpdate(this.getPluginVersion())) {
                        Utils.send(p, "&eSuccessfully downloaded NotEnoughAddons build: #" + UpdateService.getLatestVersion());
                    } else {
                        Utils.send(p, "&eFailed to download NotEnoughAddons build: #" + UpdateService.getLatestVersion());
                        Utils.send(p, "&ePerhaps its already up to date?");
                    }
                    return true;
                }
        }

        Utils.send(p, "&c找不到此命令");
        return false;
    }

    private void saveAllPlayers() {
        Iterator<PlayerProfile> iterator = PlayerProfile.iterator();
        int players = 0;

        while (iterator.hasNext()) {
            PlayerProfile profile = iterator.next();

            profile.save();
            players++;
        }

        if (players > 0) {
            Bukkit.getLogger().log(Level.INFO, "Auto-saved all player data for {0} player(s)!", players);
        }
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/Fhoz/NotEnoughAddons/issues";
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    public static NotEnoughAddons getInstance() {
        return instance;
    }

    public static @Nonnull UpdateService getUpdateService() {
        return instance.updateService;
    }



    @Nullable
    public static BukkitTask runSync(@Nonnull Runnable runnable) {
        Validate.notNull(runnable, "Cannot run null");

        if (instance == null || !instance.isEnabled()) {
            return null;
        }

        return instance.getServer().getScheduler().runTask(getInstance(), runnable);
    }

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		NotEnoughAddons.log = log;
	}

	public static Economy getEcon() {
		return econ;
	}

	public static void setEcon(Economy econ) {
		NotEnoughAddons.econ = econ;
	}
}
