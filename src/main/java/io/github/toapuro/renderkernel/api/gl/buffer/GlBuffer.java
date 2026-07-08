package io.github.toapuro.renderkernel.api.gl.buffer;

import io.github.toapuro.renderkernel.api.memory.MemoryRef;

import static io.github.toapuro.renderkernel.api.gl.buffer.GlGpuBuffer.BufferTarget;
import static io.github.toapuro.renderkernel.api.gl.buffer.GlGpuBuffer.BufferUsage;

public interface GlBuffer {
    void bind(BufferTarget target);
    void upload(BufferTarget target, MemoryRef ref, BufferUsage usage);
    void release();

    GlGpuRef ref();
}
