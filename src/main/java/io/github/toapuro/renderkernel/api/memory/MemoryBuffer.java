package io.github.toapuro.renderkernel.api.memory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.lwjgl.system.MemoryUtil;

@RequiredArgsConstructor
@Getter
public class MemoryBuffer {
    private final long address;
    private final long size;

    public void release() {
        MemoryUtil.nmemFree(address);
    }

    public MemoryWriter writer() {
        return new MemoryWriter(address);
    }
}
