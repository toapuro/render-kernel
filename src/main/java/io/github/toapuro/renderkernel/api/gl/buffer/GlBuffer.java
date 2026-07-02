package io.github.toapuro.renderkernel.api.gl.buffer;

import static io.github.toapuro.renderkernel.api.gl.buffer.GlGpuBuffer.BufferTarget;
import static io.github.toapuro.renderkernel.api.gl.buffer.GlGpuBuffer.BufferUsage;

public interface GlBuffer {
    void bind(BufferTarget target);
    void upload(BufferTarget target, GlRef ref, BufferUsage usage);
}
