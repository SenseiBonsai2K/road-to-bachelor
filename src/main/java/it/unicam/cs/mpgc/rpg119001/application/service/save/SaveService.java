package it.unicam.cs.mpgc.rpg119001.application.service.save;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unicam.cs.mpgc.rpg119001.domain.game.SaveGame;

import java.io.File;
import java.io.IOException;

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