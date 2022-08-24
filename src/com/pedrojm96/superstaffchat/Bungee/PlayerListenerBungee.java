package com.pedrojm96.superstaffchat.Bungee;

import java.util.Iterator;

import com.pedrojm96.core.bungee.CoreColor;
import com.pedrojm96.superstaffchat.AllString;
import com.pedrojm96.superstaffchat.Staff;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerListenerBungee implements Listener{

	@EventHandler
	public void onPlayerCommand(ChatEvent e) {
		if(e.isCancelled()){
			return;
		} 
		ProxiedPlayer m = (ProxiedPlayer)e.getSender();
		 String msg = e.getMessage();
		 for(Staff c : BungeeStaffChat.getIntence().listChat) {
			 if( (msg.equalsIgnoreCase("/"+c.getCommand()))  || (msg.equalsIgnoreCase("/"+c.getAlias()))  ) {
				 if(m.hasPermission(c.getPermission())) {
					 if(BungeeStaffChat.getIntence().toggleChat.containsKey(m.getName())) {
						 if(BungeeStaffChat.getIntence().toggleChat.get(m.getName()).getCommand().equalsIgnoreCase(c.getCommand())) {
							 BungeeStaffChat.getIntence().toggleChat.remove(m.getName());
							 CoreColor.message(m, AllString.prefix + AllString.channel_closed.replaceAll("%chat%", c.getID()));
						 }else {
							 CoreColor.message(m, AllString.prefix + AllString.channel_closed.replaceAll("%chat%",BungeeStaffChat.getIntence().toggleChat.get(m.getName()).getID() ));
							 BungeeStaffChat.getIntence().toggleChat.replace(m.getName(), c);
							 CoreColor.message(m, AllString.prefix + AllString.channel_open.replaceAll("%chat%", c.getID()));
						 }
					 }else {
						 BungeeStaffChat.getIntence().toggleChat.put(m.getName(), c);
						 CoreColor.message(m, AllString.prefix + AllString.channel_open.replaceAll("%chat%", c.getID()));
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
	public void onPlayerChat(ChatEvent e) {
		 if(e.isCancelled()){
			 return;
		 }
		 ProxiedPlayer m = (ProxiedPlayer)e.getSender();
		 String msg = e.getMessage();
		 if(!msg.startsWith("/")) {
			 if(BungeeStaffChat.getIntence().toggleChat.containsKey(m.getName())) {
				 Staff c = BungeeStaffChat.getIntence().toggleChat.get(m.getName());
				 String format = c.getFormat();
				 format = format.replaceAll("%player%", m.getName());
				 format = format.replaceAll("%server%", m.getServer().getInfo().getName().toLowerCase());
				 format = format.replaceAll("%message%", msg);
				 for (Iterator<ProxiedPlayer> iterator1 = ProxyServer.getInstance().getPlayers().iterator(); iterator1.hasNext();) {
					 ProxiedPlayer online = (ProxiedPlayer)iterator1.next();
					 if(online.hasPermission(c.getPermission())) {
						 CoreColor.message(online,format );
					 }
				 }
				 e.setCancelled(true);
			 }
		 }
		 
		 
	}
	
	
	@EventHandler
	public void onStaffJoin(PostLoginEvent e) {
		if(BungeeStaffChat.getIntence().config.getBoolean("join-staff-messages")) {
			ProxiedPlayer player = e.getPlayer();
			String permission = BungeeStaffChat.getIntence().config.getString("staff-join-permission");
			if(player.hasPermission(permission)) {
				for (Iterator<ProxiedPlayer> iterator1 = ProxyServer.getInstance().getPlayers().iterator(); iterator1.hasNext();) {
					ProxiedPlayer online = (ProxiedPlayer)iterator1.next();
					if(online.hasPermission(permission)) {
						CoreColor.message(online, AllString.join_staff.replaceAll("%player%", player.getDisplayName()));
					}
				}
			}
		}
		
	}
	
	
}
