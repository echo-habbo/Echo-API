package net.h4bbo.echo.api.game.entity;

import net.h4bbo.echo.api.game.pet.IPet;
import net.h4bbo.echo.api.game.player.IPlayer;

public enum EntityType {
    PLAYER(IPlayer.class, true),
    PET(IPet.class, false);

    private final Class<? extends IEntity> clazz;
    private final boolean isClient;

    EntityType(Class<? extends IEntity> clazz, boolean isClient) {
        this.clazz = clazz;
        this.isClient = isClient;
    }

    public Class<? extends IEntity> getEntityClass() {
        return clazz;
    }

    public boolean isClient() {
        return isClient;
    }
}
