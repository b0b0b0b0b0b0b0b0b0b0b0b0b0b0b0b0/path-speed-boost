## Foreword
I understand that this is a simple plugin, and I mostly made it for myself because I believe that this feature should have been included in Minecraft from the start. Using paths as mere decoration on a server is not something I want, so I created this small plugin for players. This way, they have an incentive not only to beautify their builds but also to receive a buff for doing so.

# Path Speed Boost Plugin (PSB)

This Minecraft plugin adjusts the player's speed based on their location, providing configurable speed boosts on grass paths and configurable speed reductions in tall grass.

## Installation
1. Download the latest release of the plugin.
2. Place the JAR file into the `plugins` folder of your Minecraft server.
3. Restart the server.

## Configuration

To adjust the speed multipliers, edit the `config.yml` file located in the `plugins/PathSpeedBoost` directory.

### Example `config.yml`:
```yaml
# Path Speed Boost Plugin Configuration

# Language setting: 'en' for English, 'ru' for Russian
language: 'en'

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

# Adjust the speed multiplier for players moving through tall grass.
# For example:
#   1.0 = 0% decrease (default speed)
#   0.9 = 10% decrease
#   0.8 = 20% decrease
#   0.7 = 30% decrease
#   0.6 = 40% decrease
#   0.5 = 50% decrease (default)
#   0.4 = 60% decrease
#   0.3 = 70% decrease
#   0.2 = 80% decrease
#   0.1 = 90% decrease
#   0.0 = 100% decrease (no movement)

tall-grass-speed-multiplier: 0.5

# Configuration file version (Do not change)
version: 1
```

## Commands

- `/psb reload` - Reloads the plugin configuration.
- `/psb gui` - Opens the plugin settings GUI.

## Permissions

- `pathspeedboost.admin` - Allows the user to reload the Path Speed Boost Plugin configuration and open the settings GUI. Default: op

## Development

To build the plugin, use Maven:

```bash
mvn clean package
