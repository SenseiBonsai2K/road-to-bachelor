# Road to Bachelor

Project developed for the **Modellazione e Gestione della Conoscenza** course at Camerino University.

The project consists of a simple 2D RPG Dungeon Crawler with top-down view developed in **Java** using **JavaFX**.
The player can select a character, explore rooms generated from templates, fight enemies and level up.

## Key features

- Start menu where you can start a new game, continue a saved game or exit the game.
- Initial character selection via a preset system.
- Character scaling system based on experience gained by defeating enemies.
- Exploration of dungeons made up of rooms.
- Room generation based on JSON templates.
- Orthogonal grid-based movement system with support for:
  - pathfinding
  - collisions with tiles and entities
- Combat with management of:
  - attack range
  - line of sight
  - movement and attack speed
  - collision handling
- Enemy generation via presets
- Scaling of enemy difficulty based on the dungeon level
- Basic enemy AI for movement and combat
- Progress to the next room unlocked only after all enemies in the current room have been defeated
- Ability to save the game only under specific conditions (room completed)
- Game Over screen if the player dies, with the option to return to the main menu or exit the game

## Extensibility exercises

The project includes a number of features developed as an exercise in architectural extensibility, in particular the item management system, character presets and room templates.

- A system of equipable items (Weapons, Armour) with bonuses/penalties to character statistics.
- A consumables system with effects applicable to the player.
- Modular effects system (Effects) to easily extend new mechanics (e.g. heal, speed boost).
- Extensible architecture allowing the introduction of new types of items, effects, enemies, players and rooms (via dedicated JSON files).
- Separation of the domain model and application logic to facilitate system extension.

## Technologies used

- Java 21
- JavaFX 21
- Gradle (with Gradle Wrapper)
- Jackson Databind 2.17.2

## Requirements

- JDK 21

The project uses the Gradle Wrapper, so there is no need to install Gradle manually.

## Execution

Clone the repository and run:

### Build

```bash
./gradlew build
```
On Windows:
```bash
gradlew.bat build
```

### Start
```bash
./gradlew run
```
On Windows:
```bash
gradlew.bat run
```

## Project structure

- **domain**: domain model and business logic
- **application**: application services and coordination of operations
- **presentation**: JavaFX controllers and graphical rendering
- **infrastructure**: factory, presets and room loading
- **config**: configuration constants

## Use of AI tools

During development, Artificial Intelligence tools (OpenAI’s ChatGPT) were used exclusively to support programming.

In particular, AI was used for:

- architectural suggestions
- generating documentation (Javadoc and Wiki)
- theoretical support (e.g. Manhattan distance, BFS)
- suggestions for refactoring and code improvement (e.g. use of Runnable, Consumer, separation of responsibilities)

## Future developments

The current version of the project focuses on the implementation of the game logic and on a modular architecture designed to be easily extended. Several improvements can be introduced in future iterations, including:

- **Audio system**
  - Introduction of a dedicated `SoundManager` responsible for background music, sound effects and volume management.
  - Different soundtracks for menus, exploration and combat.
  - Audio feedback for attacks, enemy deaths and player interactions.

- **Graphical improvements**
  - Attack animations and visual effects.
  - Particle effects for combat and level progression.
  - Improved environment sprites and character animations.

- **HUD improvements**
  - Health bars displayed above characters.
  - Visual indicators for movement and attack availability (cooldowns).
  - Experience bar and dungeon progression indicators.
  - Better visualization of the currently selected target.

- **Gameplay improvements**
  - Equipment management and inventory integration.
  - Consumable item usage.
  - Different enemy behaviours and advanced AI.
  - Additional player classes and enemy archetypes.
  - Boss-specific mechanics and abilities.

- **Content expansion**
  - New room templates.
  - Additional dungeon themes.
  - New weapons, armour and consumable effects.
  - Greater variety of enemies and encounters.

- **Technical improvements**
  - Configurable game settings (difficulty, audio, graphics).
  - Save game even if the room is not completed (partially implemented)
  - Additional save slots.
  - Unit and integration tests for the application services.
