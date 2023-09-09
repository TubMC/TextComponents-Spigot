package com.tubmc.text;

import java.awt.Color;
import java.util.Collection;
import java.util.UUID;

import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.tubmc.text.interaction.ClickInteraction;
import com.tubmc.text.interaction.ClickType;
import com.tubmc.text.interaction.EntityHoverData;
import com.tubmc.text.interaction.HoverInteraction;
import com.tubmc.text.interaction.HoverType;
import com.tubmc.text.interaction.ItemHoverData;

import fun.bb1.objects.defineables.ITypedProxy;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ItemTag;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Entity;
import net.md_5.bungee.api.chat.hover.content.Item;
import net.md_5.bungee.api.chat.hover.content.Text;

@Internal
sealed abstract class AbstractBaseSpigotComponent<T extends BaseComponent> implements ImplementationComponentBase, ITypedProxy<T> permits KeybindSpigotComponent, LiteralSpigotComponent, ScoreboardSpigotComponent, SelectedSpigotComponent, TranslatableSpigotComponent {
	
	protected final @NotNull T internal;
	
	protected AbstractBaseSpigotComponent(final @NotNull T internal) {
		this.internal = internal;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @Nullable Collection<@NotNull IComponent> getChildren() {
		if (this.internal.getExtra() == null || this.internal.getExtra().isEmpty()) {
			return null;
		}
		return this.internal.getExtra().stream().map(SpigotTextImplementation::wrapComponent).toList();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setChildren(@NotNull final Collection<@NotNull IComponent> newChildrenComponents) {
		if (newChildrenComponents == null || newChildrenComponents.size() == 0) {
			this.internal.setExtra(null);
			return;
		}
		this.internal.setExtra(newChildrenComponents.stream().map(SpigotTextImplementation::unwrapComponent).toList());
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @Nullable Color getColor() {
		return this.internal.getColor().getColor();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setColor(@Nullable final Color newColor) {
		this.internal.setColor(newColor == null ? null : ChatColor.of(newColor));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isBold() {
		return this.internal.isBold();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setBold(final boolean isBold) {
		this.internal.setBold(isBold);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isItalic() {
		return this.internal.isItalic();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setItalic(final boolean isItalic) {
		this.internal.setItalic(isItalic);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isUnderlined() {
		return this.internal.isUnderlined();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setUnderlined(final boolean isUnderlined) {
		this.internal.setUnderlined(isUnderlined);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isStrikedThrough() {
		return this.internal.isStrikethrough();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setStrikeThrough(final boolean isStrikedThrough) {
		this.internal.setStrikethrough(isStrikedThrough);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isObfuscated() {
		return this.internal.isObfuscated();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setObfuscated(final boolean isObfuscated) {
		this.internal.setObfuscated(isObfuscated);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @Nullable String getFont() {
		return this.internal.getFont();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setFont(@Nullable final String newFont) {
		this.internal.setFont(newFont);
	}
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("deprecation")
	@Override
	public final @Nullable ClickInteraction getClicked() {
		final ClickEvent clickEvent = this.internal.getClickEvent();
		return clickEvent == null ? null : new ClickInteraction(switch(clickEvent.getAction()) {
			case CHANGE_PAGE -> ClickType.GOTO_PAGE;
			case COPY_TO_CLIPBOARD -> ClickType.CLIPBOARD;
			case OPEN_FILE -> ClickType.OPEN_FILE;
			case OPEN_URL -> ClickType.OPEN_URL;
			case RUN_COMMAND -> ClickType.EXECUTE;
			default -> ClickType.SUGGEST;
		}, clickEvent.getValue());
	}
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("deprecation")
	@Override
	public final void setClicked(@Nullable final ClickInteraction interaction) {
		if (interaction == null) {
			this.internal.setClickEvent(null);
			return;
		}
		this.internal.setClickEvent(new ClickEvent(switch (interaction.type()) {
			case GOTO_PAGE -> Action.CHANGE_PAGE;
			case CLIPBOARD -> Action.COPY_TO_CLIPBOARD;
			case OPEN_FILE -> Action.OPEN_FILE;
			case OPEN_URL -> Action.OPEN_URL;
			case EXECUTE -> Action.RUN_COMMAND;
			default -> Action.SUGGEST_COMMAND;
		}, interaction.data()));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @Nullable HoverInteraction<?> getHovering() {
		final HoverEvent hoverEvent = this.internal.getHoverEvent();
		if (hoverEvent == null || hoverEvent.getContents().isEmpty()) {
			return null;
		}
		final Content hoverContent = hoverEvent.getContents().get(0);
		if (hoverContent instanceof final Entity entityContent) {
			return new HoverInteraction<EntityHoverData>(HoverType.ENTITY, new EntityHoverData(SpigotTextImplementation.wrapComponent(entityContent.getName()), entityContent.getType(), UUID.fromString(entityContent.getId())));
		}
		if (hoverContent instanceof final Item itemContent) {
			return new HoverInteraction<ItemHoverData>(HoverType.ITEM, new ItemHoverData(itemContent.getId(), itemContent.getCount(), itemContent.getTag().getNbt()));
		}
		return new HoverInteraction<IComponent>(HoverType.TEXT, ((Text)hoverContent).getValue() instanceof String str ? ILiteralComponent.of(str) : SpigotTextImplementation.wrapComponent((BaseComponent)((Text)hoverContent).getValue()));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setHovering(@Nullable final HoverInteraction<?> interaction) {
		if (interaction == null) {
			this.internal.setHoverEvent(null);
			return;
		}
		if (interaction.data() instanceof EntityHoverData entityHover) {
			this.internal.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ENTITY, new Entity(entityHover.typeIdentifier(), entityHover.entityUUID().toString(), SpigotTextImplementation.unwrapComponent(entityHover.entityName()))));
			return;
		}
		if (interaction.data() instanceof ItemHoverData itemHover) {
			this.internal.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new Item(itemHover.itemIdentifier(), itemHover.count(), ItemTag.ofNbt(itemHover.nbtTagAsString()))));
			return;
		}
		this.internal.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(new BaseComponent[] { SpigotTextImplementation.unwrapComponent((IComponent)interaction.data()) })));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract @NotNull AbstractBaseSpigotComponent<T> clone();
	/**
	 * Since {@link BaseComponent#duplicate()} deep clones by default just forward to it
	 */
	@Override
	public @NotNull AbstractBaseSpigotComponent<T> deepClone() {
		return this.clone();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @NotNull T getProxiedObject() {
		return this.internal;
	}
}
