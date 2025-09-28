package net.h4bbo.echo.api.event;

import net.h4bbo.echo.api.event.types.IEvent;
import net.h4bbo.echo.api.plugin.JavaPlugin;

import java.util.function.Consumer;

public interface IEventManager {

    /**
     * Scan an object for @EventHandler methods and register them.
     */
    void register(JavaPlugin plugin, Object listener);

    /**
     * Remove all handlers belonging to a given listener instance.
     */
    void unregister(Object listener);

    /**
     * Fluent subscription without annotations/reflection.
     */
    <TEvent extends IEvent> AutoCloseable subscribe(
            JavaPlugin plugin,
            Consumer<TEvent> handler,
            Class<TEvent> eventType,
            EventPriority priority,
            boolean ignoreCancelled,
            boolean once
    );

    /**
     * Publish/dispatch an event to all matching handlers.
     * Returns true if the event was cancelled, false otherwise.
     */
    boolean publish(IEvent ev);

}
