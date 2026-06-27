package it.unicam.cs.mpgc.rpg119001.application.service.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unicam.cs.mpgc.rpg119001.domain.game.SaveGame;

import java.io.File;
import java.io.IOException;

/**
 * Service responsible for persisting and restoring game save files.
 *
 * <p>This service serializes and deserializes {@link it.unicam.cs.mpgc.rpg119001.domain.game.SaveGame}
 * objects using the Jackson library. Save files are stored as JSON,
 * providing a human-readable persistence format.</p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *     <li>Save game data to disk.</li>
 *     <li>Load saved game data.</li>
 *     <li>Check whether a save file exists.</li>
 * </ul>
 */
public class SaveService {

    private static final String SAVE_FILE = "savegame.json";

    private final ObjectMapper mapper = new ObjectMapper();

    public void save(SaveGame saveGame) throws IOException {

        File file = new File(SAVE_FILE);

        if (file.exists()) {
            if (!file.delete()) {
                throw new IOException("Cannot delete old save file");
            }
        }

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(file, saveGame);
    }

    public SaveGame load() throws IOException {
        return mapper.readValue(
                new File(SAVE_FILE),
                SaveGame.class
        );
    }

    public boolean exists() {
        return new File(SAVE_FILE).exists();
    }
}