package com.Cayviel.FallBreaks;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FallBreaksConfig extends JavaPlugin {

	boolean dropEnabled = true;
	boolean dropSimPunch = false;
	boolean dropReqBootTier = true;
	boolean breakReqBootTier = true;
	boolean breakReqDamageTier = true;
	boolean breakReqBoots = true;

	private void loadDefaultConfigFile() {
		FileConfiguration c = getConfig();
		getServer().getLogger().info("Generating Config File...");

		//Configuration defaults
		c.options().header("FallBreaks Configuration File");
		c.addDefault("Drops.Enabled", true);
		c.addDefault("Drops.Lowest Tier Only", false);
		c.addDefault("Drops.Requirements.Tiered Boots", true);
		c.addDefault("Break.Requirements.Tiered Boots", true);
		c.addDefault("Break.Requirements.Tiered Damage", true);
		c.addDefault("Break.Requirements.Boots", true);
		c.addDefault("Breakables.DEFAULT", false);
		c.addDefault("Breakables.GLASS", true);
		c.addDefault("Breakables.ICE", true);
		c.addDefault("Breakables.DIRT", true);
		c.addDefault("Breakables.GRASS", true);
		c.options().copyDefaults(true);
		
		saveConfig();
		reloadConfig();
	}

	private void loadConfig() {
		try {
			if (!getDataFolder().exists()) {
				getDataFolder().mkdirs();
			}
			File file = new File(getDataFolder(), "config.yml");
			if (!file.exists()) {
				getLogger().info("Config.yml not found, creating!");
				loadDefaultConfigFile();
			} else {
				getLogger().info("Config.yml found, loading!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadkeys() {
		dropEnabled = this.getConfig().getBoolean("Drops.Enabled", true);
		dropSimPunch = this.getConfig().getBoolean("Boots.Lowest Tier Only", false);
		dropReqBootTier = this.getConfig().getBoolean("Break.Requirements.Tiered Boots", true);
		breakReqBootTier = this.getConfig().getBoolean("Break.Requirements.Tiered Boots", true);
		breakReqDamageTier = this.getConfig().getBoolean("Break.Requirements.Tiered Damage", true);
		breakReqBoots = this.getConfig().getBoolean("Break.Requirements.Boots", true);
	}

	boolean getbreak(String materialname) {
		return this.getConfig().getBoolean("Breakables." + materialname, this.getConfig().getBoolean("Breakables.DEFAULT", false));
	}
	
	void initialize() {
		loadConfig();
		loadkeys();
	}
}
