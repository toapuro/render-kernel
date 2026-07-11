package io.github.toapuro.renderkernel.api.pipeline;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.lwjgl.opengl.GL11;

@Builder
@Getter
@EqualsAndHashCode
public final class PipelineState {
    private final Polygon polygon;
    private final VertexLayout layout;

    @RequiredArgsConstructor
    @Getter
    public enum Polygon {
        LINES(GL11.GL_LINES),
        LINE_STRIP(GL11.GL_LINE_STRIP),
        TRIANGLES(GL11.GL_TRIANGLES),
        TRIANGLE_STRIP(GL11.GL_TRIANGLE_STRIP),
        TRIANGLE_FAN(GL11.GL_TRIANGLE_FAN),
        QUADS(GL11.GL_QUADS);

        private final int gl;
    }
}
