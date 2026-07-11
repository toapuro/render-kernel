package io.github.toapuro.renderkernel.api.memory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemoryRegionBuffer {
    private final MemoryPool.Chunk poolChunk;
    private final long address;
    private final long size;

    public void release() {
        poolChunk.pool().release(this);
    }

    public MemoryStream stream() {
        return new MemoryStream(address);
    }
}
