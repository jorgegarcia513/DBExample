package com.sudo.dbexample;

import com.sudo.dbexample.DBExample;
import com.sudo.dbexample.DBManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class InitUser implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerLoginEvent event) {
        DBExample instance = DBExample.getInstance();
        DBManager dbManager = instance.getDBManager();
        String username = event.getPlayer().getName();

        // If user does not exist, initialize them with a value of 0 in the table.
        if (!dbManager.userExists(username)) {
            dbManager.addUser(username);
            event.getPlayer().sendMessage("Added " + username + " to DB.");
        }
    }
}
