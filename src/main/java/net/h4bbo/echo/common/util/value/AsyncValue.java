package net.h4bbo.echo.common.util.value;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function; /**
 * A value that is computed asynchronously using CompletableFuture.
 *
 * @param <T> the type of the contained value
 */
public class AsyncValue<T> {
    private final CompletableFuture<T> future;

    /**
     * Creates a new AsyncValue wrapping a CompletableFuture.
     *
     * @param future the CompletableFuture to wrap
     */
    public AsyncValue(CompletableFuture<T> future) {
        this.future = future;
    }

    /**
     * Returns the underlying CompletableFuture.
     *
     * @return the CompletableFuture
     */
    public CompletableFuture<T> value() {
        return future;
    }

    /**
     * Applies a transformation function to the future result.
     *
     * @param <R> the result type
     * @param mapper the transformation function
     * @return a new AsyncValue with the transformed result
     */
    public <R> AsyncValue<R> apply(Function<T, R> mapper) {
        return new AsyncValue<>(future.thenApply(mapper));
    }

    /**
     * Applies a transformation function asynchronously to the future result.
     *
     * @param <R> the result type
     * @param mapper the transformation function
     * @return a new AsyncValue with the transformed result
     */
    public <R> AsyncValue<R> applyAsync(Function<T, R> mapper) {
        return new AsyncValue<>(future.thenApplyAsync(mapper));
    }

    /**
     * Blocks and gets the result from the CompletableFuture.
     *
     * @return the computed value
     */
    public T get() {
        try {
            return future.get();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get async value", e);
        }
    }

    @Override
    public String toString() {
        return "AsyncValue{future=" + (future.isDone() ? "completed" : "pending") + "}";
    }
}
