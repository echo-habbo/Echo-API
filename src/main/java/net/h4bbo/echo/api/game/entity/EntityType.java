package net.h4bbo.echo.api.game.entity;

import net.h4bbo.echo.api.game.player.IPlayer;

public enum EntityType {
    PLAYER(IPlayer.class);

    final Class<? extends IEntity> clazz;

    EntityType(Class<? extends IEntity> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends IEntity> getEntityClass() {
        return clazz;
    }
}
