package net.h4bbo.echo.common.util.value;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier; /**
 * A lazily-evaluated value that computes its result only when accessed.
 * Once computed, the result is cached for subsequent accesses.
 *
 * @param <T> the type of the contained value
 */
public class LazyValue<T> {
    private final Supplier<T> supplier;
    private volatile T cachedValue;
    private volatile boolean computed = false;

    /**
     * Creates a new LazyValue with the given supplier.
     *
     * @param supplier the supplier that computes the value
     */
    public LazyValue(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    /**
     * Gets the value, computing it if necessary.
     * The result is cached after the first computation.
     *
     * @return a Lazy wrapper containing the computed value
     */
    public Lazy<T> value() {
        return new Lazy<>(this::get);
    }

    /**
     * Computes and returns the value, using cached result if available.
     *
     * @return the computed value
     */
    private T get() {
        if (!computed) {
            synchronized (this) {
                if (!computed) {
                    cachedValue = supplier.get();
                    computed = true;
                }
            }
        }
        return cachedValue;
    }

    /**
     * Applies a transformation function eagerly to the lazy value.
     *
     * @param <R> the result type
     * @param mapper the transformation function
     * @return a new Value containing the transformed result
     */
    public <R> Value<R> apply(Function<T, R> mapper) {
        return new Value<>(mapper.apply(get()));
    }

    /**
     * Applies a transformation function lazily.
     *
     * @param <R> the result type
     * @param mapper the transformation function
     * @return a new LazyValue that chains the transformations
     */
    public <R> LazyValue<R> applyLazy(Function<T, R> mapper) {
        return new LazyValue<>(() -> mapper.apply(get()));
    }

    /**
     * Applies a transformation function asynchronously.
     *
     * @param <R> the result type
     * @param mapper the transformation function
     * @return an AsyncValue wrapping a CompletableFuture
     */
    public <R> AsyncValue<R> applyAsync(Function<T, R> mapper) {
        CompletableFuture<R> future = CompletableFuture.supplyAsync(() -> mapper.apply(get()));
        return new AsyncValue<>(future);
    }

    @Override
    public String toString() {
        return computed ? "LazyValue{computed=" + cachedValue + "}" : "LazyValue{not computed}";
    }
}
