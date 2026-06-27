package it.unicam.cs.mpgc.rpg119001.infrastructure.room;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class RoomTemplateLoader {

    private final ObjectMapper mapper = new ObjectMapper();

    public RoomTemplateDTO load(String filePath) {

        InputStream is = getClass().getResourceAsStream(filePath);

        if (is == null) {
            throw new IllegalArgumentException(
                    "Room not found: " + filePath
            );
        }

        try {
            return mapper.readValue(is, RoomTemplateDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}