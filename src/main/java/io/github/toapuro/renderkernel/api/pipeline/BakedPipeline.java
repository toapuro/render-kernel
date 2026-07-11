package io.github.toapuro.renderkernel.api.pipeline;

import io.github.toapuro.renderkernel.api.gl.buffer.array.GlArrayBuffer;

public record BakedPipeline(PipelineState pipelineState, GlArrayBuffer bakedVertexArray) {
}
