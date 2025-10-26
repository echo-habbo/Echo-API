package net.h4bbo.echo.api.game.room.entities;

import net.h4bbo.echo.api.game.entity.IEntity;

public abstract class IRoomEntity {
    private final IEntity entity;

    public IRoomEntity(IEntity entity) {
        this.entity = entity;
    }

    public abstract void applyDefault();

    public IEntity getEntity() {
        return entity;
    }
}
