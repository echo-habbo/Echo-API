package net.h4bbo.echo.api.game.entity;

import io.netty.util.AttributeMap;
import net.h4bbo.echo.api.game.room.entities.RoomEntity;
import net.h4bbo.echo.api.network.connection.IConnectionSend;

/**
 * Defines the contract for a player within the game server.
 */
public interface IEntity extends AttributeMap, IConnectionSend {
    /**
     * Gets the type.
     *
     * @return the type
     */
    EntityType getType();
    RoomEntity getRoomEntity();

    default boolean isInRoom() { return this.getRoomEntity() != null
            && this.getRoomEntity().getRoom() != null
            && this.getRoomEntity().getInstanceId() >= 0;
    }
}
