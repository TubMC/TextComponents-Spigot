# TextComponents [Spigot Edition]

A simple [TextComponents](https://github.com/TubMC/TextComponents) implementation that provides support for [SpigotMC](https://hub.spigotmc.org/) via it's [ChatComponent API](https://www.spigotmc.org/wiki/the-chat-component-api/)

*This implementation does NOT support CraftBukkit! Furthermore, it will never due to the annoyance of NMS*

## Implementation Specific Methods

The following methods are added to all IComponents in this implementation:

| Name     | Parameters    | Return Type   | Function                                          |
|----------|---------------|---------------|---------------------------------------------------|
| [sendTo](https://github.com/TubMC/TextComponents-Spigot/blob/master/src/main/java/com/tubmc/text/ImplementationSpecificComponentMethods.java#L35)   | [CommandSender](https://github.com/Bukkit/Bukkit/blob/master/src/main/java/org/bukkit/command/CommandSender.java) |               | Sends the component to the provided CommandSender |
| [toSpigot](https://github.com/TubMC/TextComponents-Spigot/blob/master/src/main/java/com/tubmc/text/ImplementationSpecificComponentMethods.java#L39) |               | [BaseComponent](https://github.com/SpigotMC/BungeeCord/blob/master/chat/src/main/java/net/md_5/bungee/api/chat/BaseComponent.java) | Converts the component into a BaseComponent       |

## What doesn't work?

### ISelectedComponent

The following methods do not work within this implementation:

`#getSeperator()`, `#setSeperator()`

This is due to the limitations of the underlying [SelectorComponent](https://github.com/SpigotMC/BungeeCord/blob/master/chat/src/main/java/net/md_5/bungee/api/chat/SelectorComponent.java) and cannot be resolved

> I have requested a fix of this in [this feature request](https://github.com/SpigotMC/BungeeCord/issues/3512)