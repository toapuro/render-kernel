package io.github.toapuro.renderkernel.api.gl;

import io.github.toapuro.renderkernel.api.gl.buffer.GlPool;
import io.github.toapuro.renderkernel.api.util.ByteUnits;

public final class PersistentPools {
    private final GlPool sharedPool = new GlPool(8 * ByteUnits.MiB);
    private final GlPool dedicatedPool = new GlPool(8 * ByteUnits.MiB);

    public GlPool shared() {
        return sharedPool;
    }

    public GlPool dedicated() {
        return dedicatedPool;
    }
}
