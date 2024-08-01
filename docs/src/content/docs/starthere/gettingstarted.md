---
title: Getting Started
description: A guide in my new Starlight docs site.
---
This page gives instruction on

1. Installing Arcade onto your servers
2. Using Arcade to start up a minigame
3. Configuring minigame settings
4. Editing global settings for Arcade

## Download

Head over to our [Modrinth page](https://modrinth.com/plugin/arcade) and install the jar file.
Arcade works with Bukkit, Spigot, and Paper servers.

Once you've installed the `arcade.jar` file, move it into your `plugins/` folder.

## Using Arcade

After you've started your server (or used `/reload`) you've successfully installed Arcade
and you are ready to use it.

To start a minigame, use the command `/arcade open`, which will bring up a menu with
the minigames we currently have to offer. You can check the documentation for each
minigame/tweak [here](/minigames/overview).

### Starting a minigame

To start a minigame, simply left click on the minigame icon to start it.

### Configuring a minigame

To configure a minigame, you can right click a minigame icon.

Arcade has a few different types of setting types:

- Integer Setting
- Boolean Setting
- Player Selection Setting
- String Setting

As of right now, the only settings which are being used in various minigames are the
Integer Setting, and the Boolean Setting.

#### Integer Setting

The integer setting type allows you to pick a number. Some settings include limits on
the amount you can pick. For example, the [Death Swap](/minigames/deathswap) minigame's
`Time Between`
setting only allows you to have a maximum time of `3600` seconds (1 hour). And some settings
also have different intervals, for example the [Border Size](/globalsettings/bordersize) setting
has a 50m interval. Meaning as you click, the value goes up/down by 50m.

#### Boolean setting

This is a simple setting, simply a true/false value.

#### String Setting

This setting type is currently unimplemented but the plan is to have a sign pop up, asking
you to enter in a string value.

#### Player Selection Setting

This setting type is currently unimplemented. But the plan is to have a menu
with player heads to choose from.

### Global settings

There are two ways to access the global settings menu

1. Through the main Arcade menu (`/arcade open`): click the `Iron Nugget` to the
   left of the stop minigame icon.
2. Or using the `/arcade settings`

For a full list of Global Settings, view the page [here](/globalsettings/overview).