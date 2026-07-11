package io.github.toapuro.renderkernel.api.memory.pooling;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ObjectPool<T> {
    private final ArrayDeque<T> pool;
    private final Supplier<T> factory;
    @Nullable
    private final Consumer<T> objectReset;

    public ObjectPool(int initialSize, Supplier<T> factory) {
        this(initialSize, factory, null);
    }

    public ObjectPool(int initialSize, Supplier<T> factory, @Nullable Consumer<T> objectReset) {
        this.pool = new ArrayDeque<>(initialSize);
        this.factory = factory;
        this.objectReset = objectReset;
    }

    public T acquire() {
        T obj = pool.pollLast();
        return obj != null ? obj : factory.get();
    }

    public void release(T obj) {
        if (obj == null) {
            return;
        }

        if (objectReset != null) {
            objectReset.accept(obj);
        }

        pool.addLast(obj);
    }

    public void preallocate(int count) {
        for (int i = 0; i < count; i++) {
            pool.addLast(factory.get());
        }
    }

    public int size() {
        return pool.size();
    }

    public void clear() {
        pool.clear();
    }
}
