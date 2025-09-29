package net.h4bbo.echo.api.network.connection;

import io.netty.channel.Channel;

/**
 * Manages network connections and their associated sessions.
 * Provides methods to add, remove, and retrieve connection sessions.
 */
public interface IConnectionManager extends IConnectionSend {

    /**
     * Adds a new connection for the given channel.
     *
     * @param channel the network channel to add
     * @throws IllegalArgumentException if channel is null
     */
    void addConnection(Channel channel);

    /**
     * Removes an existing connection for the given channel.
     *
     * @param channel the network channel to remove
     * @throws IllegalArgumentException if channel is null
     */
    void removeConnection(Channel channel);

    /**
     * Retrieves the connection session for the given channel.
     *
     * @param channel the network channel to look up
     * @return the ConnectionSession associated with the channel, or null if not found
     * @throws IllegalArgumentException if channel is null
     */
    IConnectionSession getConnection(Channel channel);
}