package it.unicam.cs.mpgc.rpg119001.infrastructure.room;

import it.unicam.cs.mpgc.rpg119001.config.Constants;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RoomTemplateRepository {

    private final RoomTemplateLoader loader = new RoomTemplateLoader();

    private final List<String> templates = List.of(
            Constants.RoomPathConstants.CROSSROAD,
            Constants.RoomPathConstants.CHAMBERS,
            Constants.RoomPathConstants.CORRIDOR,
            Constants.RoomPathConstants.RING
    );

    private final List<String> bossTemplates = List.of(
            Constants.BossRoomPathConstants.BOSS_CHAMBER
    );

    private final Map<String, String> templateIndex = new HashMap<>();

    public RoomTemplateRepository() {
        indexTemplates();
    }

    private void indexTemplates() {
        for (String file : templates) {
            RoomTemplateDTO dto = loader.load(file);
            templateIndex.put(dto.id(), file);
        }

        for (String file : bossTemplates) {
            RoomTemplateDTO dto = loader.load(file);
            templateIndex.put(dto.id(), file);
        }
    }

    public RoomTemplateDTO randomTemplate() {
        String file = templates.get(
                ThreadLocalRandom.current().nextInt(templates.size())
        );
        return loader.load(file);
    }

    public RoomTemplateDTO randomBossTemplate() {
        String file = bossTemplates.get(
                ThreadLocalRandom.current().nextInt(bossTemplates.size())
        );
        return loader.load(file);
    }

    public RoomTemplateDTO getById(String id) {

        String file = templateIndex.get(id);

        if (file == null) {
            throw new IllegalArgumentException("Template not found: " + id);
        }

        return loader.load(file);
    }
}