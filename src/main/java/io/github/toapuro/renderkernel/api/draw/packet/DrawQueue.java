package io.github.toapuro.renderkernel.api.draw.packet;

import io.github.toapuro.renderkernel.api.gl.buffer.GlBuffer;
import io.github.toapuro.renderkernel.api.pipeline.PipelineState;

import java.util.ArrayList;
import java.util.List;

public final class DrawQueue {
    private final List<Dispatchable> drawPackets = new ArrayList<>();

    private void submitElements(PipelineState pso, GlBuffer arrayBuffer, GlBuffer vertexBuffer, GlBuffer indexBuffer) {
        ElementsDrawPacket packet = PacketObjectPools.ELEMENTS_PACKET.acquire();
        packet.setup(pso, arrayBuffer, vertexBuffer, indexBuffer);
        this.drawPackets.add(packet);
    }

    public void flush() {
        for (Dispatchable packet : drawPackets) {
            packet.dispatch();
        }
    }
}
