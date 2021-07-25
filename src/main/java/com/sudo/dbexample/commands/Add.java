package com.sudo.dbexample.commands;

import com.sudo.dbexample.DBExample;
import com.sudo.dbexample.DBManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Add implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        DBExample instance = DBExample.getInstance();
        DBManager dbManager = instance.getDBManager();

        if (args.length != 1) {
            sender.sendMessage("Usage: /add USERNAME");
            return false;
        }

        dbManager.addCount(args[0]);
        sender.sendMessage("Added 1 to count of " + args[0]);

        return false;
    }
}
