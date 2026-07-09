package io.github.toapuro.renderkernel.api.gl.buffer;

import io.github.toapuro.renderkernel.api.gl.GlApi;
import org.lwjgl.opengl.GL30;

public class GlArrayBuffer {
    private final int id = GL30.glGenVertexArrays();

    public void bind() {
        GlApi.getState().ensureVertexArrayBound(id);
    }
}
