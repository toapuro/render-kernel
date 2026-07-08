package io.github.toapuro.renderkernel.api.gl.buffer;

import io.github.toapuro.renderkernel.api.memory.MemoryRef;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static io.github.toapuro.renderkernel.api.gl.buffer.GlGpuBuffer.BufferTarget;
import static io.github.toapuro.renderkernel.api.gl.buffer.GlGpuBuffer.BufferUsage;

@RequiredArgsConstructor
@Getter
public final class GlRegionBuffer implements GlBuffer {
    private final GlPool parentPool;
    private final long chunkOffset;
    private final long capacity;

    @Override
    public void bind(BufferTarget target) {
        parentPool.getArena().bind(target);
    }

    public void upload(BufferTarget target, MemoryRef ref) {
        parentPool.getArena().uploadSub(target, chunkOffset, ref);
    }

    @Override
    public void upload(BufferTarget target, MemoryRef ref, BufferUsage usage) {
        upload(target, ref);
    }

    @Override
    public void release() {
        parentPool.release(this);
    }

    @Override
    public GlGpuRef ref() {
        return GlGpuRef.ref(parentPool.getArena().getId(), chunkOffset, capacity);
    }
}
