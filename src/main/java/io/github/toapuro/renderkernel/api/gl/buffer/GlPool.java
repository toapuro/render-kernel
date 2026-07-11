package io.github.toapuro.renderkernel.api.gl.buffer;

import io.github.toapuro.renderkernel.api.util.FreeListAllocator;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public final class GlPool {
    private final List<Chunk> chunks = new ArrayList<>();

    private long lastChunkCapacity;

    @Getter
    private long capacity;

    public GlPool(long initialSize) {
        lastChunkCapacity = initialSize;
        capacity = initialSize;

        chunks.add(new Chunk(this, new GlGpuBuffer(initialSize), FreeListAllocator.create(initialSize)));
    }

    public Chunk growPool() {
        lastChunkCapacity *= 2;
        capacity += lastChunkCapacity;

        Chunk chunk = new Chunk(this, new GlGpuBuffer(lastChunkCapacity), FreeListAllocator.create(lastChunkCapacity));
        chunks.add(chunk);
        return chunk;
    }

    public GlRegionBuffer suballoc(int size) {
        for (Chunk chunk : chunks) {
            long offset = chunk.allocator.allocate(size);
            if (offset >= 0) {
                return new GlRegionBuffer(chunk, offset, size);
            }
        }

        while (lastChunkCapacity < size) {
            Chunk chunk = growPool();

            long offset = chunk.allocator.allocate(size);
            if (offset >= 0) {
                return new GlRegionBuffer(chunk, offset, size);
            }
        }
        throw new IllegalStateException("Unable to sub-allocate buffer region: size:" + size);
    }

    public void release(GlRegionBuffer buffer) {
        if (!chunks.contains(buffer.getPoolChunk()))
            throw new IllegalStateException("Buffer was allocated by another memory pool");

        buffer.getPoolChunk().allocator().release(buffer.getChunkOffset(), buffer.getCapacity());
    }

    public record Chunk(GlPool pool, GlGpuBuffer buffer, FreeListAllocator allocator) {
    }
}
