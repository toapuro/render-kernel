package io.github.toapuro.renderkernel.api.pipeline;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public final class PipelineState {
    private final VertexLayout layout;
}
