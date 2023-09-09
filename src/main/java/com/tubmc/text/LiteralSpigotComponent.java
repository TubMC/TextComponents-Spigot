package com.tubmc.text;

import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.md_5.bungee.api.chat.TextComponent;

@Internal
final class LiteralSpigotComponent extends AbstractBaseSpigotComponent<TextComponent> implements ILiteralComponent {
	
	@Internal
	LiteralSpigotComponent(@NotNull final TextComponent internal) {
		super(internal);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @Nullable String getMessage() {
		return this.internal.getText();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMessage(@Nullable final String newMessage) {
		this.internal.setText(newMessage);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @NotNull LiteralSpigotComponent clone() {
		return new LiteralSpigotComponent(this.internal.duplicate());
	}
}
