package net.h4bbo.echo.api.game.room.entities;

import io.netty.util.AttributeKey;
import net.h4bbo.echo.api.game.entity.IEntity;
import net.h4bbo.echo.api.game.room.IRoom;
import net.h4bbo.echo.storage.models.user.UserData;

public abstract class RoomEntity {
    private int instanceId;

    public static final AttributeKey<RoomEntity> DATA_KEY = AttributeKey.valueOf(RoomEntity.class.getCanonicalName());

    private final IEntity entity;
    private final IRoom room;

    public RoomEntity(IRoom room, IEntity entity) {
        this.room = room;
        this.entity = entity;
    }

    public abstract void resetState();

    public IEntity getEntity() {
        return entity;
    }

    public IRoom getRoom() {
        return room;
    }

    public int getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(int instanceId) {
        this.instanceId = instanceId;
    }
}
