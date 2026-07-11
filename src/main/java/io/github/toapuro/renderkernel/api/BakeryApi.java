package io.github.toapuro.renderkernel.api;

import io.github.toapuro.renderkernel.api.pipeline.PipelineBakery;
import lombok.Getter;

public final class BakeryApi {
    @Getter
    private static final PipelineBakery pipelineBakery = new PipelineBakery();
}
