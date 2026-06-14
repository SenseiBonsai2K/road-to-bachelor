package it.unicam.cs.mpgc.rpg119001.game;

import it.unicam.cs.mpgc.rpg119001.game.world.Direction;
import javafx.scene.input.KeyCode;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class InputMapper {
    private static final Map<KeyCode, Direction> KEY_TO_DIRECTION = Map.of(
            KeyCode.W, Direction.UP,
            KeyCode.S, Direction.DOWN,
            KeyCode.A, Direction.LEFT,
            KeyCode.D, Direction.RIGHT
    );

    public Optional<Direction> getDirection(List<KeyCode> pressedKeys) {

        for (int i= pressedKeys.size() - 1; i >= 0; i--) {
            Direction dir = KEY_TO_DIRECTION.get(pressedKeys.get(i));
            if (dir != null) {
                return Optional.of(dir);
            }
        }

        return Optional.empty();
    }
}
