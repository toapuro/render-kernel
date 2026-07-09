package io.github.toapuro.renderkernel.api.draw.packet;

import io.github.toapuro.renderkernel.api.gl.buffer.GlArrayBuffer;
import io.github.toapuro.renderkernel.api.gl.buffer.GlBuffer;
import io.github.toapuro.renderkernel.api.gl.buffer.GlGpuBuffer;
import io.github.toapuro.renderkernel.api.gl.buffer.GlGpuRef;
import io.github.toapuro.renderkernel.api.pipeline.PipelineState;
import io.github.toapuro.renderkernel.api.pipeline.VertexLayout;
import lombok.Getter;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11C;

@Getter
public class ElementsDrawPacket implements Dispatchable {
    private PipelineState state;
    private GlArrayBuffer arrayBuffer;
    private GlBuffer vertexBuffer;
    private GlBuffer indexBuffer;

    public void setup(PipelineState state, GlArrayBuffer arrayBuffer, GlBuffer vertexBuffer, GlBuffer indexBuffer) {
        this.state = state;
        this.arrayBuffer = arrayBuffer;
        this.vertexBuffer = vertexBuffer;
        this.indexBuffer = indexBuffer;
    }

    @Override
    public void dispatch() {
        VertexLayout.Polygon polygon = state.getLayout().getPolygon();
        GlGpuRef ref = vertexBuffer.ref();

        if(ref.getSize() > Integer.MAX_VALUE) throw new IllegalStateException();

        arrayBuffer.bind();
        vertexBuffer.bind(GlGpuBuffer.BufferTarget.ARRAY_BUFFER);
        indexBuffer.bind(GlGpuBuffer.BufferTarget.ELEMENT_ARRAY_BUFFER);
        GL11C.nglDrawElements(polygon.getGl(), (int) ref.getSize(), GL11.GL_UNSIGNED_BYTE, ref.getOffset());
    }
}
