package wocplugin.wocplugin.Items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack wand;
    public static ItemStack Sheepwand;
    public static ItemStack aspectofthevoid;
    public static ItemStack shortBow;
    public static ItemStack mobTester;
    public static ItemStack grapple;

    public static void init() {
        createWand();
        createSheepWand();
        createAOTV();
        createShortBow();
        enemyTest();
        createGrapple();
    }

    private static void createWand() {
        ItemStack item = new ItemStack(Material.STICK, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§1§lWand");
        List<String> lore = new ArrayList<>();
        lore.add("§7wand");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        wand = item;
    }
    private static void createSheepWand() {
        ItemStack item = new ItemStack(Material.STICK, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§1§lSheep Wand");
        List<String> lore = new ArrayList<>();
        lore.add("§7The default sheep wand");
        lore.add("§e§lRight Click:");
        lore.add("§7Sheep: Spawns a sheep!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        Sheepwand = item;
    }
    private static void createAOTV() {
        ItemStack item = new ItemStack(Material.DIAMOND_SHOVEL, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§1§lAspect of the Void");
        List<String> lore = new ArrayList<>();
        lore.add("§7The best teleportation weapon in existence");
        lore.add("§e§lRight Click:");
        lore.add("§7Teleport: Teleports you 12 blocks in the direction you are looking");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        aspectofthevoid = item;
    }
    private static void createShortBow() {
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§1§lShort Bow");
        List<String> lore = new ArrayList<>();
        lore.add("§7The best teleportation weapon in existence");
        lore.add("§e§lRight Click:");
        lore.add("§7Teleport: Teleports you 12 blocks in the direction you are looking");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        shortBow = item;
    }
    private static void enemyTest() {
        ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§1§lMob Tester");
        List<String> lore = new ArrayList<>();
        lore.add("§7Mob tester, Made for admins only");
        lore.add("§e§lRight Click:");
        lore.add("§7Spawns the defined mob to test");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        mobTester = item;
    }
    private static void createGrapple() {
        ItemStack item = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§1Grapple");
        List<String> lore = new ArrayList<>();
        lore.add("§7This item propels you around the map!");
        lore.add("§e§lRight Click Ability:");
        lore.add("§7Grapple! Pulls you along!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        grapple = item;
    }
}
