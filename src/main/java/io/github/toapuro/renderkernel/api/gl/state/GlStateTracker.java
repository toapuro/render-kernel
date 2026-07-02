package io.github.toapuro.renderkernel.api.gl.state;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import org.lwjgl.opengl.GL15;

public final class GlStateTracker {
    private final Int2IntMap buffers = new Int2IntOpenHashMap();

    public GlStateTracker() {
        buffers.defaultReturnValue(-1);
    }

    public void ensureBufferBound(int target, int bufferId) {
        Preconditions.checkArgument(GL15.GL_STREAM_DRAW <= target && target <= GL15.GL_DYNAMIC_COPY, "target is out of range");
        if (buffers.put(target, bufferId) != bufferId) {
            GL15.glBindBuffer(target, bufferId);
        }
    }

    public void _setBufferBound(int target, int bufferId) {
        this.buffers.put(target, bufferId);
    }
}
