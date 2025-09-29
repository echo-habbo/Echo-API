package net.h4bbo.echo.api.network.connection;

import io.netty.channel.Channel;

import java.util.List;

/**
 * Manages network connections and their associated sessions.
 * Provides methods to add, remove, and retrieve connection sessions.
 */
public interface IConnectionManager extends IConnectionSend {
    void addConnection(IConnectionSession session);

    void removeConnection(IConnectionSession session);

    IConnectionSession getConnection(Channel channel);

    List<IConnectionSession> getConnections();
}