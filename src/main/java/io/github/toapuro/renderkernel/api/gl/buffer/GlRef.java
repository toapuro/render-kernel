package io.github.toapuro.renderkernel.api.gl.buffer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

@AllArgsConstructor(staticName = "ref")
@Getter
public final class GlRef {
    private final long address;
    private final long size;

    public static GlRef bufferRef(ByteBuffer buffer) {
        return new GlRef(MemoryUtil.memAddress(buffer), buffer.remaining());
    }
}
