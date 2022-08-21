package wocplugin.wocplugin.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class cave_loot_chest {



    public static boolean openGUI(Player player) {

        Inventory inventory = Bukkit.createInventory(player, 27, "Cave Loot Chest");

        ItemStack filler = new ItemStack(Material.AIR, 1);

        // Make a list of Loot items
        List<ItemStack> loot = new ArrayList<>();
        loot.add(new ItemStack(Material.EMERALD, 1));
        loot.add(new ItemStack(Material.EMERALD, 2));
        loot.add(new ItemStack(Material.EMERALD, 3));
        loot.add(new ItemStack(Material.COBWEB, 1));
        loot.add(new ItemStack(Material.COBWEB, 1));
        loot.add(new ItemStack(Material.COBWEB, 1));
        loot.add(new ItemStack(Material.COBWEB, 1));
        loot.add(new ItemStack(Material.COBWEB, 1));
        loot.add(new ItemStack(Material.COBWEB, 1));
        loot.add(new ItemStack(Material.COBWEB, 1));
        loot.add(new ItemStack(Material.COMPASS, 1));
        loot.add(new ItemStack(Material.FISHING_ROD, 1));
        loot.add(new ItemStack(Material.IRON_PICKAXE, 1));
        loot.add(new ItemStack(Material.GOLDEN_SWORD, 1));

        loot.add(filler);
        loot.add(filler);
        loot.add(filler);
        loot.add(filler);
        loot.add(filler);
        loot.add(filler);
        loot.add(filler);
        loot.add(filler);
        loot.add(filler);
        loot.add(filler);




        int slots = 27;

        for (int i = 1; i < slots; i++) {
            // Random number from 1 to 24
            int random = (int) (Math.random() * loot.size());
            inventory.setItem(i - 1, loot.get(random));

        }

        //
        // - - - - - - - - -
        // - - - - - - - - -
        // - - - - - - - - -
        //

        player.openInventory(inventory);
        return true;
    }
}
