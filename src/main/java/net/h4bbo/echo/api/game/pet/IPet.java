package net.h4bbo.echo.api.game.pet;


import net.h4bbo.echo.api.commands.CommandSender;
import net.h4bbo.echo.api.game.entity.IEntity;
import net.h4bbo.echo.api.network.connection.IConnectionSession;

import java.util.concurrent.CompletableFuture;

/**
 * Defines the contract for a player within the game server.
 */
public interface IPet extends IEntity, CommandSender {

}
