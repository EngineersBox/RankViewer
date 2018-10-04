package me.engineersbox.rankviewer;

import me.engineersbox.rankviewer.Main;

public class Config extends AbstractFile {

    public Config(Main main) {
       
        super(main, "Configuration.yml");
       
    }

    public static Object getData(String data) {
    
    	Object returnedData = config.get(data);
    	return returnedData;
    	
    }
    
    public static Object getULine() {
    	
    	Boolean booleanData = config.getBoolean("Links.Underline");
    	
    	if (booleanData.equals(true)) {
    		
    		return "&N";
    		
    	} else if (booleanData.equals(false)) {
    		
    		return "";
    		
    	} else {
    		
    		return null;
    		
    	}
    	
    }
    
    public static Object getGName() {
    	
    	Boolean booleanData = config.getBoolean("Use-Group-Name");
    	
    	if (booleanData.equals(true)) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    		
    	}
    	
    }
    
    public static Object getTabFormat() {
    	
    	Object format = config.get("Tab-Format");
    	Object default_format = "&b[&cR&b]";
    	
    	if (format.toString().trim().length() > 0) {
    		
    		return format;
    		
    	} else {
    		
    		return default_format;
    		
    	}
    	
    	
    }
    
    public static Object getDCConfig() {
    	
    	return config.getBoolean("Discord-Config.Use-Main-Discord-Channel");
    	
    }
    
    public static Object getDCChannel() {
    	
    	return config.get("Discord-Config.Alternate-Channel");
    	
    }
   
}