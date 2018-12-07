package me.engineersbox.rankviewer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class GroupPlugins {

	public static List<String> pexGetGroups(Player p) {
		
		PermissionUser user2 = PermissionsEx.getUser(p);
		
		List<String> groups = new ArrayList<>();
		List<String> g2 = user2.getOwnParentIdentifiers();
		
		for (int i = 0; i < g2.size(); i++) {
			
			groups.add(g2.get(i).toString());
		}
		
		return groups;
		
	}
	
	public static List<String> pexGetGroupPrefixes(Player p) {
		
		PermissionUser user2 = PermissionsEx.getUser(p);
		
		List<String> g2 = user2.getOwnParentIdentifiers();
		List<String> groupPrefix = new ArrayList<>();
		
		for (int i = 0; i < g2.size(); i++) {
			
			groupPrefix.add(Main.format(user2.getParents().get(i).getPrefix()));
		}
		
		return groupPrefix;
		
	}
	
}
