package com.Cayviel.FallBreaks;

import java.io.File;

import org.bukkit.util.config.Configuration;

public class BreakConfig{

    public String directory;
    public File configFile;
    public static boolean bootmining;
    public static boolean bootreq;
    public static boolean boottier;
    
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
            loadkeys();
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
    write("Boots.Required To Break", true);
    write("Boots.Tiered Break Requirement", true);
    write("Boots.Boot Mining", true);
    write("Break On Fall.DEFAULT",false);
    write("Break On Fall.GLASS",true);
    write("Break On Fall.ICE",true);
    FallBreaks.log.info("Loading Config File...");
   }
   
   private void loadkeys(){
       Configuration config = load();
	   bootreq = config.getBoolean("Boots.Required To Break", true);
	   bootmining = config.getBoolean("Boots.Boot Mining", true);	   
	   boottier = config.getBoolean("Boots.Tiered Break Requirement", true);
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
