package it.unicam.cs.mpgc.rpg119001.presentation.input;

import it.unicam.cs.mpgc.rpg119001.domain.world.Direction;
import javafx.scene.input.KeyCode;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
