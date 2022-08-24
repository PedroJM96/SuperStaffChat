package com.pedrojm96.superstaffchat.Bungee;

import com.pedrojm96.core.bungee.CoreColor;
import com.pedrojm96.core.bungee.CoreCommand;
import com.pedrojm96.core.bungee.CoreConfig;
import com.pedrojm96.superstaffchat.AllString;

import net.md_5.bungee.api.CommandSender;

public class CommandReload  extends CoreCommand{
	 
	 
	private BungeeStaffChat plugin;
	 

	public CommandReload(BungeeStaffChat plugin, String name, String[] aliases) {
		super(name, "staffchat.reload", aliases);
		this.plugin = plugin;
		plugin.log.info("Register command /staffchatreload");
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		// TODO Auto-generated method stub
		this.plugin.config = new CoreConfig(this.plugin,"config",this.plugin.log,this.plugin.getResourceAsStream("config.yml"),this.plugin.getResourceAsStream("config.yml"),true);
		this.plugin.log.seDebug(this.plugin.config.getBoolean("debug"));
		this.plugin.loadMessages();
		AllString.LoadString(this.plugin);
		this.plugin.chat = new CoreConfig(this.plugin,"chat",this.plugin.log,this.plugin.getResourceAsStream("bungee_chat.yml"),this.plugin.getResourceAsStream("bungee_chat.yml"),false);
		this.plugin.loadChat();
		CoreColor.message(sender, AllString.prefix + AllString.command_reload);
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
		return "staffchat.reload";
	}
}
