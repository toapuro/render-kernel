package io.github.toapuro.renderkernel.api.gl.buffer;

import io.github.toapuro.renderkernel.api.gl.GlApi;
import io.github.toapuro.renderkernel.api.util.BufferRange;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lwjgl.opengl.*;

@Getter
@AllArgsConstructor
public final class GlGpuBuffer implements GlBuffer, GlRangedBuffer {
    private final int id = GL15.glGenBuffers();

    private long size;

    @Override
    public void bind(BufferTarget target) {
        GlApi.getState().ensureBufferBound(target.gl, id);
    }

    @Override
    public void upload(BufferTarget target, GlRef ref, BufferUsage usage) {
        // TODO:アップロード方法の改善
        GlApi.getState().ensureBufferBound(target.gl, id);
        GL15C.nglBufferData(target.gl, ref.getSize(), ref.getAddress(), usage.gl);
        if(size > ref.getSize()) size = ref.getSize();
    }

    public void uploadSub(BufferTarget target, long offset, GlRef ref) {
        // TODO:アップロード方法の改善
        GlApi.getState().ensureBufferBound(target.gl, id);
        GL15C.nglBufferSubData(target.gl, offset, ref.getSize(), ref.getAddress());
        if(size > ref.getSize()) size = ref.getSize();
    }

    public void release() {
        GL15.glDeleteBuffers(id);
    }

    @Override
    public BufferRange getRange() {
        return new BufferRange(0, (int) size);
    }

    @AllArgsConstructor
    @Getter
    public enum BufferTarget {
        ARRAY_BUFFER(GL15.GL_ARRAY_BUFFER),
        ELEMENT_ARRAY_BUFFER(GL15.GL_ELEMENT_ARRAY_BUFFER),
        COPY_READ_BUFFER(GL31.GL_COPY_READ_BUFFER),
        COPY_WRITE_BUFFER(GL31.GL_COPY_WRITE_BUFFER),
        PIXEL_PACK_BUFFER(GL21.GL_PIXEL_PACK_BUFFER),
        PIXEL_UNPACK_BUFFER(GL21.GL_PIXEL_UNPACK_BUFFER),
        TRANSFORM_FEEDBACK_BUFFER(GL30.GL_TRANSFORM_FEEDBACK_BUFFER),
        UNIFORM_BUFFER(GL31.GL_UNIFORM_BUFFER);

        private final int gl;
    }

    @AllArgsConstructor
    @Getter
    public enum BufferUsage {
        STREAM_DRAW(GL15.GL_STREAM_DRAW),
        STREAM_READ(GL15.GL_STREAM_READ),
        STREAM_COPY(GL15.GL_STREAM_COPY),

        STATIC_DRAW(GL15.GL_STATIC_DRAW),
        STATIC_READ(GL15.GL_STATIC_READ),
        STATIC_COPY(GL15.GL_STATIC_COPY),

        DYNAMIC_DRAW(GL15.GL_DYNAMIC_DRAW),
        DYNAMIC_READ(GL15.GL_DYNAMIC_READ),
        DYNAMIC_COPY(GL15.GL_DYNAMIC_COPY);

        private final int gl;
    }
}
