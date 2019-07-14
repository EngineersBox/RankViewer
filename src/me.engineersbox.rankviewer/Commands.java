package me.engineersbox.rankviewer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.engineersbox.rankviewer.Main;

public class Commands implements CommandExecutor {

	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if (cmd.getName().equalsIgnoreCase("rv")) {
				
				if ((args.length == 0) && (p.hasPermission("rv.use"))) {
					
					String version = Bukkit.getServer().getPluginManager().getPlugin("RankViewer").getDescription().getVersion();
        			
    				p.sendMessage("");
    		    	p.sendMessage(ChatColor.GRAY + "----={<" + ChatColor.AQUA + "  [" + ChatColor.BLUE + "RankViewer Version Info" + ChatColor.AQUA + "]  " + ChatColor.GRAY + "}>=----");
    		    	p.sendMessage("");
    		    	p.sendMessage(ChatColor.BLACK + "> " + ChatColor.DARK_GREEN + "Version Number " + ChatColor.WHITE + ":: " + ChatColor.DARK_RED + version);
    		    	p.sendMessage(ChatColor.BLACK + "> " + ChatColor.DARK_GREEN + "Author " + ChatColor.WHITE + ":: " + ChatColor.DARK_RED + "EngineersBox");
    		    	p.sendMessage("");
    		    	p.sendMessage(ChatColor.GRAY + "----=<{" + ChatColor.AQUA + "  [" + ChatColor.BLUE + "RankViewer Version Info" + ChatColor.AQUA + "]  " + ChatColor.GRAY + "}>=----");
    		    	p.sendMessage("");
					
				}
			
				if (args.length > 0) {
				
					if ((args[0].equalsIgnoreCase("version")) && (p.hasPermission("rv.version"))) {
							
						String version = Bukkit.getServer().getPluginManager().getPlugin("RankViewer").getDescription().getVersion();
	        			
	    				p.sendMessage("");
	    		    	p.sendMessage(ChatColor.GRAY + "----={<" + ChatColor.AQUA + "  [" + ChatColor.BLUE + "RankViewer Version Info" + ChatColor.AQUA + "]  " + ChatColor.GRAY + "}>=----");
	    		    	p.sendMessage("");
	    		    	p.sendMessage(ChatColor.BLACK + "> " + ChatColor.DARK_GREEN + "Version Number " + ChatColor.WHITE + ":: " + ChatColor.DARK_RED + version);
	    		    	p.sendMessage(ChatColor.BLACK + "> " + ChatColor.DARK_GREEN + "Author " + ChatColor.WHITE + ":: " + ChatColor.DARK_RED + "EngineersBox");
	    		    	p.sendMessage("");
	    		    	p.sendMessage(ChatColor.GRAY + "----=<{" + ChatColor.AQUA + "  [" + ChatColor.BLUE + "RankViewer Version Info" + ChatColor.AQUA + "]  " + ChatColor.GRAY + "}>=----");
	    		    	p.sendMessage("");
						
					} else if ((args[0].equalsIgnoreCase("help")) && (p.hasPermission("rv.help"))) {
						
		            	p.sendMessage("");
				    	p.sendMessage(ChatColor.GRAY + "----={<" + ChatColor.AQUA + "  [" + ChatColor.BLUE + "RankViewer Help" + ChatColor.AQUA + "]  " + ChatColor.GRAY + "}>=----");
				    	p.sendMessage("");
		            	p.sendMessage(ChatColor.BLACK + "> " + ChatColor.DARK_GREEN + "/rv version " + ChatColor.WHITE + ":: " + ChatColor.DARK_RED + "Displays the plugin version and author");
		            	p.sendMessage(ChatColor.BLACK + "> " + ChatColor.DARK_GREEN + "/rv reload " + ChatColor.WHITE + ":: " + ChatColor.DARK_RED + "Reloads the plugin");
		            	p.sendMessage(ChatColor.BLACK + "> " + ChatColor.DARK_GREEN + "/rv userank <enable/disable> " + ChatColor.WHITE + ":: " + ChatColor.DARK_RED + "Switches between "+ ChatColor.AQUA + "[" + ChatColor.RED + "R" + ChatColor.AQUA + "]" + ChatColor.DARK_RED + " prefix and rank name prefix");
		            	p.sendMessage(ChatColor.BLACK + "> " + ChatColor.DARK_GREEN + "/rv help " + ChatColor.WHITE + ":: " + ChatColor.DARK_RED + "Opens this menu");
				    	p.sendMessage("");
				    	p.sendMessage(ChatColor.GRAY + "----=<{" + ChatColor.AQUA + "  [" + ChatColor.BLUE + "RankViewer Help" + ChatColor.AQUA + "]  " + ChatColor.GRAY + "}>=----");
				    	p.sendMessage("");
					
					} else if ((args[0].equalsIgnoreCase("userank")) && (p.hasPermission("rv.userank"))) {
				    	
						if (args[1].equalsIgnoreCase("enable")) {
							if (Config.useRankName()) {
								p.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "Already enabled!");
							} else {
								Config.setRankName(true);
								p.sendMessage(Main.prefix + ChatColor.DARK_GREEN + "Using rank name for hoverable prefix");
							}
						} else if (args[1].equalsIgnoreCase("disable")) {
							if (Config.useRankName()) {
								Config.setRankName(false);
								p.sendMessage(Main.prefix + ChatColor.DARK_GREEN + "Using " + ChatColor.AQUA + "[" + ChatColor.RED + "R" + ChatColor.AQUA + "]" + ChatColor.DARK_GREEN + " as hoverable prefix");
							} else {
								p.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "Already disabled!");
							}
						} else {
							
						}
						
					} else if ((args[0].equalsIgnoreCase("reload")) && (p.hasPermission("rv.reload"))) {
					
		    			p.sendMessage(Main.prefix + ChatColor.DARK_GREEN + "Reloading RankViewer...");
		    			Plugin plugin = p.getServer().getPluginManager().getPlugin("RankViewer");
		    			p.getServer().getPluginManager().disablePlugin(plugin);
		    			p.getServer().getPluginManager().enablePlugin(plugin);
		    			p.sendMessage(Main.prefix + ChatColor.DARK_GREEN + "Reload Complete!");
						
					} else if ((!p.hasPermission("rv.help")) || (!p.hasPermission("rv.version")) || (!p.hasPermission("rv.reload"))) {
						
						p.sendMessage(Main.prefix + ChatColor.RED + "You Do Not Have Permission!");
						
					} else {
						
						p.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "Invalid Command!");
						
					}
					
					return true;
				
				}
				
				return true;
			
			}
			
			return true;
			
		}
		
		return true;
		
	}
	
}
