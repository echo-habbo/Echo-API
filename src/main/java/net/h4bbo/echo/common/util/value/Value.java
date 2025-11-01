package net.h4bbo.echo.common.util.value;// Value.java
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A container that holds a value and supports both eager and lazy transformations.
 * Provides synchronous and asynchronous computation capabilities.
 *
 * @param <T> the type of the contained value
 */
public record Value<T>(T value) {
    /**
     * Creates a new Value with the given value.
     *
     * @param value the value to wrap
     */
    public Value {
    }

    /**
     * Returns the contained value.
     *
     * @return the value
     */
    @Override
    public T value() {
        return value;
    }

    /**
     * Applies a transformation function eagerly and returns a new Value.
     *
     * @param <R>    the result type
     * @param mapper the transformation function
     * @return a new Value containing the transformed value
     */
    public <R> Value<R> apply(Function<T, R> mapper) {
        return new Value<>(mapper.apply(value));
    }

    /**
     * Applies a transformation function lazily and returns a LazyValue.
     * The computation is deferred until value() is called on the LazyValue.
     *
     * @param <R>    the result type
     * @param mapper the transformation function
     * @return a LazyValue that will compute the result when accessed
     */
    public <R> LazyValue<R> applyLazy(Function<T, R> mapper) {
        return new LazyValue<>(() -> mapper.apply(value));
    }

    /**
     * Applies a transformation function asynchronously using CompletableFuture.
     *
     * @param <R>    the result type
     * @param mapper the transformation function
     * @return an AsyncValue wrapping a CompletableFuture
     */
    public <R> AsyncValue<R> applyAsync(Function<T, R> mapper) {
        CompletableFuture<R> future = CompletableFuture.supplyAsync(() -> mapper.apply(value));
        return new AsyncValue<>(future);
    }

    @Override
    public String toString() {
        return "Value{" + value + "}";
    }
}
