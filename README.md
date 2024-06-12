# Path Speed Boost Plugin

This Minecraft plugin increases the player's speed by a configurable percentage when moving on a dirt path.

## Installation

1. Download the latest release of the plugin.
2. Place the JAR file into the `plugins` folder of your Minecraft server.
3. Restart the server.

## Configuration

To adjust the speed multiplier, edit the `config.yml` file located in the `plugins/PathSpeedBoost` directory.

Example `config.yml`:
```yaml
# Path Speed Boost Plugin Configuration
# Adjust the speed multiplier for players running on dirt paths.
# The multiplier determines how much faster the player will move compared to the default speed.
# For example:
#   1.0 = 0% increase (default speed)
#   1.1 = 10% increase
#   1.2 = 20% increase
#   1.3 = 30% increase
#   1.4 = 40% increase (default)
#   1.5 = 50% increase
#   1.6 = 60% increase
#   1.7 = 70% increase
#   1.8 = 80% increase
#   1.9 = 90% increase
#   2.0 = 100% increase (double speed)

speed-multiplier: 1.4
```
## Commands

- `/psb reload` - Reloads the plugin configuration.

## Permissions

- `pathspeedboost.reload` - Allows the user to reload the Path Speed Boost Plugin configuration. Default: op

## Development

To build the plugin, use Maven:

```bash
mvn clean package
