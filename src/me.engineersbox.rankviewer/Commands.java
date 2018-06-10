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

	public static Player user;

	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		
		if (sender instanceof Player) {
			
			if ((cmd.getName().equalsIgnoreCase("rv version")) && (p.hasPermission("rv.version"))) {
				
				if (args.length == 0) {
					
					String version = Bukkit.getServer().getPluginManager().getPlugin("BlockPalette").getDescription().getVersion();
        			
    				p.sendMessage("");
    		    	p.sendMessage(ChatColor.GRAY + "----={<" + ChatColor.AQUA + "  [" + ChatColor.RED + "RankViewer Version Info" + ChatColor.AQUA + "]  " + ChatColor.GRAY + "}>=----");
    		    	p.sendMessage("");
    		    	p.sendMessage(ChatColor.BLACK + "> " + ChatColor.DARK_GREEN + "Version Number " + ChatColor.WHITE + ":: " + ChatColor.DARK_RED + version);
    		    	p.sendMessage(ChatColor.BLACK + "> " + ChatColor.DARK_GREEN + "Author " + ChatColor.WHITE + ":: " + ChatColor.DARK_RED + "EngineersBox");
    		    	p.sendMessage("");
    		    	p.sendMessage(ChatColor.GRAY + "----=<{" + ChatColor.AQUA + "  [" + ChatColor.RED + "RankViewer Version Info" + ChatColor.AQUA + "]  " + ChatColor.GRAY + "}>=----");
    		    	p.sendMessage("");
					
				} else {
					
					p.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "Invalid Syntax!");
    				p.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "Usage: " + ChatColor.ITALIC + "/rv version");
					
				}
				
			} else if ((cmd.getName().equalsIgnoreCase("rv help")) && (p.hasPermission("rv.help"))) {
				
				if (args.length == 0) {
					
	            	p.sendMessage("");
			    	p.sendMessage(ChatColor.GRAY + "----={<" + ChatColor.AQUA + "  [" + ChatColor.RED + "RankViewer Version Info" + ChatColor.AQUA + "]  " + ChatColor.GRAY + "}>=----");
			    	p.sendMessage("");
	            	p.sendMessage(ChatColor.BLACK + "> " + ChatColor.DARK_GREEN + "/rv version " + ChatColor.WHITE + ":: " + ChatColor.DARK_RED + "Displays The Plugin Version And Author");
	            	p.sendMessage(ChatColor.BLACK + "> " + ChatColor.DARK_GREEN + "/rv reload " + ChatColor.WHITE + ":: " + ChatColor.DARK_RED + "Reloads The Plugin");
	            	p.sendMessage(ChatColor.BLACK + "> " + ChatColor.DARK_GREEN + "/rv help " + ChatColor.WHITE + ":: " + ChatColor.DARK_RED + "Opens This Menu");
			    	p.sendMessage("");
			    	p.sendMessage(ChatColor.GRAY + "----=<{" + ChatColor.AQUA + "  [" + ChatColor.RED + "RankViewer Version Info" + ChatColor.AQUA + "]  " + ChatColor.GRAY + "}>=----");
			    	p.sendMessage("");
					
				} else {
					
					p.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "Invalid Syntax!");
    				p.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "Usage: " + ChatColor.ITALIC + "/rv help");
					
				}
				
			} else if ((cmd.getName().equalsIgnoreCase("rv reload")) && (p.hasPermission("rv.reload"))) {
				
				if (args.length == 0) {
					
	    			p.sendMessage(Main.prefix + ChatColor.DARK_GREEN + "Reloading RankViewer...");
	    			Plugin plugin = p.getServer().getPluginManager().getPlugin("RankViewer");
	    			p.getServer().getPluginManager().disablePlugin(plugin);
	    			p.getServer().getPluginManager().enablePlugin(plugin);
	    			p.sendMessage(Main.prefix + ChatColor.DARK_GREEN + "Reload Complete!");
					
				} else {
					
					p.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "Invalid Syntax!");
    				p.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "Usage: " + ChatColor.ITALIC + "/rv reload");
					
				}
				
			} else if ((!p.hasPermission("rv.help")) || (!p.hasPermission("rv.version")) || (!p.hasPermission("rv.reload"))) {
				
				p.sendMessage(Main.prefix + ChatColor.RED + "You Do Not Have Permission!");
				
			} else {
				
				p.sendMessage(Main.prefix + ChatColor.DARK_PURPLE + "Invalid Command!");
				
			}
			
			return false;
			
		}
		
		return false;
		
	}
	
}
