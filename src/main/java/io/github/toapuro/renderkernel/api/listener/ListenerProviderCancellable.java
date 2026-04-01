package io.github.toapuro.renderkernel.api.listener;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ListenerProviderCancellable<T> extends ListenerProvider<Function<T, Boolean>> {

    public <R> R dispatchCancellable(BiFunction<Function<T, Boolean>, R, R> transformer, R value) {
        return super.dispatchReturnable(transformer, value);
    }
}
