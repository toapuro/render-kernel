package io.github.toapuro.renderkernel.api.model;

import io.github.toapuro.renderkernel.api.KernelApi;
import io.github.toapuro.renderkernel.api.gl.GlApi;
import io.github.toapuro.renderkernel.api.gl.buffer.GlRangedBuffer;
import io.github.toapuro.renderkernel.api.memory.MemoryBuffer;

public class Mesh {
    private final GlRangedBuffer vaoGpuBuffer;
    private final MemoryBuffer vaoCpuBuffer;

    public Mesh() {
        vaoGpuBuffer = GlApi.getPersistentPool().suballocateChunk(1024);
        vaoCpuBuffer = KernelApi.allocateMemory(1024);
    }
}
