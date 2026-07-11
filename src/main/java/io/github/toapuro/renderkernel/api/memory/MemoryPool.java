package io.github.toapuro.renderkernel.api.memory;

import io.github.toapuro.renderkernel.api.KernelApi;
import io.github.toapuro.renderkernel.api.util.FreeListAllocator;
import lombok.Getter;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;

public final class MemoryPool {
    private final List<Chunk> chunks = new ArrayList<>();

    private long lastChunkCapacity;

    @Getter
    private long capacity;

    public MemoryPool(long initialSize) {
        lastChunkCapacity = initialSize;
        capacity = initialSize;

        chunks.add(new Chunk(this, KernelApi.allocateMemory(initialSize), FreeListAllocator.create(initialSize)));
    }

    private Chunk growPool() {
        lastChunkCapacity *= 2;
        capacity += lastChunkCapacity;

        Chunk chunk = new Chunk(this, KernelApi.allocateMemory(lastChunkCapacity), FreeListAllocator.create(lastChunkCapacity));
        chunks.add(chunk);
        return chunk;
    }

    // sub-allocate buffer
    public MemoryRegionBuffer suballoc(int size) {
        for (Chunk chunk : chunks) {
            long offset = chunk.allocator.allocate(size);
            if(offset >= 0) {
                return new MemoryRegionBuffer(chunk, chunk.buffer.getAddress() + offset, size);
            }
        }

        while (lastChunkCapacity < size) {
            Chunk chunk = growPool();

            long offset = chunk.allocator.allocate(size);
            if (offset >= 0) {
                return new MemoryRegionBuffer(chunk, offset, size);
            }
        }
        throw new IllegalStateException("Unable to sub-allocate buffer region: size:" + size);
    }

    public void release(MemoryRegionBuffer regionBuffer) {
        if (!chunks.contains(regionBuffer.getPoolChunk()))
            throw new IllegalStateException("Buffer was allocated by another memory pool");

        regionBuffer.getPoolChunk().allocator.release(regionBuffer.getAddress(), regionBuffer.getSize());
    }

    @ApiStatus.Internal
    public void clear() {
        for (Chunk chunk : chunks) {
            chunk.buffer.release();
        }
        chunks.clear();
    }

    public record Chunk(MemoryPool pool, MemoryBuffer buffer, FreeListAllocator allocator) {
    }
}
