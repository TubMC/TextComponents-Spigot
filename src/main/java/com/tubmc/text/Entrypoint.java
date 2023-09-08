package com.tubmc.text;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * 
 * @author BradBot_1
 * 
 */
public final class Entrypoint extends JavaPlugin {
	
	@Override
	public void onLoad() {
		// implementations autoregister themselves
		if (AbstractImplementation.IMPLEMENTATION == null) {
			new SpigotTextImplementation();
		}
	}
	
}
