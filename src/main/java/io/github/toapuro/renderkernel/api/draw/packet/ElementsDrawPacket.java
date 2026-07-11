package io.github.toapuro.renderkernel.api.draw.packet;

import io.github.toapuro.renderkernel.api.BakeryApi;
import io.github.toapuro.renderkernel.api.gl.buffer.GlBuffer;
import io.github.toapuro.renderkernel.api.gl.buffer.GlGpuBuffer;
import io.github.toapuro.renderkernel.api.gl.buffer.GlGpuRef;
import io.github.toapuro.renderkernel.api.gl.enums.GlValueType;
import io.github.toapuro.renderkernel.api.pipeline.PipelineState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.opengl.GL11C;

@Slf4j
@Getter
public class ElementsDrawPacket implements Dispatchable {
    private PipelineState state;
    private GlBuffer vertexBuffer;
    private GlBuffer indexBuffer;
    private int vertices;

    public void setup(PipelineState state, GlBuffer vertexBuffer, GlBuffer indexBuffer, int vertices) {
        this.state = state;
        this.vertexBuffer = vertexBuffer;
        this.indexBuffer = indexBuffer;
        this.vertices = vertices;
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
        GL11C.nglDrawElements(polygon.getGl(), vertices, GlIndexType.fitFor(vertices).getGl(), ref.getOffset());
    }

    @RequiredArgsConstructor
    @Getter
    public enum GlIndexType {
        BYTE(GlValueType.BYTE.getGl()),
        SHORT(GlValueType.SHORT.getGl()),
        INT(GlValueType.INT.getGl());

        private final int gl;

        public static GlIndexType fitFor(int count) {
            if ((count & -256) == 0) return BYTE;
            if ((count & -65536) == 0) return SHORT;
            return INT;
        }
    }
}
