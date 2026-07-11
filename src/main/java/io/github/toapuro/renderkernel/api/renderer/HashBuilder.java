package io.github.toapuro.renderkernel.api.renderer;

import org.jetbrains.annotations.Nullable;

/**
 * Builder for the hash chain used to detect renderer changes
 */
public class HashBuilder {
    private static final long FNV_OFFSET = 0xcbf29ce484222325L;
    private static final long FNV_PRIME = 0x100000001b3L;

    private long hash;

    public HashBuilder() {
        this.hash = FNV_OFFSET;
    }

    /**
     * The received object has {@link Object#hashCode} overridden and must be changed based on its contents.
     * @param obj Any object
     * @return this
     */
    public HashBuilder update(@Nullable Object obj) {
        return update((obj == null) ? 0L : obj.hashCode());
    }

    public HashBuilder update(long value) {
        hash ^= value;
        hash *= FNV_PRIME;
        return this;
    }

    public long build() {
        return hash;
    }
}
