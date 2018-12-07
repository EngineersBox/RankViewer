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
		            	p.sendMessage(ChatColor.BLACK + "> " + ChatColor.DARK_GREEN + "/rv version " + ChatColor.WHITE + ":: " + ChatColor.DARK_RED + "Displays The Plugin Version And Author");
		            	p.sendMessage(ChatColor.BLACK + "> " + ChatColor.DARK_GREEN + "/rv reload " + ChatColor.WHITE + ":: " + ChatColor.DARK_RED + "Reloads The Plugin");
		            	p.sendMessage(ChatColor.BLACK + "> " + ChatColor.DARK_GREEN + "/rv help " + ChatColor.WHITE + ":: " + ChatColor.DARK_RED + "Opens This Menu");
				    	p.sendMessage("");
				    	p.sendMessage(ChatColor.GRAY + "----=<{" + ChatColor.AQUA + "  [" + ChatColor.BLUE + "RankViewer Help" + ChatColor.AQUA + "]  " + ChatColor.GRAY + "}>=----");
				    	p.sendMessage("");
						
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
