package com.pedrojm96.superstaffchat.Bukkit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.pedrojm96.superstaffchat.Staff;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;



/**
 * This class will be registered through the register-method in the 
 * plugins onEnable-method.
 */
public class PlaceholderApiExpansion extends PlaceholderExpansion {

    private BukkitStaffChat plugin;

    /**
     * Since we register the expansion inside our own plugin, we
     * can simply use this method here to get an instance of our
     * plugin.
     *
     * @param plugin
     *        The instance of our plugin.
     */
    public PlaceholderApiExpansion(BukkitStaffChat plugin){
        this.plugin = plugin;
    }

    /**
     * Because this is an internal class,
     * you must override this method to let PlaceholderAPI know to not unregister your expansion class when
     * PlaceholderAPI is reloaded
     *
     * @return true to persist through reloads
     */
    @Override
    public boolean persist(){
        return true;
    }

    /**
     * Because this is a internal class, this check is not needed
     * and we can simply return {@code true}
     *
     * @return Always true since it's an internal class.
     */
    @Override
    public boolean canRegister(){
        return true;
    }

    /**
     * The name of the person who created this expansion should go here.
     * <br>For convienience do we return the author from the plugin.yml
     * 
     * @return The name of the author as a String.
     */
    @Override
    public String getAuthor(){
        return plugin.getInstance().getDescription().getAuthors().toString();
    }

    /**
     * The placeholder identifier should go here.
     * <br>This is what tells PlaceholderAPI to call our onRequest 
     * method to obtain a value if a placeholder starts with our 
     * identifier.
     * <br>This must be unique and can not contain % or _
     *
     * @return The identifier in {@code %<identifier>_<value>%} as String.
     */
    @Override
    public String getIdentifier(){
        return "superstaffchat";
    }

    /**
     * This is the version of the expansion.
     * <br>You don't have to use numbers, since it is set as a String.
     *
     * For convienience do we return the version from the plugin.yml
     *
     * @return The version as a String.
     */
    @Override
    public String getVersion(){
        return plugin.getInstance().getDescription().getVersion();
    }

    /**
     * This is the method called when a placeholder with our identifier 
     * is found and needs a value.
     * <br>We specify the value identifier in this method.
     * <br>Since version 2.9.1 can you use OfflinePlayers in your requests.
     *
     * @param  player
     *         A {@link org.bukkit.Player Player}.
     * @param  identifier
     *         A String containing the identifier/value.
     *
     * @return possibly-null String of the requested identifier.
     */
    @Override
    public String onPlaceholderRequest(Player p, String identifier){

    	if (p == null) {
            return "";
        }
    	//%superstaffchat_chat%
		if(identifier.startsWith("chat")) {
			//%superstaffchat_chat%
			if(identifier.equalsIgnoreCase("chat")) {
				if(this.plugin.toggleChat.containsKey(p.getName().toLowerCase())) {
					 Staff c = this.plugin.toggleChat.get(p.getName().toLowerCase());
					 return String.valueOf(c.getID()); 
				}
				return " ";
			}
			//%superstaffchat_chat_<playe>r%
			if(identifier.contains("_")) {
				String identifier_player = identifier.split("_")[1].trim();
				if(this.plugin.toggleChat.containsKey(identifier_player.toLowerCase())) {
					 Staff c = this.plugin.toggleChat.get(identifier_player.toLowerCase());
					 return String.valueOf(c.getID()); 
				}
				return " ";
			}
			return null;
		}
		
		//%superstaffchat_status_<playe>r%
		if(identifier.startsWith("status")) {
			//%superstaffchat_status_<playe>r%
			if(identifier.contains("_")) {
				String identifier_player = identifier.split("_")[1].trim();
				if(Bukkit.getPlayer(identifier_player)!=null) {
					return String.valueOf("Online"); 
				}
				return String.valueOf("Offline"); 
			}
			return null;
		}	
        // anything else someone types is invalid because we never defined %customplaceholder_<what they want a value for>%
        // we can just return null so the placeholder they specified is not replaced.
        return null;
    }
}