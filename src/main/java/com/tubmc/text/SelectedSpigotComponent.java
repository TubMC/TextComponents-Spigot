package com.tubmc.text;

import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.md_5.bungee.api.chat.SelectorComponent;

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
