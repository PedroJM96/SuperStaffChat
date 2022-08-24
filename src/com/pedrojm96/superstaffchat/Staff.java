package com.pedrojm96.superstaffchat;

import java.util.ArrayList;
import java.util.List;

public class Staff {

	private String command;
	private String alias;
	private String permission;
	private String format;
	private String id;
	public List<String> playersnames = new ArrayList<String>();
	
	
	public Staff(String id) {
		this.id = id;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	
	
	public void setCommand(String command) {
		this.command = command;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getCommand() {
		return this.command;
	}
	public String getID() {
		return this.id;
	}
	public String getAlias() {
		return this.alias;
	}
	public String getPermission() {
		return this.permission;
	}
	public String getFormat() {
		return this.format;
	}
	
}
