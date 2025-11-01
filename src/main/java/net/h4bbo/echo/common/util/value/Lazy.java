package net.h4bbo.echo.common.util.value;

import java.util.function.Supplier; /**
 * A simple wrapper that provides lazy access to a value.
 *
 * @param <T> the type of the contained value
 */
public class Lazy<T> {
    private final Supplier<T> supplier;

    public Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    /**
     * Gets the value from the supplier.
     *
     * @return the value
     */
    public T get() {
        return supplier.get();
    }

    @Override
    public String toString() {
        return "Lazy{...}";
    }
}
