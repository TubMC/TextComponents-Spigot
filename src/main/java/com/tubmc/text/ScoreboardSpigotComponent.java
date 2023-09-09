package com.tubmc.text;

import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.md_5.bungee.api.chat.ScoreComponent;

@Internal
final class ScoreboardSpigotComponent extends AbstractBaseSpigotComponent<ScoreComponent> implements IScoreboardComponent {
	
	@Internal
	ScoreboardSpigotComponent(@NotNull final ScoreComponent internal) {
		super(internal);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @NotNull String getSelector() {
		return this.internal.getName();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setSelector(final @NotNull String newSelector) {
		this.internal.setValue(newSelector);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @NotNull String getObjective() {
		return this.internal.getObjective();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setObjective(final @NotNull String newObjective) {
		this.internal.setObjective(newObjective);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @Nullable String getValue() {
		return this.internal.getValue();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setValue(final @Nullable String newValue) {
		this.internal.setValue(newValue);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @NotNull ScoreboardSpigotComponent clone() {
		return new ScoreboardSpigotComponent(this.internal.duplicate());
	}
}
