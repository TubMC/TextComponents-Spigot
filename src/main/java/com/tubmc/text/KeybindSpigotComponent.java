package com.tubmc.text;

import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.md_5.bungee.api.chat.KeybindComponent;

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
