package io.github.toapuro.renderkernel.api.gl.buffer;

import io.github.toapuro.renderkernel.api.util.BufferRange;

public interface GlRangedBuffer extends GlBuffer {
    BufferRange getRange();
}
