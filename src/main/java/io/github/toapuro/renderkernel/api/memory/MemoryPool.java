package io.github.toapuro.renderkernel.api.memory;

import io.github.toapuro.renderkernel.api.KernelApi;
import io.github.toapuro.renderkernel.api.util.FreeListAllocator;
import lombok.Getter;

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

        chunks.add(new Chunk(KernelApi.allocateMemory(initialSize), FreeListAllocator.create(initialSize)));
    }

    private Chunk expandChunk() {
        lastChunkCapacity *= 2;
        capacity += lastChunkCapacity;

        Chunk chunk = new Chunk(KernelApi.allocateMemory(lastChunkCapacity), FreeListAllocator.create(lastChunkCapacity));
        chunks.add(chunk);
        return chunk;
    }

    // sub-allocate buffer
    public MemoryBuffer suballoc(int size) {
        for (Chunk chunk : chunks) {
            long offset = chunk.allocator.allocate(size);
            if(offset >= 0) {
                return new MemoryBuffer(chunk.buffer.getAddress() + offset, size);
            }
        }

        Chunk newChunk = this.expandChunk();

        // re-allocate
        long newOffset = newChunk.allocator.allocate(size);
        if(newOffset < 0) throw new IllegalStateException("Could not sub-allocate");
        return new MemoryBuffer(newChunk.buffer.getAddress() + newOffset, size);
    }

    public void clear() {
        for (Chunk chunk : chunks) {
            chunk.buffer.free();
        }
        chunks.clear();
    }

    record Chunk(MemoryBuffer buffer, FreeListAllocator allocator) {
    }
}
