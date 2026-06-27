package it.unicam.cs.mpgc.rpg119001.infrastructure.room;

import java.util.List;

public record RoomTemplateDTO(
        String id,
        List<String> tiles
) {
}