package com.pedrojm96.superstaffchat.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.pedrojm96.core.CoreConfig;
import com.pedrojm96.core.CoreLog;
import com.pedrojm96.core.CorePlugin;
import com.pedrojm96.core.CoreSpigotUpdater;
import com.pedrojm96.core.command.CoreCommands;
import com.pedrojm96.superstaffchat.AllString;
import com.pedrojm96.superstaffchat.Staff;






public class BukkitStaffChat extends JavaPlugin implements CorePlugin {
	
	public BukkitStaffChat plugin;
	public CoreConfig config;
	public CoreConfig messages;
	public CoreConfig chat;
	public Map<String, Staff> toggleChat = new HashMap<String, Staff>();
	public List<Staff> listChat = new ArrayList<Staff>();
	
	public CoreLog log;
	
	
	public void onEnable() {
		plugin = this;
		log = new CoreLog(this,CoreLog.Color.RED);
		log.line();
		log.info("SuperStaffChat v" + getDescription().getVersion() + " is being loaded...");
		log.info("Plugin Create by PedroJM96.");
		getServer().getPluginManager().registerEvents(new PlayerChatListenerBukkit(this), this);
		CoreCommands.registerCommand(new CommandReload(this), this);
		CoreCommands.registerCommand(new CommandStaffOnline(this), this);
		log.info("Loading configuration...");
		config = new CoreConfig(this,"config",log,this.getResource("config.yml"),true);
	    loadMessages();
	    AllString.LoadString(this);
	    chat = new CoreConfig(this,"chat",log,this.getResource("bukkit_chat.yml"),false);
	    loadChat();
	    checkForUpdates();
	    @SuppressWarnings("unused")
		Metrics metrics = new Metrics(this,	15364);
	    log.line();
	}
	

	public void loadChat() {
		listChat= new ArrayList<Staff>();
		Set<String> key = chat.getKeys(false);
		for (String nodomenu : key) {
			Staff c = new Staff(chat.getConfigurationSection(nodomenu).getName());
			c.setCommand(chat.getString(nodomenu+".command"));
			c.setAlias(chat.getString(nodomenu+".alias"));
			c.setPermission(chat.getString(nodomenu+".permission"));
			c.setFormat(chat.getString(nodomenu+".format"));
			listChat.add(c);
		}
	}
	
	public void loadMessages(){
		String m = config.getString("messages");
		switch(m.toUpperCase()){
		case "EN":
			this.messages = new CoreConfig(this,"messages_EN",this.log,this.getResource("messages_EN.yml"),true);
			break;
		case "ES":
			this.messages = new CoreConfig(this,"messages_ES",this.log,this.getResource("messages_ES.yml"),true);
			break;
		default:
			this.messages = new CoreConfig(this,"messages_EN",this.log,this.getResource("messages_EN.yml"),true);
			break;
		}
	}
	

	
	public void checkForUpdates() {
		if(config.getBoolean("update-check")){
			new BukkitRunnable() {
				@Override
				public void run() {
					CoreSpigotUpdater updater = new CoreSpigotUpdater(plugin, 54423);
		        	try {
		                if (updater.checkForUpdates()) {
		                	log.alert("An update was found! for SuperStaffChat. Please update to recieve latest version. download: " + updater.getResourceURL());
		                }	
		            } catch (Exception e) {
		            	
		            	log.error("Failed to check for a update on spigot.");
		            }
				}
        		
        	}.runTask(this);
        	
        	
        } 
    }
	
	
	public CoreConfig getCConfig(){
		return this.config;
	}
	public CoreConfig getCMessages(){
		return this.messages;
	}
	
	@Override
	public CoreLog getLog() {
		// TODO Auto-generated method stub
		return this.log;
	}


	@Override
	public JavaPlugin getInstance() {
		// TODO Auto-generated method stub
		return this.plugin;
	}
	
	
}
