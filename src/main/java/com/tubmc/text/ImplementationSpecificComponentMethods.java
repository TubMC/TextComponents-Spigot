package com.tubmc.text;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;

import net.md_5.bungee.api.chat.BaseComponent;

/**
 *    Copyright 2023 TubMC.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
/**
 * Adds functionality to all {@link IComponent}'s
 * 
 * @author BradBot_1
 * @since 1.0.0
 * @version 1.0.0
 * @see IComponent
 */
@Internal
sealed interface ImplementationSpecificComponentMethods permits IComponent {
	
	public default void sendTo(@NotNull final CommandSender commandSender) {
		commandSender.spigot().sendMessage(this.toSpigot());
	}
	
	public default @NotNull BaseComponent toSpigot() {
		return SpigotTextImplementation.unwrapComponent((IComponent)this);
	}
	
	public static @NotNull IComponent fromSpigot(@NotNull final BaseComponent baseComponent) {
		return SpigotTextImplementation.wrapComponent(baseComponent);
	}
	
}