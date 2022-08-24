package com.pedrojm96.superstaffchat.Bungee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bstats.bungeecord.Metrics;

import com.pedrojm96.core.bungee.CoreConfig;
import com.pedrojm96.core.bungee.CoreLog;
import com.pedrojm96.core.bungee.CoreSpigotUpdater;
import com.pedrojm96.superstaffchat.AllString;
import com.pedrojm96.superstaffchat.Staff;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeStaffChat extends Plugin{
	
	private static BungeeStaffChat plugin;
	public CoreConfig config;
	public CoreConfig messages;
	public CoreConfig chat;
	public CoreLog log;
	public Map<String, Staff> toggleChat = new HashMap<String, Staff>();
	public List<Staff> listChat = new ArrayList<Staff>();
	
	public void onEnable() {
		plugin = this;
		log = new CoreLog(this,CoreLog.Color.RED);
		log.line();
		log.info("SuperStaffChat v" + getDescription().getVersion() + " is being loaded...");
		log.info("Plugin Create by PedroJM96.");
		PluginManager pluginManager = getProxy().getPluginManager();
		pluginManager.registerListener(this, new PlayerListenerBungee());
		pluginManager.registerCommand(this, new CommandReload(this,"staffchatreload",new String[] {"screload"}));
		pluginManager.registerCommand(this, new CommandStaffOnline(this,"staffonline",new String[] {"sonline","staffon"}));
		log.info("&7Loading configuration...");
		this.config = new CoreConfig(this,"config",log,this.getResourceAsStream("config.yml"),this.getResourceAsStream("config.yml"),true);
		this.log.seDebug(this.config.getBoolean("debug"));
		loadMessages();
	    AllString.LoadString(this);
	    this.chat = new CoreConfig(this,"chat",log,this.getResourceAsStream("bungee_chat.yml"),this.getResourceAsStream("bungee_chat.yml"),false);
	    loadChat();
	    checkForUpdates();
	    @SuppressWarnings("unused")
		Metrics metrics = new Metrics(this,	15363);
	    log.line();
	}
	
	public void loadMessages(){
		String m = config.getString("messages");
		switch(m.toUpperCase()){
		case "EN":
			this.messages = new CoreConfig(this,"messages_EN",log,this.getResourceAsStream("messages_EN.yml"),this.getResourceAsStream("messages_EN.yml"),true);
			break;
		case "ES":
			this.messages = new CoreConfig(this,"messages_ES",log,this.getResourceAsStream("messages_ES.yml"),this.getResourceAsStream("messages_ES.yml"),true);
			break;
		default:
			this.messages = new CoreConfig(this,"messages_EN",log,this.getResourceAsStream("messages_EN.yml"),this.getResourceAsStream("messages_EN.yml"),true);
			break;
		}
	}

	

	public void loadChat() {
		listChat= new ArrayList<Staff>();
		Collection<String> key = chat.getKeys();
		for (Iterator<String> iterator = key.iterator(); iterator.hasNext();) {
			String nodomenu = iterator.next();
			Staff c = new Staff(nodomenu);
			c.setCommand(chat.getString(nodomenu+".command"));
			c.setAlias(chat.getString(nodomenu+".alias"));
			c.setPermission(chat.getString(nodomenu+".permission"));
			c.setFormat(chat.getString(nodomenu+".format"));
			listChat.add(c);
			
			
		}
	}
	

	public void checkForUpdates() {
        if(config.getBoolean("update-check")){

        	getProxy().getScheduler().runAsync(plugin, new Runnable() {
				@Override
				public void run() {
					CoreSpigotUpdater updater = new CoreSpigotUpdater(plugin, 54423);
		        	try {
		                if (updater.checkForUpdates()) {
		                	log.info("An update was found! for SuperStaffChat. Please update to recieve latest version. download: " + updater.getResourceURL());
		                }	
		            } catch (Exception e) {
		            	
		            	log.error("Failed to check for a update on spigot.");
		            }
				}
        		
        	});
        } 
    }
	
	public CoreLog getLog(){
		return this.log;
	}
	
	public CoreConfig getCConfig(){
		return this.config;
	}
	public CoreConfig getCMessages(){
		return this.messages;
	}
	
	public static BungeeStaffChat getIntence(){
		return plugin;
	}
}