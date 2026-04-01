package io.github.toapuro.renderkernel.api;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexBuffer;
import io.github.toapuro.renderkernel.api.renderer.HashBuilder;
import io.github.toapuro.renderkernel.api.renderer.IObjectRenderer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.client.renderer.MultiBufferSource;

@RequiredArgsConstructor
@Getter
public class RenderState<T> {
    private final RenderProperties properties;
    private final IObjectRenderer<T> renderer;
    private final VertexBuffer buffer;

    private long lastHash;

    private void render(T object) {
        HashBuilder hashBuilder = new HashBuilder();
        renderer.writeHash(object, hashBuilder);
        this.lastHash = hashBuilder.build();
    }

    private void upload(T object, PoseStack poseStack, MultiBufferSource bufferSource, int partialTick, int packedLight) {
        renderer.render(object, poseStack, bufferSource, partialTick, packedLight);
        if(bufferSource instanceof MultiBufferSource.BufferSource batch) {
            batch.endBatch();
        }
    }
}
