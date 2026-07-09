package io.github.toapuro.renderkernel.api.pipeline;

import io.github.toapuro.renderkernel.api.gl.buffer.array.GlArrayBuffer;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public final class PipelineBakery {
    private final Map<PipelineState, BakedPipeline> bakedPipelines = new HashMap<>();

    public void bake(PipelineState pipelineState) {
        GlArrayBuffer arrayBuffer = new GlArrayBuffer();
        arrayBuffer.bind();
        pipelineState.getLayout().apply(arrayBuffer);

        bakedPipelines.put(pipelineState, new BakedPipeline(pipelineState, arrayBuffer));
        GlArrayBuffer.unbind();
    }

    @Nullable
    public BakedPipeline getBakedPipeline(PipelineState state) {
        return bakedPipelines.get(state);
    }
}
