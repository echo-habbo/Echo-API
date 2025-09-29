package net.h4bbo.echo.api.messages;

import net.h4bbo.echo.api.event.IEventManager;
import net.h4bbo.echo.api.network.codecs.IClientCodec;
import net.h4bbo.echo.api.network.connection.IConnectionSession;
import net.h4bbo.echo.api.plugin.JavaPlugin;
import net.h4bbo.echo.api.plugin.IPluginManager;
import org.oldskooler.inject4j.ServiceProvider;
import org.oldskooler.simplelogger4j.SimpleLog;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.*;

public class MessageHandler implements IMessageHandler {
    private final IConnectionSession connectionSession;
    private final ConcurrentHashMap<Integer, List<MessageEvent<? extends JavaPlugin>>> events = new ConcurrentHashMap<>();
    private final SimpleLog log;
    private final IEventManager eventManager;
    private final IPluginManager pluginManager;
    private final ServiceProvider serviceProvider;

    public MessageHandler(IConnectionSession connectionSession, IEventManager eventManager, IPluginManager pluginManager, ServiceProvider serviceProvider) {
        this.log = SimpleLog.of(MessageHandler.class);
        this.connectionSession = connectionSession;
        this.eventManager = eventManager;
        this.pluginManager = pluginManager;
        this.serviceProvider = serviceProvider;
    }

    public <THandler extends MessageEvent<? extends JavaPlugin>> void register(JavaPlugin plugin, int headerId, THandler handler) {
        // Objects.requireNonNull(plugin);
        Objects.requireNonNull(handler);

        handler.inject(
                this.eventManager,
                this.pluginManager,
                plugin
        );

        List<MessageEvent<? extends JavaPlugin>> list = events.computeIfAbsent(headerId, k -> new ArrayList<>());
        list.add(handler);

        log.debug("Registered handler {} for header {}", handler.getClass().getSimpleName(), headerId);
    }

    // Registers a handler by type. The handler must implement IMessageEvent and expose a public HeaderId property.
    public <THandler extends MessageEvent<? extends JavaPlugin>> void register(JavaPlugin plugin, Class<THandler> handlerClass) {
        try {
            // Constructor<THandler> ctor = handlerClass.getDeclaredConstructor();
            // THandler instance = ctor.newInstance(); // you must have these!
            THandler instance = this.serviceProvider.createInstance(handlerClass);
            int headerId = instance.getHeaderId();
            register(plugin, headerId, instance);
        } catch (Exception e) {
            log.error("Failed to register handler", e);
        }
    }

    // Deregisters all handlers of the specified type.
    public <THandler extends MessageEvent<? extends JavaPlugin>> int deregister(JavaPlugin plugin, Class<THandler> handlerClass) {
        int headerId;
        try {
            // Constructor<THandler> ctor = handlerClass.getDeclaredConstructor();
            // THandler instance = ctor.newInstance(); // you must have these!
            THandler instance = this.serviceProvider.createInstance(handlerClass);
            headerId = instance.getHeaderId();
        } catch (Exception e) {
            log.error("Failed to deregister handler", e);
            return 0;
        }
        List<MessageEvent<? extends JavaPlugin>> list = events.get(headerId);
        if (list != null) {
                int originalSize = list.size();
                list.removeIf(h -> h.getClass() == handlerClass);
                int removed = originalSize - list.size();
                if (list.isEmpty()) {
                    events.remove(headerId);
                }
                if (removed > 0) {
                    log.debug("Deregistered {} handler(s) of type {} for header {}", removed, handlerClass.getSimpleName(), headerId);
                }
                return removed;
        }
        return 0;
    }

    // Dispatches an incoming message to all handlers registered for its header identifier.
    public void handleMessage(IClientCodec packet) {
        try (packet) {
            List<MessageEvent<? extends JavaPlugin>> handlers = events.get(packet.getHeaderId());

            if (handlers == null || handlers.isEmpty()) {
                log.debug("Unknown: [{}] {} / {}", packet.getHeaderId(), packet.getHeader(), packet.getMessageBody());
                return;
            }

            log.debug("RECEIVED {}: [{} / {}] / {}", handlers.get(0).getClass().getSimpleName(), packet.getHeader(), packet.getHeaderId(), packet.getMessageBody());

            List<MessageEvent<? extends JavaPlugin>> snapshot = new ArrayList<>(handlers);

            if (snapshot.size() == 1) {
                snapshot.getFirst().handle(connectionSession.getPlayer(), packet);
                return;
            }

            for (MessageEvent<? extends JavaPlugin> handler : snapshot) {
                try (var copy = packet.copy()) {
                    handler.handle(connectionSession.getPlayer(), copy);
                }
            }
        } catch (Exception ex) {
            log.error("Error occurred in MessageHandler", ex);
        }
    }
}
