package com.pedrojm96.superstaffchat.Bungee;

import java.util.Iterator;

import com.pedrojm96.core.bungee.CoreColor;
import com.pedrojm96.core.bungee.CoreCommand;
import com.pedrojm96.superstaffchat.AllString;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class CommandStaffOnline  extends CoreCommand{
	 
	 
	 public CommandStaffOnline(BungeeStaffChat plugin, String name, String... aliases)
	  {
	    super(name, "staffchat.staff", aliases);
	     plugin.log.info("Register command /staffonline");
	  }
	 
	 

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		// TODO Auto-generated method stub
		
	
	    String permission = BungeeStaffChat.getIntence().config.getString("staff-online-permission");
	    for (Iterator<ProxiedPlayer> iterator1 = ProxyServer.getInstance().getPlayers().iterator(); iterator1.hasNext();) {
	    	ProxiedPlayer online = (ProxiedPlayer)iterator1.next();
			if(online.hasPermission(permission)) {
				CoreColor.message(sender, AllString.online_staff.replaceAll("%server%", online.getServer().getInfo().getName().toLowerCase()).replaceAll("%player%", online.getDisplayName()));
			}
	    }
    	return;	
	}

	@Override
	public String getErrorNoPermission() {
		// TODO Auto-generated method stub
		return AllString.prefix + AllString.no_permission;
	}

	@Override
	public String getPerm() {
		// TODO Auto-generated method stub
		return "staffchat.staff";
	}
}
