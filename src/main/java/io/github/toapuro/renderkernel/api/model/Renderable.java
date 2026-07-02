package io.github.toapuro.renderkernel.api.model;

import io.github.toapuro.renderkernel.api.render.draw.DrawQueue;

public interface Renderable {
    void render(DrawQueue queue);
}
