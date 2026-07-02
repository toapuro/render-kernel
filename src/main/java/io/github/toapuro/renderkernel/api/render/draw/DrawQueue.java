package io.github.toapuro.renderkernel.api.render.draw;

import io.github.toapuro.renderkernel.api.gl.buffer.GlRangedBuffer;
import io.github.toapuro.renderkernel.api.pipeline.PipelineState;

import java.util.ArrayList;
import java.util.List;

public final class DrawQueue {
    private final List<DrawJob> drawJobs = new ArrayList<>();

    private void enqueueJob(DrawJob job) {
        this.drawJobs.add(job);
    }

    private void enqueueDrawElements(PipelineState pso, GlRangedBuffer buffer) {
        this.drawJobs.add(new DrawJob.SimpleDrawJob(pso, buffer));
    }

    public void flush() {
        for (DrawJob drawJob : drawJobs) {
            drawJob.draw();
        }
    }
}
