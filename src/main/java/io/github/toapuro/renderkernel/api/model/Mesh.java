package io.github.toapuro.renderkernel.api.model;

import io.github.toapuro.renderkernel.api.gl.buffer.GlBuffer;
import io.github.toapuro.renderkernel.api.pipeline.PipelineState;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Mesh {
    private final PipelineState pipelineState;
    private final GlBuffer vaoGpuBuffer;
}
