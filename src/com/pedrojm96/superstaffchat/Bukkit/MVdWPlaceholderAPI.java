package com.pedrojm96.superstaffchat.Bukkit;


import org.bukkit.Bukkit;

import com.pedrojm96.superstaffchat.Staff;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;

public class MVdWPlaceholderAPI {

	public static void register(BukkitStaffChat plugin) {
		PlaceholderAPI.registerPlaceholder(plugin.getInstance(), "superstaffchat_chat", new PlaceholderReplacer()  {  
			
			@Override
			public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
				
				//%superstaffchat_chat%
				if(e.getPlaceholder().equalsIgnoreCase("chat")) {
					if(plugin.toggleChat.containsKey(e.getPlayer().getName().toLowerCase())) {
						 Staff c = plugin.toggleChat.get(e.getPlayer().getName().toLowerCase());
						 return String.valueOf(c.getID()); 
					}
					return " ";
				}
				//%superstaffchat_chat_<playe>r%
				if(e.getPlaceholder().contains("_")) {
					String identifier_player = e.getPlaceholder().split("_")[1].trim();
					if(plugin.toggleChat.containsKey(identifier_player.toLowerCase())) {
						 Staff c = plugin.toggleChat.get(identifier_player.toLowerCase());
						 return String.valueOf(c.getID()); 
					}
					return " ";
				}
				return null;
			}
				
		});
		
		PlaceholderAPI.registerPlaceholder(plugin.getInstance(), "superstaffchat_status", new PlaceholderReplacer()  {  
			
			@Override
			public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
				//%superstaffchat_status_<playe>r%
				if(e.getPlaceholder().contains("_")) {
					String identifier_player = e.getPlaceholder().split("_")[1].trim();
					if(Bukkit.getPlayer(identifier_player)!=null) {
						return String.valueOf("Online"); 
					}
					return String.valueOf("Offline"); 
				}
				return null;
			}
		
		});
	}
	
	
}
