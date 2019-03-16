# Player Reviewer

## Features
This plugin allows users to display ranks as a hoverable tag like this: [R] in order to see their respective ranks. It has integration with the discord API (DiscordSRV), ensuring there is no disruption with the messages sent to the discord server. Note that this requires the DiscordSRV plugin, which is automatically detected by RankViewer.The application logs the user's location and any user with permissions can teleport to the location to view a build relating to the application (for example). Another option is to log the location of the plot the user is currently in (Requires PlotSquared dependency).

The plugin supports users having multiple ranks, and can display them all in the hoverable tab. If you are using PermissionsEx the order in which they are listed matches that of the permissions.yml file. If you are using LuckPerms then the order is dictated by assigned order.

Allows all colour codes and text changes such as italics, bold, obfuscation, etc. Currently only works with PermissionsEx and LuckPerms, however support of other rank plugins will be made available in future updates.

The hoverable tab:\
![Hoverable Tab](https://preview.ibb.co/mPQ7zK/example_rv1.png)

The content of the tab:\
![Tab Content](https://preview.ibb.co/bJZBRz/example_rv2.png)

---

## Commands

* **/rv** - *Displays Plugin Version Information*
* **/rv version** - *Displays Plugin Version Information*
* **/rv help** - *Displays Plugin Help Information*
* **/rv reload** - *Reloads The Plugin*


---

## Permissions

* **rv.use** - *allows the use of /rv*
* **rv.version** - *allows the use of /rv version*
* **rv.help** - *allows the use of /rv help*
* **rv.reload** - *allows the use of /rv reload (default: op)*


---

## Configuration and Installation

Drag and drop the most recent version of the plugin into your plugins folder. Restart or start the server, the config file will generate.

The config file contains:

> \# RankViewer Config File\
> Default-Group-Name: default\
> Use-Group-Name: false\
> Links:\
> &nbsp;&nbsp;Color: '\&9'\
> &nbsp;&nbsp;Underline: true\
> Tab-Format: '\&b\[\&cR\&b\]'\
> Discord-Config:\
> &nbsp;&nbsp;Use-Main-Discord-Channel: true\
 >&nbsp;&nbsp;Alternate-Channel: none

Here you can specify:
* The name of the default group
* Whether to use only the name and not the prefix in the hoverable tab
* The colour of any links sent in chat
* Whether links have an underline or not
* Tab colour and design format
* Whether to use the main discord channel (DiscordSRV Dependency)
* Specify an alternate discord channel (Use-Main-Discord-Channel must be true)(DiscordSRV Dependency)

---