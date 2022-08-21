package wocplugin.wocplugin.Items;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemSmith {

    public ItemStack makeItem(Material m, String name, String desc, int amount) {

        ItemStack item = new ItemStack (m, amount);

        //Create the item's meta data (name, lore/desc text, etc.)
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        //Creates the lore
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(desc);
        im.setLore(lore);
        //Hides the vanilla Minecraft tooltip text
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        //Sets the item's metadata to the custom "im" metadata
        item.setItemMeta(im);

        return item;

    }

    public ItemStack buildWeapon(String sword) {

        Material m = Material.GOLDEN_SWORD;
        String name = new String();
        String desc = new String();

        if ( sword.toLowerCase().equals("midas") ) {
            m = Material.GOLDEN_SWORD;
            name = (ChatColor.GOLD + "" + ChatColor.BOLD + "Sword of King Midas");
            desc = (ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "The golden sword of King Midas!");
        } else if ( sword.toLowerCase().equals("excalibur") ) {
            m = Material.IRON_SWORD;
            name = (ChatColor.GOLD + "" + ChatColor.BOLD + "Sword of King Arthur");
            desc = (ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "The fantastic sword of King Arthur!");
        } else if ( sword.toLowerCase().equals("troll") ) {
            m = Material.STONE_SWORD;
            name = (ChatColor.GOLD + "" + ChatColor.BOLD + "Sword of the Troll King");
            desc = (ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "The powerful and heavy Troll King's sword!");
        }

        return makeItem(m, name, desc, 1);

    }

}
