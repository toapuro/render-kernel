package io.github.toapuro.renderkernel.api.gl.buffer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "ref")
@Getter
public class GlGpuRef {
    private final int id;
    private final long offset;
    private final long size;
}
