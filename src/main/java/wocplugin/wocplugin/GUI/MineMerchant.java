package wocplugin.wocplugin.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MineMerchant {
    public static Inventory inventory = Bukkit.createInventory(null, 27, "Mine Merchant");

    public static int [] prices = new int[]{10, 7, 1, 4, 1, 2, 3, 2, 1, 1, 2};

    public static Inventory Give = Bukkit.createInventory(null, 27, "Mine Merchant");

    public static void init(){
        Give.setItem(0, new ItemStack(Material.GOLDEN_PICKAXE, 1));
        Give.setItem(1, new ItemStack(Material.IRON_PICKAXE, 1));
        Give.setItem(2, new ItemStack(Material.COAL, 3));
        Give.setItem(3, new ItemStack(Material.DIAMOND, 1));
        Give.setItem(4, new ItemStack(Material.LAPIS_LAZULI, 3));
        Give.setItem(5, new ItemStack(Material.IRON_INGOT, 2));
        Give.setItem(6, new ItemStack(Material.GOLD_INGOT, 1));
        Give.setItem(7, new ItemStack(Material.COPPER_INGOT, 2));
        Give.setItem(8, new ItemStack(Material.RAW_IRON, 1));
        // -
        Give.setItem(9, new ItemStack(Material.RAW_COPPER, 1));
        Give.setItem(10, new ItemStack(Material.RAW_GOLD, 1));
    }

    public static boolean openGUI(Player player) {

        init();

        ItemStack filler = new ItemStack(Material.AIR, 1);

        List<String> lore_10 = new ArrayList<>();lore_10.add("§6Costs 10 coins");
        List<String> lore_7 = new ArrayList<>();lore_7.add("§6Costs 7 coins");
        List<String> lore_4 = new ArrayList<>();lore_4.add("§6Costs 4 coins");
        List<String> lore_3 = new ArrayList<>();lore_3.add("§6Costs 3 coins");
        List<String> lore_2 = new ArrayList<>();lore_2.add("§6Costs 2 coins");
        List<String> lore_1 = new ArrayList<>();lore_1.add("§6Costs 1 coins");


        ItemStack Golden_pickaxe = new ItemStack(Material.GOLDEN_PICKAXE, 1);
        ItemMeta Golden_pickaxe_meta = Golden_pickaxe.getItemMeta();
        Golden_pickaxe_meta.setLore(lore_10);
        Golden_pickaxe.setItemMeta(Golden_pickaxe_meta);

        ItemStack Iron_pickaxe = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemMeta Iron_pickaxe_meta = Iron_pickaxe.getItemMeta();
        Iron_pickaxe_meta.setLore(lore_7);
        Iron_pickaxe.setItemMeta(Iron_pickaxe_meta);

        ItemStack Coal = new ItemStack(Material.COAL, 3);
        ItemMeta Coal_meta = Coal.getItemMeta();
        Coal_meta.setLore(lore_1);
        Coal.setItemMeta(Coal_meta);

        ItemStack Diamond = new ItemStack(Material.DIAMOND, 1);
        ItemMeta Diamond_meta = Diamond.getItemMeta();
        Diamond_meta.setLore(lore_4);
        Diamond.setItemMeta(Diamond_meta);

        ItemStack Lapis = new ItemStack(Material.LAPIS_LAZULI, 3);
        ItemMeta Lapis_meta = Lapis.getItemMeta();
        Lapis_meta.setLore(lore_1);
        Lapis.setItemMeta(Lapis_meta);

        ItemStack Iron = new ItemStack(Material.IRON_INGOT, 2);
        ItemMeta Iron_meta = Iron.getItemMeta();
        Iron_meta.setLore(lore_2);
        Iron.setItemMeta(Iron_meta);

        ItemStack Gold = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta Gold_meta = Gold.getItemMeta();
        Gold_meta.setLore(lore_3);
        Gold.setItemMeta(Gold_meta);

        ItemStack Copper = new ItemStack(Material.COPPER_INGOT, 2);
        ItemMeta Copper_meta = Copper.getItemMeta();
        Copper_meta.setLore(lore_2);
        Copper.setItemMeta(Copper_meta);

        ItemStack Raw_iron = new ItemStack(Material.RAW_IRON, 1);
        ItemMeta Raw_iron_meta = Raw_iron.getItemMeta();
        Raw_iron_meta.setLore(lore_1);
        Raw_iron.setItemMeta(Raw_iron_meta);

        ItemStack Raw_copper = new ItemStack(Material.RAW_COPPER, 1);
        ItemMeta Raw_copper_meta = Raw_copper.getItemMeta();
        Raw_copper_meta.setLore(lore_1);
        Raw_copper.setItemMeta(Raw_copper_meta);

        ItemStack Raw_gold = new ItemStack(Material.RAW_GOLD, 1);
        ItemMeta Raw_gold_meta = Raw_gold.getItemMeta();
        Raw_gold_meta.setLore(lore_2);
        Raw_gold.setItemMeta(Raw_gold_meta);

        inventory.setItem(0, Golden_pickaxe);
        inventory.setItem(1, Iron_pickaxe);
        inventory.setItem(2, Coal);
        inventory.setItem(3, Diamond);
        inventory.setItem(4, Lapis);
        inventory.setItem(5, Iron);
        inventory.setItem(6, Gold);
        inventory.setItem(7, Copper);
        inventory.setItem(8, Raw_iron);
        // -
        inventory.setItem(9, Raw_copper);
        inventory.setItem(10, Raw_gold);
        inventory.setItem(11, filler);
        inventory.setItem(12, filler);
        inventory.setItem(13, filler);
        inventory.setItem(14, filler);
        inventory.setItem(15, filler);
        inventory.setItem(16, filler);
        inventory.setItem(17, filler);
        // -
        inventory.setItem(18, filler);
        inventory.setItem(19, filler);
        inventory.setItem(20, filler);
        inventory.setItem(21, filler);
        inventory.setItem(22, filler);
        inventory.setItem(23, filler);
        inventory.setItem(24, filler);
        inventory.setItem(25, filler);
        inventory.setItem(26, filler);

        player.openInventory(inventory);
        return true;
    }
}
