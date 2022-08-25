package wocplugin.wocplugin.CommandManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wocplugin.wocplugin.Items.ItemManager;

public class Wand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command!");
            return true;
        }

        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("wand")) {
            if (args[0].equalsIgnoreCase("wand")) {
                player.getInventory().addItem(ItemManager.wand);
            } else if (args[0].equalsIgnoreCase("sheep")) {
                player.getInventory().addItem(ItemManager.Sheepwand);
            } else if (args[0].equalsIgnoreCase("tp")) {
                player.getInventory().addItem(ItemManager.aspectofthevoid);
            } else if (args[0].equalsIgnoreCase("bow")) {
                player.getInventory().addItem(ItemManager.shortBow);
            } else if (args[0].equalsIgnoreCase("mob")) {
                player.getInventory().addItem(ItemManager.mobTester);
            } else if (args[0].equalsIgnoreCase("grapple")) {
                player.getInventory().addItem(ItemManager.grapple);
            }
        }
        return true;
    }
}
