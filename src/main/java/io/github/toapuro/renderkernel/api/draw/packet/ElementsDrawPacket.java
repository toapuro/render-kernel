package io.github.toapuro.renderkernel.api.draw.packet;

import io.github.toapuro.renderkernel.api.BakeryApi;
import io.github.toapuro.renderkernel.api.gl.buffer.GlBuffer;
import io.github.toapuro.renderkernel.api.gl.buffer.GlGpuBuffer;
import io.github.toapuro.renderkernel.api.gl.buffer.GlGpuRef;
import io.github.toapuro.renderkernel.api.pipeline.PipelineState;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11C;

@Slf4j
@Getter
public class ElementsDrawPacket implements Dispatchable {
    private PipelineState state;
    private GlBuffer vertexBuffer;
    private GlBuffer indexBuffer;

    public void setup(PipelineState state, GlBuffer vertexBuffer, GlBuffer indexBuffer) {
        this.state = state;
        this.vertexBuffer = vertexBuffer;
        this.indexBuffer = indexBuffer;
    }

    @Override
    public void dispatch() {
        var polygon = state.getPolygon();
        GlGpuRef ref = vertexBuffer.ref();

        if(ref.getSize() > Integer.MAX_VALUE) throw new IllegalStateException();

        var bakedPipeline = BakeryApi.getPipelineBakery().getBakedPipeline(state);
        if (bakedPipeline == null) {
            log.error("Invalid pipeline state: pipeline must be pre-baked");
            return;
        }
        bakedPipeline.bakedVertexArray().bind();
        vertexBuffer.bind(GlGpuBuffer.BufferTarget.ARRAY_BUFFER);
        indexBuffer.bind(GlGpuBuffer.BufferTarget.ELEMENT_ARRAY_BUFFER);
        GL11C.nglDrawElements(polygon.getGl(), (int) ref.getSize(), GL11.GL_UNSIGNED_BYTE, ref.getOffset());
    }
}
