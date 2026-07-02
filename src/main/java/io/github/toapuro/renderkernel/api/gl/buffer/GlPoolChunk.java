package io.github.toapuro.renderkernel.api.gl.buffer;

import io.github.toapuro.renderkernel.api.util.BufferRange;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static io.github.toapuro.renderkernel.api.gl.buffer.GlGpuBuffer.*;
import static io.github.toapuro.renderkernel.api.gl.buffer.GlGpuBuffer.BufferTarget;

@RequiredArgsConstructor
@Getter
public final class GlPoolChunk implements GlBuffer, GlRangedBuffer {
    private final GlPool parentPool;
    private final long chunkOffset;
    private final long capacity;

    @Override
    public void bind(BufferTarget target) {
        parentPool.getArena().bind(target);
    }

    public void upload(BufferTarget target, GlRef ref) {
        parentPool.getArena().uploadSub(target, chunkOffset, ref);
    }

    @Override
    public void upload(BufferTarget target, GlRef ref, BufferUsage usage) {
        upload(target, ref);
    }

    @Override
    public BufferRange getRange() {
        return new BufferRange(chunkOffset, (int) capacity);
    }
}
