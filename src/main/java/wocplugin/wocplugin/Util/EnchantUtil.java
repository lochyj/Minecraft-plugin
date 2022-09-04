package wocplugin.wocplugin.Util;

import org.bukkit.Bukkit;
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

    public static ItemStack addEnchant(ItemStack item, String enchant, int lvl) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        List<String> newLore = new ArrayList<>();

        List<Object> metaArry = List.of(meta.getLore().toArray());
        for (int i = 0; i < metaArry.size(); i++) {
            newLore.add((String) metaArry.get(i));
            if (metaArry.get(i).equals(Util.convertToInvisibleString("%e start%"))) {
                newLore.add(i+1, ChatColor.GRAY + enchant + " " + lvl);
                i = i + 2;
            }
        }
        Bukkit.getLogger().info("Set new lore");
        meta.setLore(newLore);
        item.setItemMeta(meta);
        return item;
    }
}
