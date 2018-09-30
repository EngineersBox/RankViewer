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
    	
    	Boolean booleanData = config.getBoolean("Use group name");
    	
    	if (booleanData.equals(true)) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    		
    	}
    	
    }
   
}