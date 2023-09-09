package com.tubmc.text;

import java.lang.reflect.Field;
import java.util.Collection;

import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.md_5.bungee.api.chat.TranslatableComponent;

@Internal
final class TranslatableSpigotComponent extends AbstractBaseSpigotComponent<TranslatableComponent> implements ITranslatableComponent {
	
	private static Field FALLBACK_FIELD;
	private static boolean LOOKED_UP_FIELD = false;
	
	@Internal
	TranslatableSpigotComponent(@NotNull final TranslatableComponent internal) {
		super(internal);
		if (FALLBACK_FIELD == null) {
			LOOKED_UP_FIELD = true;
			try {
				FALLBACK_FIELD = TranslatableComponent.class.getField("fallback");
				FALLBACK_FIELD.setAccessible(true);
			} catch (NoSuchFieldException | SecurityException e) {
				if (!LOOKED_UP_FIELD) {
					Entrypoint.getPlugin(Entrypoint.class).getLogger().warning("Failed to find fallback field for TranslatableComponent");
				}
			}
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @NotNull String getTranslationKey() {
		return this.internal.getTranslate();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setTranslationKey(@NotNull final String newTranslationKey) {
		this.internal.setTranslate(newTranslationKey);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @Nullable String getTranslationFallback() {
		if (FALLBACK_FIELD != null) {
			try {
				return (String) FALLBACK_FIELD.get(this.internal);
			} catch (IllegalArgumentException | IllegalAccessException | ClassCastException e) {
				FALLBACK_FIELD = null;
				Entrypoint.getPlugin(Entrypoint.class).getLogger().warning("Failed to fetch field fallback TranslatableComponent");
			}
		}
		return null;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setTranslationFallback(@Nullable final String newFallback) {
		if (FALLBACK_FIELD != null) {
			try {
				FALLBACK_FIELD.set(this.internal, newFallback);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				FALLBACK_FIELD = null;
				Entrypoint.getPlugin(Entrypoint.class).getLogger().warning("Failed to set field fallback TranslatableComponent");
			}
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @Nullable Collection<@NotNull IComponent> getInsertions() {
		return this.internal.getWith().stream().map(SpigotTextImplementation::wrapComponent).toList();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setInsertions(@NotNull final Collection<@NotNull IComponent> newInsertions) {
		if (newInsertions == null) {
			this.internal.setWith(null);
			return;
		}
		this.internal.setWith(newInsertions.stream().map(SpigotTextImplementation::unwrapComponent).toList());
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @NotNull TranslatableSpigotComponent clone() {
		return new TranslatableSpigotComponent(this.internal.duplicate());
	}
}
