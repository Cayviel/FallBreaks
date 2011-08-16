package com.Cayviel.FallBreaks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FallBreaks extends JavaPlugin{

	public static String directory = "plugins" + File.separator + "FallBreaks";	
    public static File configFile = new File(directory + File.separator + "config.yml");
    public static File serverproperties = new File("server.properties");
	public static Properties properties = new Properties();
	public static Logger log = Logger.getLogger("Minecraft");
	
	public static BreakConfig breakconfig = new BreakConfig(configFile,directory);
	
	@Override
	public void onDisable() {
		getServer().getLogger().info("Disabling FallBreaks...");	
		
	}
	
	@Override
	public void onEnable() {
		
	    try {
			FileInputStream inputfile = new FileInputStream(serverproperties);
			properties.load(inputfile);
			inputfile.close();
		} catch (IOException eceptin) {
			eceptin.printStackTrace();
		}	
	    breakconfig.configCheck();		
		getServer().getLogger().info("Enabling FallBreaks...");		
		PluginManager pm = getServer().getPluginManager();
		FallListener falllisten = new FallListener(pm);
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, falllisten, Priority.Low, this);
		
	}
	
}
