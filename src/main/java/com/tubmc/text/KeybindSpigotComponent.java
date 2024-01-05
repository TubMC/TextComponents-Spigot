package com.tubmc.text;

import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.md_5.bungee.api.chat.KeybindComponent;

/**
 *    Copyright 2023-2024 TubMC.com
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
 * 
 * @author BradBot_1
 * @since 1.0.0
 * @version 1.0.0
 */
@Internal
final class KeybindSpigotComponent extends AbstractBaseSpigotComponent<KeybindComponent> implements IKeybindComponent {
	
	@Internal
	KeybindSpigotComponent(@NotNull final KeybindComponent internal) {
		super(internal);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @Nullable String getKeybind() {
		return this.internal.getKeybind();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setKeybind(@Nullable final String newKeybind) {
		this.internal.setKeybind(newKeybind);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @NotNull KeybindSpigotComponent clone() {
		return new KeybindSpigotComponent(this.internal.duplicate());
	}
}
