package io.github.toapuro.renderkernel.api.gl.buffer.array;

import io.github.toapuro.renderkernel.api.gl.GlApi;
import io.github.toapuro.renderkernel.api.gl.enums.GlValueType;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class GlArrayBuffer {
    private final int id = GL30.glGenVertexArrays();

    public static void unbind() {
        GlApi.state().ensureVertexArrayBound(0);
    }

    public void bind() {
        GlApi.state().ensureVertexArrayBound(id);
    }

    public void enableAttribute(int index) {
        GL20.glEnableVertexAttribArray(index);
    }

    public void enableAttributes(int min, int max) {
        for (int i = min; i < max; i++) {
            GL20.glEnableVertexAttribArray(i);
        }
    }

    public void setAttribute(GlAttributeType attributeType, int index, int count, GlValueType type, boolean normalized, int vertexStride, long pointer) {
        attributeType.getSetup().setup(index, count, type.getGl(), normalized, vertexStride, pointer);
    }

    public void release() {
        GL30.glDeleteVertexArrays(id);
    }
}
