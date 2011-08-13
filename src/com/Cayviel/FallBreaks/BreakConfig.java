package com.Cayviel.FallBreaks;

import java.io.File;

import org.bukkit.util.config.Configuration;

public class BreakConfig{

    public String directory;
    public File configFile;
    
	BreakConfig(File conf, String dir){
		directory = dir;
		configFile = conf;
	}

	public void configCheck(){
	        new File(directory).mkdir();
	        if(!configFile.exists()){
	            try {
	            	configFile.createNewFile();
	                addDefaults();

	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	
   public void write(String root, Object x){
       Configuration config = load();
       config.setProperty(root, x);
       config.save();
   }

   private Configuration load(){

       try {
           Configuration config = new Configuration(configFile);
           config.load();
           return config;

       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }
   
   private void addDefaults(){
    FallBreaks.log.info("Generating Config File...");
    write("Break On Fall.DEFAULT",false);
    write("Break On Fall.GLASS",true);
    write("Break On Fall.ICE",true);
    FallBreaks.log.info("Loading Config File...");
   }
   
   public boolean getdefaultbreak(){
       Configuration config = load();
       return config.getBoolean("Break On Fall.DEFAULT",false);
   }
   
   public boolean getbreak(String materialname){
       Configuration config = load();
       return config.getBoolean("Break On Fall."+materialname,getdefaultbreak());
   }
}
