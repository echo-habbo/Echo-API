package net.h4bbo.echo.api;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * An interface defining an advanced scheduling service that supports
 * delayed, periodic, and asynchronous task execution.
 */
public interface IAdvancedScheduler {

    /**
     * Schedule a task to run once after a specified delay.
     *
     * @param task  the task to execute
     * @param delay the delay before execution
     * @param unit  the time unit of the delay
     * @return a ScheduledFuture representing the pending completion of the task
     */
    ScheduledFuture<?> schedule(Runnable task, long delay, TimeUnit unit);

    /**
     * Schedule a task to run immediately (no delay).
     *
     * @param task the task to execute
     * @return a ScheduledFuture representing the pending completion of the task
     */
    default ScheduledFuture<?> schedule(Runnable task) {
        return schedule(task, 0, TimeUnit.MILLISECONDS);
    }

    /**
     * Schedule a repeating task to run at a fixed rate.
     *
     * @param task         the task to execute
     * @param initialDelay the initial delay before the first execution
     * @param period       the period between successive executions
     * @param unit         the time unit of the delay and period
     * @return a ScheduledFuture representing the scheduled task
     */
    ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit);

    /**
     * Schedule an asynchronous computation that provides a result after a delay.
     *
     * @param supplier the supplier providing the result
     * @param delay    the delay before computation
     * @param unit     the time unit of the delay
     * @param <T>      the type of result produced
     * @return a CompletableFuture that completes with the supplier’s result
     */
    <T> CompletableFuture<T> scheduleAsync(Supplier<T> supplier, long delay, TimeUnit unit);

    /**
     * Schedule an asynchronous computation to run immediately.
     *
     * @param supplier the supplier providing the result
     * @param <T>      the type of result produced
     * @return a CompletableFuture that completes with the supplier’s result
     */
    default <T> CompletableFuture<T> scheduleAsync(Supplier<T> supplier) {
        return scheduleAsync(supplier, 0, TimeUnit.MILLISECONDS);
    }

    /**
     * Gracefully shut down the scheduler, stopping all running and queued tasks.
     */
    void shutdown();
}
