package io.github.toapuro.renderkernel.api.memory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

@AllArgsConstructor(staticName = "ref")
@Getter
public final class MemoryRef {
    private final long offset;
    private final long size;

    public static MemoryRef bufferRef(ByteBuffer buffer) {
        return new MemoryRef(MemoryUtil.memAddress(buffer), buffer.remaining());
    }

    public static MemoryRef bufferRef(MemoryBuffer buffer) {
        return new MemoryRef(buffer.getAddress(), buffer.getSize());
    }
}
