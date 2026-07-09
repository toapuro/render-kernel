package io.github.toapuro.renderkernel.api.pipeline;

import io.github.toapuro.renderkernel.api.gl.buffer.array.GlArrayBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public record BakedPipeline(PipelineState pipelineState, GlArrayBuffer bakedVertexArray) {
}
