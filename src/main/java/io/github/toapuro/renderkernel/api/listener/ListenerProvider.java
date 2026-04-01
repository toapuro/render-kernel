package io.github.toapuro.renderkernel.api.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class ListenerProvider<T> {

    private final List<T> listeners = new ArrayList<>();

    public void addListener(T listener) {
        this.listeners.add(listener);
    }

    public void dispatch(Consumer<T> consumer) {
        this.listeners.forEach(consumer);
    }

    public <R> R dispatchReturnable(BiFunction<T, R, R> transformer, R value) {
        for (T listener : this.listeners) {
            value = transformer.apply(listener, value);
        }
        return value;
    }
}
