package wocplugin.wocplugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public class Util {

    public static void interactCancel(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null) {
            if (event.getClickedBlock().getType() == Material.ANVIL || event.getClickedBlock().getType() == Material.CHIPPED_ANVIL || event.getClickedBlock().getType() == Material.DAMAGED_ANVIL) {
                event.setCancelled(true);
            }
            else if (event.getClickedBlock().getType() == Material.IRON_TRAPDOOR || event.getClickedBlock().getType() == Material.ACACIA_TRAPDOOR || event.getClickedBlock().getType() == Material.BIRCH_TRAPDOOR || event.getClickedBlock().getType() == Material.DARK_OAK_TRAPDOOR || event.getClickedBlock().getType() == Material.JUNGLE_TRAPDOOR || event.getClickedBlock().getType() == Material.SPRUCE_TRAPDOOR) {
                event.setCancelled(true);
            }
            else if (event.getClickedBlock().getType() == Material.ACACIA_BUTTON || event.getClickedBlock().getType() == Material.STONE_BUTTON || event.getClickedBlock().getType() == Material.BIRCH_BUTTON || event.getClickedBlock().getType() == Material.DARK_OAK_BUTTON || event.getClickedBlock().getType() == Material.JUNGLE_BUTTON || event.getClickedBlock().getType() == Material.OAK_BUTTON || event.getClickedBlock().getType() == Material.SPRUCE_BUTTON) {
                event.setCancelled(true);
            }
        }
    }

    public static String convertToInvisibleString(String s) {
        String hidden = "";
        for (char c : s.toCharArray()) hidden += ChatColor.COLOR_CHAR+""+c;
        return hidden;
    }

    public static void sendPlayerMessage(Player player, String message) {
        player.sendMessage(message);
    }
}
