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

### ITranslatableComponent (kinda)

The following methods *may* not work:

`#getTranslationFallback()`, `#setTranslationFallback()`

While the underlying api now [supports this feature](https://github.com/SpigotMC/BungeeCord/blob/master/chat/src/main/java/net/md_5/bungee/api/chat/TranslatableComponent.java#L36) spigot's version of it doesn't seem to have caught up yet. As a result an attempt to modify the field is performed upon calling these methods (for when it's updated) but if that fails an error message is displayed **once**.

## Installation

> It is best to use an implementation rather than depending on this as  methods for sending messages will not be accessible

TextComponents is available on Maven from either the [Official Maven Repository](https://repo.bb1.fun/#/releases/com/tubmc/text-components-spigot) or [JitPack](https://jitpack.io/#TubMC/TextComponents-Spigot)

### Official Repository

The latest version is hosted on an [Official Maven Repository](https://repo.bb1.fun/#/releases/com/tubmc/text-components-spigot)

First include the repository:

```xml
<repository>
  <id>bb1-repository-releases</id>
  <name>BradBot_1's Repository</name>
  <url>https://repo.bb1.fun/releases</url>
</repository>
```

Then add the dependency:

```xml
<dependency>
  <groupId>com.tubmc</groupId>
  <artifactId>text-components-spigot</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Jitpack

If the official repository is down or you choose not to trust it you can always pull it from [JitPack](https://jitpack.io/#TubMC/TextComponents-Spigot)

First include the repository:

```xml
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>
```

Then add the dependency:

```xml
<dependency>
  <groupId>com.github.BradBot1</groupId>
  <artifactId>text-components-spigot</artifactId>
  <version>LATEST</version>
</dependency>
```

### Local Installation

Just run the following commands:

```shell
git clone https://github.com/TubMC/TextComponents-Spigot.git
cd TextComponents-Spigot
mvn clean install
```

It will then be accessible from your [local Maven Repoistory](https://www.javatpoint.com/maven-repository)

Now you can simply add the following dependency without a repository:

```xml
<dependency>
  <groupId>com.tubmc</groupId>
  <artifactId>text-components-spigot</artifactId>
  <version>1.0.0</version>
</dependency>
```