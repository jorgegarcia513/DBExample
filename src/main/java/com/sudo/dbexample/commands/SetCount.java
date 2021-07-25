package com.sudo.dbexample.commands;

import com.sudo.dbexample.DBExample;
import com.sudo.dbexample.DBManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetCount implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        DBExample instance = DBExample.getInstance();
        DBManager dbManager = instance.getDBManager();

        if (args.length != 2) {
            sender.sendMessage("Usage: /setcount USERNAME COUNT");
            return false;
        }

        dbManager.setCount(args[0], Integer.parseInt(args[1]));
        sender.sendMessage("Set count of " + args[0] + " to " + args[1]);

        return false;
    }
}
