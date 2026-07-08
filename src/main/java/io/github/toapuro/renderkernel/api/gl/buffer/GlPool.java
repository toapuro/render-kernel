package io.github.toapuro.renderkernel.api.gl.buffer;

import io.github.toapuro.renderkernel.api.util.FreeListAllocator;
import lombok.Getter;

public final class GlPool {
    @Getter
    private final GlGpuBuffer arena;
    private final FreeListAllocator allocator;

    @Getter
    private long capacity;

    public GlPool(long initialSize) {
        arena = new GlGpuBuffer(initialSize);
        allocator = FreeListAllocator.create(initialSize);
        capacity = initialSize;
    }

    public GlRegionBuffer suballoc(int size) {
        long offset = allocator.allocate(size);
        if(offset >= 0) {
            return new GlRegionBuffer(this, offset, size);
        }

        allocator.add(capacity, size);
        capacity += size;

        // re-allocate

        long newOffset = allocator.allocate(size);
        if(newOffset < 0) throw new IllegalStateException("Could not sub-allocate");
        return new GlRegionBuffer(this, newOffset, size);
    }

    public void release(GlRegionBuffer buffer) {
        allocator.release(buffer.getChunkOffset(), buffer.getCapacity());
    }
}
