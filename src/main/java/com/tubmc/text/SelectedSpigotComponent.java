package com.tubmc.text;

import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.md_5.bungee.api.chat.SelectorComponent;

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
final class SelectedSpigotComponent extends AbstractBaseSpigotComponent<SelectorComponent> implements ISelectedComponent {
	
	@Internal
	SelectedSpigotComponent(@NotNull final SelectorComponent internal) {
		super(internal);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @NotNull String getSelector() {
		return this.internal.getSelector();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setSelector(final @NotNull String newSelector) {
		this.internal.setSelector(newSelector);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @Nullable IComponent getSeperator() {
		return null;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setSeperator(@Nullable IComponent newSeperator) {
		// Spigot does not support overwriting this
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @NotNull SelectedSpigotComponent clone() {
		return new SelectedSpigotComponent(this.internal.duplicate());
	}
}
