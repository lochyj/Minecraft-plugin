package wocplugin.wocplugin;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import wocplugin.wocplugin.CommandManager.*;
import wocplugin.wocplugin.handlers.*;

import com.mysql.cj.xdevapi.Statement;

import java.sql.Connection;
import java.sql.DriverManager;


public final class WOCPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {


        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=master;user=sa";

        try {
            // Load SQL Server JDBC driver and establish connection.
            System.out.print("Connecting to SQL Server ... ");
            try (Connection connection = DriverManager.getConnection(connectionUrl)) {
                System.out.println("Done.");
            }
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }

        // Register events
        getServer().getPluginManager().registerEvents(new EventHandler(this), this);
        getServer().getPluginManager().registerEvents(this, this);

        getCommand("fly").setExecutor(new Fly());
        getCommand("item").setExecutor(new Item());
    }


}
