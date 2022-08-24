package com.pedrojm96.superstaffchat;

import java.util.ArrayList;
import java.util.List;
import net.md_5.bungee.api.ChatColor;



public class CoreUtils {

	public static String rColor(String nonColoredText) {
        String coloredText = ChatColor.translateAlternateColorCodes('&', nonColoredText);
        return coloredText;
    }
	
	public static boolean isint(String s){
		try{
			@SuppressWarnings("unused")
			int i = Integer.parseInt(s);
			return true;
		}
		catch(NumberFormatException er){
			return false;
		}
		
	}
	
	public static int toint(String s){
		try{
			int i = Integer.parseInt(s);
			return i;
		}
		catch(NumberFormatException er){
			return 0;
		}
		
	}
	
	
	
	
	public static List<String> getLisColor(){
		List<String> c = new ArrayList<String>();
		c.add("&0");
		c.add("&1");
		c.add("&2");
		c.add("&3");
		c.add("&4");
		c.add("&5");
		c.add("&6");
		c.add("&7");
		c.add("&8");
		c.add("&9");
		
		c.add("&a");
		c.add("&b");
		c.add("&c");
		c.add("&d");
		c.add("&e");
		c.add("&f");
		c.add("&k");
		c.add("&l");
		c.add("&m");
		c.add("&n");
		c.add("&o");
		c.add("&r");
		
		c.add("&A");
		c.add("&B");
		c.add("&C");
		c.add("&D");
		c.add("&E");
		c.add("&F");
		c.add("&K");
		c.add("&L");
		c.add("&M");
		c.add("&N");
		c.add("&O");
		c.add("&R");
		return c;
	}
	
}
