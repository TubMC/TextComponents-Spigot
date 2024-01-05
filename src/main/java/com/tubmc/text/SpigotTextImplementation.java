package com.tubmc.text;

import java.util.Collection;

import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fun.bb1.objects.defineables.ITypedProxy;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.KeybindComponent;
import net.md_5.bungee.api.chat.ScoreComponent;
import net.md_5.bungee.api.chat.SelectorComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;

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
final class SpigotTextImplementation extends AbstractImplementation {
	
	@Contract("null -> null")
	@Internal
	static @Nullable final IComponent wrapComponent(@Nullable final BaseComponent toWrap) {
		if (toWrap == null) return null;
		if (toWrap instanceof KeybindComponent component) return new KeybindSpigotComponent(component);
		if (toWrap instanceof TextComponent component) return new LiteralSpigotComponent(component);
		if (toWrap instanceof ScoreComponent component) return new ScoreboardSpigotComponent(component);
		if (toWrap instanceof SelectorComponent component) return new SelectedSpigotComponent(component);
		if (toWrap instanceof TranslatableComponent component) return new TranslatableSpigotComponent(component);
		throw new IllegalArgumentException("Unexpected wrap type: " + toWrap.getClass().getName());
	}
	
	@Contract("null -> null")
	@Internal
	static @Nullable final BaseComponent unwrapComponent(@Nullable final IComponent toUnwrap) {
		if (toUnwrap == null) return null;
		// All AbstractBaseSpigotComponent's descend from ITypedProxy where the proxied object is a BaseComponent
		// So rather than building a new object we can grab it's proxied BaseComponent
		if (toUnwrap instanceof ITypedProxy proxy && proxy.getProxiedObject() instanceof BaseComponent baseComponent) return baseComponent;
		// Handle custom component implementations as they need a new BaseComponent built for them
		final BaseComponent baseComponent = (toUnwrap instanceof IKeybindComponent keybindComponent) ? new KeybindComponent(keybindComponent.getKeybind())
				: (toUnwrap instanceof ILiteralComponent literalComponent) ? new TextComponent(literalComponent.getMessage())
				: (toUnwrap instanceof IScoreboardComponent scoreboardComponent) ? new ScoreComponent(scoreboardComponent.getSelector(), scoreboardComponent.getObjective(), scoreboardComponent.getValue() == null ? "" : scoreboardComponent.getValue())
				: (toUnwrap instanceof ISelectedComponent selectorComponent) ? new SelectorComponent(selectorComponent.getSelector())
				: (toUnwrap instanceof ITranslatableComponent translatableComponent) ? new TranslatableComponent(translatableComponent.getTranslationKey())
				: null;
		if (baseComponent == null) throw new IllegalArgumentException("Provided IComponent is not valid! " + toUnwrap.getClass().getName());
		if (toUnwrap.getChildren() != null && toUnwrap.getChildren().size() != 0) {
			baseComponent.setExtra(toUnwrap.getChildren().stream().map(SpigotTextImplementation::unwrapComponent).toList());
		}
		// Extra logic for TranslatableComponent as fallbacks are a newer feature and may not be supported
		if (baseComponent instanceof TranslatableComponent translatableComponent) {
			final ITranslatableComponent copyFrom = (ITranslatableComponent) toUnwrap;
			// doing some magic because i am lazy!
			final TranslatableSpigotComponent wrapper = new TranslatableSpigotComponent(translatableComponent);
			wrapper.setTranslationFallback(copyFrom.getTranslationFallback());
			if (copyFrom.getInsertions() != null) wrapper.setInsertions(copyFrom.getInsertions());
		}
		return baseComponent;
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