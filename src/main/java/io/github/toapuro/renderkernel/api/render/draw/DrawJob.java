package io.github.toapuro.renderkernel.api.render.draw;

import io.github.toapuro.renderkernel.api.gl.buffer.GlGpuBuffer;
import io.github.toapuro.renderkernel.api.gl.buffer.GlRangedBuffer;
import io.github.toapuro.renderkernel.api.pipeline.PipelineState;
import io.github.toapuro.renderkernel.api.pipeline.VertexLayout;
import io.github.toapuro.renderkernel.api.util.BufferRange;
import lombok.AllArgsConstructor;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11C;

@AllArgsConstructor
public abstract class DrawJob {
    protected final PipelineState state;

    public abstract void draw();

    public static class SimpleDrawJob extends DrawJob {
        private final GlRangedBuffer buffer;

        public SimpleDrawJob(PipelineState state, GlRangedBuffer buffer) {
            super(state);
            this.buffer = buffer;
        }

        @Override
        public void draw() {
            VertexLayout.Polygon polygon = state.getLayout().getPolygon();
            BufferRange range = buffer.getRange();

            buffer.bind(GlGpuBuffer.BufferTarget.ARRAY_BUFFER);
            GL11C.nglDrawElements(polygon.getGl(), range.getSize(), GL11.GL_UNSIGNED_BYTE, range.getStart());
        }
    }
}
