package net.h4bbo.echo.api.messages;

import net.h4bbo.echo.api.network.codecs.IClientCodec;
import net.h4bbo.echo.api.plugin.JavaPlugin;

public interface IMessageHandler {
    <THandler extends MessageEvent<? extends JavaPlugin>> void register(JavaPlugin plugin, int headerId, THandler handler);

    <THandler extends MessageEvent<? extends JavaPlugin>> void register(JavaPlugin plugin, Class<THandler> handlerClass);

    <THandler extends MessageEvent<? extends JavaPlugin>> int deregister(JavaPlugin plugin, Class<THandler> handlerClass);

    void handleMessage(IClientCodec packet);
}
