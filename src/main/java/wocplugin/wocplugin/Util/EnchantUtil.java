package wocplugin.wocplugin.Util;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EnchantUtil {
    public static ItemStack addEnchantList(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.addAll(meta.getLore());
        lore.add(Util.convertToInvisibleString("%e start%"));
        lore.add(Util.convertToInvisibleString("%e end%"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static void addEnchant(ItemStack item, String enchant, int lvl) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        List<String> newLore = new ArrayList<>();
        List<String> lore = new ArrayList<>();
        newLore.add(enchant + " " + lvl + "\r\n");

        List<Object> metaArry = List.of(meta.getLore().toArray());
        for (int i = 0; i < metaArry.size(); i++) {
            if (metaArry.get(i).equals(Util.convertToInvisibleString("%e start%"))) {
                lore.add(i + 1, ChatColor.GRAY + enchant + " " + lvl);
            }
        }
    }
}
