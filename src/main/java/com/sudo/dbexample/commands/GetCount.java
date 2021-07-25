package com.sudo.dbexample.commands;

import com.sudo.dbexample.DBExample;
import com.sudo.dbexample.DBManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GetCount implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        DBExample instance = DBExample.getInstance();
        DBManager dbManager = instance.getDBManager();

        if (args.length != 1) {
            sender.sendMessage("Usage: /getcount USERNAME");
            return false;
        }

        sender.sendMessage("Count of " + args[0] + " is " + dbManager.getCount(args[0]));

        return false;
    }
}
