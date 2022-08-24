package com.pedrojm96.superstaffchat;

import com.pedrojm96.superstaffchat.Bukkit.BukkitStaffChat;
import com.pedrojm96.superstaffchat.Bungee.BungeeStaffChat;

public class AllString {
	public static String line;
	public static String prefix;
	public static String no_permission;
	

	public static String command_reload;
	public static String channel_open;
	public static String channel_closed;
	public static String join_staff;
	public static String online_staff;
	
	
	
	public static void LoadString(BungeeStaffChat plugin){
		line = "&6&m----------------------------------";
		prefix = plugin.getCConfig().getString("prefix");
		no_permission = plugin.getCMessages().getString("no-permission");
		command_reload = plugin.getCMessages().getString("command-reload");
		channel_open = plugin.getCMessages().getString("channel-open");
		channel_closed = plugin.getCMessages().getString("channel-closed");
		join_staff = plugin.getCMessages().getString("join-staff");
		online_staff = plugin.getCMessages().getString("online-staff");
		
	}
	
	public static void LoadString(BukkitStaffChat plugin){
		line = "&6&m----------------------------------";
		prefix = plugin.getCConfig().getString("prefix");
		no_permission = plugin.getCMessages().getString("no-permission");
		command_reload = plugin.getCMessages().getString("command-reload");
		channel_open = plugin.getCMessages().getString("channel-open");
		channel_closed = plugin.getCMessages().getString("channel-closed");
		join_staff = plugin.getCMessages().getString("join-staff");
		online_staff = plugin.getCMessages().getString("online-staff");
		
	}
}
