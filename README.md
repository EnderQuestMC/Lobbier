# Lobbier

Lobbier is a collection of useful utilities for lobby & minigame servers.

Mainly, it includes utilities to set a header and footer on the player list and a scoreboard. More features may be added in the future.

## Developers

### Maven

Insert the following snippets into your POM.xml.

For the repository:

```xml
<repositories>
    ...
    <repository>
        <id>github</id>
        <name>GitHub Packages</name>
        <url>https://maven.pkg.github.com/EnderQuestMC/Lobbier</url>
    </repository>
    ...
</repositories>
```

For the dependency:

```xml
<dependencies>
    ...
    <dependency>
        <groupId>quest.ender</groupId>
        <artifactId>lobbier</artifactId>
        <version>{version}</version>
    </dependency>
    ...
</dependencies>
```

Replace `{version}` with the current version. You can see the current version below. Don't include the "v".

![Current Version](https://img.shields.io/github/v/release/EnderQuestMC/Lobbier)
