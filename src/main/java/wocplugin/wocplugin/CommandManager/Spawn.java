package wocplugin.wocplugin.CommandManager;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import wocplugin.wocplugin.Entities.Ball;
import wocplugin.wocplugin.Entities.Ghoul;

public class Spawn implements CommandExecutor {

    public static Ball instance = null;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
//        if (!(sender instanceof Player)) {
//            sender.sendMessage("Only players can run this command!");
//            return true;
//        }

        //Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("spawn")) {
            if (args[0].equalsIgnoreCase("ghoul")) {
                Location location = new Location(Bukkit.getServer().getWorlds().get(0), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
                new Ghoul(location);
                return true;
            } else if (args[0].equalsIgnoreCase("ball")) {
                Location location = new Location(Bukkit.getServer().getWorlds().get(0), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
                instance = new Ball(location);
                return true;
            }
        }
        return true;
    }
}
