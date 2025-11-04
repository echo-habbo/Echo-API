package net.h4bbo.echo.api.game.player;


import net.h4bbo.echo.api.commands.CommandSender;
import net.h4bbo.echo.api.game.entity.IEntity;
import net.h4bbo.echo.api.network.connection.IConnectionSend;
import net.h4bbo.echo.api.network.connection.IConnectionSession;

import java.util.concurrent.CompletableFuture;

/**
 * Defines the contract for a player within the game server.
 */
public interface IPlayer extends IEntity, CommandSender {
    boolean isAuthenticated();
    void setAuthenticated(boolean flag);
    IConnectionSession getConnection();
    void disconnect();
}
