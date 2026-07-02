package io.github.toapuro.renderkernel.api.gl;

import io.github.toapuro.renderkernel.api.gl.buffer.GlPool;
import io.github.toapuro.renderkernel.api.gl.state.GlStateTracker;
import lombok.Getter;

public final class GlApi {
    @Getter
    private static final GlStateTracker state = new GlStateTracker();
    @Getter
    private static final GlPool transientPool = new GlPool(8389000);
    @Getter
    private static final GlPool persistentPool = new GlPool(8389000);
}
