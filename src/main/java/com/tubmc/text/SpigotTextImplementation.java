package com.tubmc.text;

import java.util.Collection;

import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.KeybindComponent;
import net.md_5.bungee.api.chat.ScoreComponent;
import net.md_5.bungee.api.chat.SelectorComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;

@Internal
final class SpigotTextImplementation extends AbstractImplementation {
	
	@Contract("null -> null")
	@Internal
	static @Nullable final IComponent wrapComponent(@Nullable final BaseComponent toWrap) {
		return null;
	}
	
	@Contract("null -> null")
	@Internal
	static @Nullable final BaseComponent unwrapComponent(@Nullable final IComponent toUnwrap) {
		return null;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public @NotNull final IKeybindComponent createKeybind(@NotNull final String keybind) {
		return new KeybindSpigotComponent(new KeybindComponent(keybind));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public @NotNull final ILiteralComponent createLiteral(@NotNull final String text) {
		return new LiteralSpigotComponent(new TextComponent(text));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public @NotNull final IScoreboardComponent createScoreboard(@NotNull final String selector, @NotNull final String objective, @Nullable final String value) {
		final ScoreComponent scoreComponent = new ScoreComponent(selector, objective);
		if (value != null) scoreComponent.setValue(value);
		return new ScoreboardSpigotComponent(scoreComponent);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public @NotNull final ISelectedComponent createSelector(@NotNull final String selector, @Nullable final IComponent seperator) {
		return new SelectedSpigotComponent(new SelectorComponent(selector));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public @NotNull final ITranslatableComponent createTranslatable(@NotNull final String translationKey, @Nullable final String fallback, @Nullable final Collection<@NotNull IComponent> insertions) {
		final TranslatableSpigotComponent translatableComponent = new TranslatableSpigotComponent(new TranslatableComponent(translationKey));
		translatableComponent.setTranslationFallback(fallback);
		translatableComponent.setInsertions(insertions);
		return translatableComponent;
	}
}