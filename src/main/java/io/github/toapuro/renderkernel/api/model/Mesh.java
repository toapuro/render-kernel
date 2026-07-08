package io.github.toapuro.renderkernel.api.model;

import io.github.toapuro.renderkernel.api.gl.buffer.GlBuffer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Mesh {
    private final GlBuffer vaoGpuBuffer;
}
