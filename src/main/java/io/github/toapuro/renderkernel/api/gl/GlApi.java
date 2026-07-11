package io.github.toapuro.renderkernel.api.gl;

import io.github.toapuro.renderkernel.api.gl.buffer.GlPool;
import io.github.toapuro.renderkernel.api.gl.state.GlStateTracker;
import io.github.toapuro.renderkernel.api.util.ByteUnits;

public final class GlApi {
    private static final GlStateTracker state = new GlStateTracker();
    private static final GlPool transientPool = new GlPool(8 * ByteUnits.MiB);
    private static final PersistentPools persistentPool = new PersistentPools();

    public static GlStateTracker state() {
        return state;
    }

    public static GlPool transientPool() {
        return transientPool;
    }

    public static PersistentPools persistentPools() {
        return persistentPool;
    }
}
