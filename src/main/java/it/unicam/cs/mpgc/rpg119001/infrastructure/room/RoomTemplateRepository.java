package it.unicam.cs.mpgc.rpg119001.infrastructure.room;

import it.unicam.cs.mpgc.rpg119001.config.Constants;
import it.unicam.cs.mpgc.rpg119001.config.Constants.RoomPathConstants;

import java.util.List;
import java.util.Random;

public class RoomTemplateRepository {

    private final RoomTemplateLoader loader = new RoomTemplateLoader();

    private final Random random = new Random();

    private final List<String> templates = List.of(
            RoomPathConstants.CROSSROAD,
            RoomPathConstants.CHAMBERS,
            RoomPathConstants.CORRIDOR,
            RoomPathConstants.RING
    );

    private final List<String> boosTemplates = List.of(
            Constants.BossRoomPathConstants.BOSS_CHAMBER
    );

    public RoomTemplateDTO randomTemplate() {
        String file = templates.get(random.nextInt(templates.size()));
        return loader.load(file);
    }

    public RoomTemplateDTO randomBossTemplate() {
        String file = boosTemplates.get(random.nextInt(boosTemplates.size()));
        return loader.load(file);
    }
}