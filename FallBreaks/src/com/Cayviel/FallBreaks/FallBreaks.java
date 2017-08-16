package com.Cayviel.FallBreaks;

public class FallBreaks extends FallBreaksConfig{
	static FallBreaks fallbreaksmain;
	
	@Override
	public void onDisable() {
		getServer().getLogger().info("Disabling FallBreaks...");	
	}
	
	@Override
	public void onEnable() {
		fallbreaksmain = this;
		getServer().getLogger().info("Enabling FallBreaks...");
		initialize();
		getServer().getPluginManager().registerEvents(
				
				new FallListener(), this);
		
	}
	
}
