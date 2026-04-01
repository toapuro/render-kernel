package io.github.toapuro.renderkernel.api.renderer;

import com.mojang.blaze3d.vertex.BufferBuilder;
import io.github.toapuro.renderkernel.api.listener.ListenerProvider;

import java.util.function.Consumer;

public class RenderListenerHost {

    public static final ListenerProvider<Consumer<BufferBuilder.RenderedBuffer>> RENDER = new ListenerProvider<>();
}
