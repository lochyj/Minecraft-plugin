package wocplugin.wocplugin.CommandManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wocplugin.wocplugin.Items.Items.FieldHoe;
import wocplugin.wocplugin.Items.Items.GardenHoe;

public class Item implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command!");
            return true;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("item")) {
            player.getInventory().addItem(GardenHoe.give());
            player.getInventory().addItem(FieldHoe.give());
        }
        return true;
    }
}