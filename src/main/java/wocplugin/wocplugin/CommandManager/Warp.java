package wocplugin.wocplugin.CommandManager;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command!");
            return true;
        }

        Player player = (Player) sender;
        Location forge = new Location(player.getWorld(),-344.5, 133, -104.5);
        Location hub = new Location(player.getWorld(),-255.5, 135, -175.5);
        Location mines = new Location(player.getWorld(),-309.5, 140, -175.5);
        if (command.getName().equalsIgnoreCase("warp")) {
            if (args[0].equalsIgnoreCase("forge")) {
                player.teleport(forge);
            } else if (args[0].equalsIgnoreCase("hub")) {
                player.teleport(hub);
            } else if (args[0].equalsIgnoreCase("mines")) {
                player.teleport(mines);
            }
        }
        return true;
    }
}
