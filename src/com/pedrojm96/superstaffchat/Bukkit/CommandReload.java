package com.pedrojm96.superstaffchat.Bukkit;



import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pedrojm96.core.CoreColor;
import com.pedrojm96.core.CoreConfig;
import com.pedrojm96.core.command.CorePluginCommand;
import com.pedrojm96.superstaffchat.AllString;


public class CommandReload extends CorePluginCommand{


	private BukkitStaffChat plugin;

	public CommandReload(BukkitStaffChat plugin) {
		this.plugin = plugin;
		plugin.log.info("Register command /staffchatreload");
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, String command, String[] args) {

		if (sender instanceof Player) {
			boolean mc18 = Bukkit.getBukkitVersion().split("-")[0].contains("1.8");
			boolean mc17 = Bukkit.getBukkitVersion().split("-")[0].contains("1.7");
			if(mc18 || mc17){
				 ((Player)sender).playSound(((Player)sender).getLocation(), Sound.valueOf("CLICK"), 1.0F, 1.0F);
			}else{
				 ((Player)sender).playSound(((Player)sender).getLocation(), Sound.valueOf("UI_BUTTON_CLICK"), 1.0F, 1.0F);
			}
	       
		}
		
		if (!sender.hasPermission("staffchat.reload")){
			CoreColor.message((Player)sender, AllString.prefix + AllString.no_permission);
			return true;
		}
		this.plugin.config = new CoreConfig(this.plugin,"config",this.plugin.log,this.plugin.getResource("config.yml"),true);
		this.plugin.loadMessages();
		AllString.LoadString(this.plugin);
		this.plugin.chat = new CoreConfig(this.plugin,"chat",this.plugin.log,this.plugin.getResource("bukkit_chat.yml"),false);
		this.plugin.loadChat();
		CoreColor.message((Player)sender, AllString.prefix + AllString.command_reload);
		return true;
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


	@Override
	public List<String> onCustomTabComplete(CommandSender sender, List<String> list, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "staffchatreload";
	}


	@Override
	public List<String> getAliases() {
		// TODO Auto-generated method stub
		return Arrays.asList("screload");
	}


	@Override
	public String getUsage() {
		// TODO Auto-generated method stub
		return "/staffchatreload";
	}


	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "SuperStaffChat staffchatreload command.";
	}
	
}
