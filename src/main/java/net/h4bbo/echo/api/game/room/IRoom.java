package net.h4bbo.echo.api.game.room;

import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.AttributeMap;
import io.netty.util.DefaultAttributeMap;
import net.h4bbo.echo.api.commands.CommandSender;
import net.h4bbo.echo.api.game.entity.IEntity;
import net.h4bbo.echo.api.game.room.managers.IRoomEntityManager;
import net.h4bbo.echo.api.network.connection.IConnectionSend;
import net.h4bbo.echo.api.network.connection.IConnectionSession;
import net.h4bbo.echo.storage.models.room.RoomData;

import java.util.concurrent.CompletableFuture;

public abstract class IRoom implements AttributeMap {
    private final AttributeMap attributeMap = new DefaultAttributeMap();

    public IRoom(RoomData roomData) {
        this.attributeMap.attr(RoomData.DATA_KEY).setIfAbsent(roomData);
    }

    public abstract IRoomEntityManager getEntityManager();

    public RoomData getData() {
        return this.attributeMap
                .attr(RoomData.DATA_KEY)
                .get();
    }

    @Override
    public <T> Attribute<T> attr(AttributeKey<T> attributeKey) {
        return attributeMap.attr(attributeKey);
    }

    @Override
    public <T> boolean hasAttr(AttributeKey<T> attributeKey) {
        return attributeMap.hasAttr(attributeKey);
    }
}
