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
    	
    	Object returnedData = "";
    	
    	if (booleanData.equals(true)) {
    		
    		returnedData = "&N";
    		
    	} else {
    		
    		returnedData = "";
    		
    	}
    	
		return returnedData;
    	
    }
   
}