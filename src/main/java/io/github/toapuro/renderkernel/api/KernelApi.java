package io.github.toapuro.renderkernel.api;

import io.github.toapuro.renderkernel.api.memory.MemoryBuffer;
import org.lwjgl.system.MemoryUtil;

public final class KernelApi {
    public static MemoryBuffer allocateMemory(long size) {
        long address = MemoryUtil.nmemAlloc(size);
        return new MemoryBuffer(address, size);
    }

    public static MemoryBuffer reallocateMemory(MemoryBuffer memoryBuffer, long newSize) {
        long address = MemoryUtil.nmemRealloc(memoryBuffer.getAddress(), newSize);
        return new MemoryBuffer(address, newSize);
    }
}
