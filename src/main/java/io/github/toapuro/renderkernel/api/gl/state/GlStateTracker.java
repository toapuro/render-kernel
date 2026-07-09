package io.github.toapuro.renderkernel.api.gl.state;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public final class GlStateTracker {
    private final Int2IntMap boundBuffers = new Int2IntOpenHashMap();
    private int boundVertexArray = -1;

    public GlStateTracker() {
        boundBuffers.defaultReturnValue(-1);
    }

    public void ensureBufferBound(int target, int bufferId) {
        Preconditions.checkArgument(GL15.GL_STREAM_DRAW <= target && target <= GL15.GL_DYNAMIC_COPY, "target is out of range");
        if (boundBuffers.put(target, bufferId) != bufferId) {
            GL15.glBindBuffer(target, bufferId);
        }
    }

    public void _setBufferBound(int target, int bufferId) {
        this.boundBuffers.put(target, bufferId);
    }

    public void ensureVertexArrayBound(int vertexArray) {
        if (boundVertexArray != vertexArray) {
            GL30.glBindVertexArray(vertexArray);
        }
    }

    public void _setVertexArray(int vertexArray) {
        this.boundVertexArray = vertexArray;
    }
}
