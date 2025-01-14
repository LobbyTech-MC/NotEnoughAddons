package me.fhoz.notenoughaddons.utils;

import org.bukkit.Material;

import dev.j3fftw.extrautils.utils.LoreBuilderDynamic;
import io.github.bakedlibs.dough.items.CustomItemStack;
import io.github.bakedlibs.dough.skins.PlayerHead;
import io.github.bakedlibs.dough.skins.PlayerSkin;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.fhoz.notenoughaddons.machines.BudgetDustFabricator;

/**
 * Specifies all plugin items
 */
public class NEAItems {

    private NEAItems() {
    }

    // Machines
    public static final SlimefunItemStack BUDGET_DUST_FABRICATOR_NEA = new SlimefunItemStack("BUDGET_DUST_FABRICATOR_NEA",
        Material.CRACKED_STONE_BRICKS,
        "&6经济型矿粉制造机",
        "",
        "&7一个经济型多合一机器,",
        "&7可以直接使用圆石或者其他变种来获取矿粉",
        "",
        "&7&o为懒狗制作的机器...",
        "",
        LoreBuilder.machine(MachineTier.GOOD, MachineType.MACHINE),
        LoreBuilder.speed(1),
        LoreBuilderDynamic.powerBuffer(BudgetDustFabricator.CAPACITY),
        LoreBuilderDynamic.powerPerTick(BudgetDustFabricator.ENERGY_CONSUMPTION)
    );

    // public static final SlimefunItemStack FLYING_BUBBLE = new SlimefunItemStack("FLYING_BUBBLE",
    //     Material.CRYING_OBSIDIAN,
    //     "&6Flying Bubble",
    //     "",
    //     "&f Creative Flight within an 45 block area",
    //     "",
    //     LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
    //     LoreBuilder.powerPerSecond(128)
    // );

    public static final SlimefunItemStack ANGEL_BLOCK = new SlimefunItemStack("ANGEL_BLOCK",
        Material.FEATHER,
        "&6天使方块",
        "",
        "&7在你脚下放置一个方块",
        "&7当你在空中时非常好用",
        "",
        LoreBuilder.RIGHT_CLICK_TO_USE
    );


    public static final SlimefunItemStack MINER_BACKPACK = new SlimefunItemStack("MINER_BACKPACK",
        new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromHashCode("8dcc6eb40f3bada41e4339888d6d207437598bdbd175c2e731191d5a9a42d3c8"))),
        "&6矿工背包",
        "",
        "&f可以存储矿物",
        "&f在捡起矿物时自动存入",
        "&f在物品栏中时生效",
        "",
        "&7大小: &e54 (大箱子)",
        "",
        "&7ID: <ID>",
        "",
        LoreBuilder.RIGHT_CLICK_TO_OPEN
    );

    // TERRARIA

    // TOOLS

    // public static final SlimefunItemStack SHORTSWORD_COPPER = new SlimefunItemStack("SHORTSWORD_COPPER", 
    //     Material.WOODEN_SWORD,
    //     "&fCopper Shortsword",
    //     TerrariaUtils.getDMG(CopperShortsword.getDMG()),
    //     TerrariaUtils.getCC(CopperShortsword.getCC()),
    //     TerrariaUtils.useTimeConv(CopperShortsword.getUseTime()),
    //     TerrariaUtils.kbConv(CopperShortsword.getKB())
    // );

    // public static final SlimefunItemStack SHORTSWORD_TIN = new SlimefunItemStack("SHORTSWORD_TIN", 
    //     Material.WOODEN_SWORD,
    //     "&fTin Shortsword",
    //     TerrariaUtils.getDMG(TinShortsword.getDMG()),
    //     TerrariaUtils.getCC(TinShortsword.getCC()),
    //     TerrariaUtils.useTimeConv(TinShortsword.getUseTime()),
    //     TerrariaUtils.kbConv(TinShortsword.getKB())
    // );

    // public static final SlimefunItemStack SHORTSWORD_IRON = new SlimefunItemStack("SHORTSWORD_IRON", 
    //     Material.WOODEN_SWORD,
    //     "&fIron Shortsword",
    //     TerrariaUtils.getDMG(IronShortsword.getDMG()),
    //     TerrariaUtils.getCC(IronShortsword.getCC()),
    //     TerrariaUtils.useTimeConv(IronShortsword.getUseTime()),
    //     TerrariaUtils.kbConv(IronShortsword.getKB())
    // );

    // public static final SlimefunItemStack SHORTSWORD_LEAD = new SlimefunItemStack("SHORTSWORD_LEAD", 
    //     Material.WOODEN_SWORD,
    //     "&fLead Shortsword",
    //     TerrariaUtils.getDMG(LeadShortsword.getDMG()),
    //     TerrariaUtils.getCC(LeadShortsword.getCC()),
    //     TerrariaUtils.useTimeConv(LeadShortsword.getUseTime()),
    //     TerrariaUtils.kbConv(LeadShortsword.getKB())
    // );

    // public static final SlimefunItemStack SHORTSWORD_SILVER = new SlimefunItemStack("SHORTSWORD_SILVER", 
    //     Material.WOODEN_SWORD,
    //     "&fSilver Shortsword",
    //     TerrariaUtils.getDMG(SilverShortsword.getDMG()),
    //     TerrariaUtils.getCC(SilverShortsword.getCC()),
    //     TerrariaUtils.useTimeConv(SilverShortsword.getUseTime()),
    //     TerrariaUtils.kbConv(SilverShortsword.getKB())
    // );

    // public static final SlimefunItemStack SHORTSWORD_TUNGSTEN = new SlimefunItemStack("SHORTSWORD_TUNGSTEN", 
    //     Material.WOODEN_SWORD,
    //     "&fTungsten Shortsword",
    //     TerrariaUtils.getDMG(TungstenShortsword.getDMG()),
    //     TerrariaUtils.getCC(TungstenShortsword.getCC()),
    //     TerrariaUtils.useTimeConv(TungstenShortsword.getUseTime()),
    //     TerrariaUtils.kbConv(TungstenShortsword.getKB())
    // );

    // public static final SlimefunItemStack SHORTSWORD_GOLD = new SlimefunItemStack("SHORTSWORD_GOLD", 
    //     Material.WOODEN_SWORD,
    //     "&fGold Shortsword",
    //     TerrariaUtils.getDMG(GoldShortsword.getDMG()),
    //     TerrariaUtils.getCC(GoldShortsword.getCC()),
    //     TerrariaUtils.useTimeConv(GoldShortsword.getUseTime()),
    //     TerrariaUtils.kbConv(GoldShortsword.getKB())
    // );

    // public static final SlimefunItemStack SHORTSWORD_PLATINUM = new SlimefunItemStack("SHORTSWORD_PLATINUM", 
    //     Material.WOODEN_SWORD,
    //     "&fPlatinum Shortsword",
    //     TerrariaUtils.getDMG(PlatinumShortsword.getDMG()),
    //     TerrariaUtils.getCC(PlatinumShortsword.getCC()),
    //     TerrariaUtils.useTimeConv(PlatinumShortsword.getUseTime()),
    //     TerrariaUtils.kbConv(PlatinumShortsword.getKB())
    // );
}
