package com.sudo.dbexample;

import com.sudo.dbexample.commands.Add;
import com.sudo.dbexample.commands.GetCount;
import com.sudo.dbexample.commands.SetCount;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class DBExample extends JavaPlugin {

    private static DBExample instance;

    private DBManager dbManager;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        config = getConfig();

        getCommand("getcount").setExecutor(new GetCount());
        getCommand("add").setExecutor(new Add());
        getCommand("setcount").setExecutor(new SetCount());

        getServer().getPluginManager().registerEvents(new InitUser(), this);

        initDBManager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static DBExample getInstance() {
        return instance;
    }

    public DBManager getDBManager() {
        return this.dbManager;
    }

    public void initDBManager() {
        if (config.getBoolean("settings.database.enabled")) {
            String host  = config.getString("settings.database.host");
            String port  = config.getString("settings.database.port");
            String username  = config.getString("settings.database.username");
            String password  = config.getString("settings.database.password");
            String database  = config.getString("settings.database.database-name");

            this.dbManager = new DBManager(host, port, username, password, database);
            this.dbManager.initDB();
            getServer().getConsoleSender().sendMessage("Database successfully initiliazed");
            return;
        }
        getServer().getConsoleSender().sendMessage("MySQL Database option disabled in config.yml");
    }
}
