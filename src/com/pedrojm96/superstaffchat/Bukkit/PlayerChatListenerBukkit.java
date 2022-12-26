package com.pedrojm96.superstaffchat.Bukkit;



import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.pedrojm96.core.CoreColor;
import com.pedrojm96.core.CoreVariables;
import com.pedrojm96.superstaffchat.AllString;
import com.pedrojm96.superstaffchat.Staff;







public class PlayerChatListenerBukkit implements Listener {
		
	
	private BukkitStaffChat plugin;

	public PlayerChatListenerBukkit(BukkitStaffChat plugin) {
		this.plugin = plugin;
		plugin.log.info("Register Listeners");
	}
	
	
	@EventHandler
		public void onPlayerCommand(PlayerCommandPreprocessEvent e){
		if(e.isCancelled()){
				return;
		 }
		 Player m = e.getPlayer();
		 String msg = e.getMessage();
		 for(Staff c : this.plugin.listChat) {
			 if( (msg.equalsIgnoreCase("/"+c.getCommand()))  || (msg.equalsIgnoreCase("/"+c.getAlias()))  ) {
				 if(m.hasPermission(c.getPermission())) {
					 if(this.plugin.toggleChat.containsKey(m.getName().toLowerCase())) {
						 if(this.plugin.toggleChat.get(m.getName().toLowerCase()).getCommand().equalsIgnoreCase(c.getCommand())) {
							 this.plugin.toggleChat.remove(m.getName().toLowerCase());
							
							 CoreColor.message(m, AllString.prefix + AllString.channel_closed.replaceAll("%chat%", c.getID()));
						 }else {
							 CoreColor.message(m, AllString.prefix + AllString.channel_closed.replaceAll("%chat%",this.plugin.toggleChat.get(m.getName().toLowerCase()).getID() ));
							 this.plugin.toggleChat.replace(m.getName().toLowerCase(), c);
						
							 CoreColor.message(m, AllString.prefix + AllString.channel_open.replaceAll("%chat%", c.getID()));
						 }
					 }else {
						 this.plugin.toggleChat.put(m.getName().toLowerCase(), c);
						
						 CoreColor.message(m, AllString.prefix + AllString.channel_open.replaceAll("%chat%",c.getID()));
					 }
				 }else {
					 CoreColor.message(m, AllString.prefix + AllString.no_permission);
				 }
				 e.setCancelled(true);
				 break;
			 }
		 }
		 
	  }
		@EventHandler
		public void onPlayerChat(AsyncPlayerChatEvent e) {
			if(e.isCancelled()){
				 return;
			 }
			 Player m = e.getPlayer();
			 String msg = e.getMessage();
			 if(!msg.startsWith("/")) {
				 if(this.plugin.toggleChat.containsKey(m.getName().toLowerCase())) {
					 Staff c = this.plugin.toggleChat.get(m.getName().toLowerCase());
					 String format = c.getFormat();
					 format = format.replaceAll("%player%", m.getName());
					 format = format.replaceAll("%world%", m.getWorld().getName().toLowerCase());
					 format = format.replaceAll("%message%", msg);
					 format = CoreVariables.replace(format, m);
					 for (Player online : Bukkit.getServer().getOnlinePlayers()) {
						
						 if(online.hasPermission(c.getPermission())) {
							 CoreColor.message(online,format );
						 }
					 }
					 e.setCancelled(true);
				 }
			 }
		}
}
