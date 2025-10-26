package net.h4bbo.echo.api.game.room.managers;

import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.AttributeMap;
import io.netty.util.DefaultAttributeMap;
import net.h4bbo.echo.api.game.entity.IEntity;
import net.h4bbo.echo.api.game.player.IPlayer;
import net.h4bbo.echo.api.game.room.IRoom;
import net.h4bbo.echo.storage.models.room.RoomData;

public abstract class IRoomEntityManager {
    private final IRoom room;

    public IRoomEntityManager(IRoom room) {
        this.room = room;
    }

    public IRoom getRoom() {
        return room;
    }

    public abstract void enter(IEntity entity);
}
