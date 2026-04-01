package io.github.toapuro.renderkernel.api.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;

public interface IObjectRenderer<T> {
    void writeHash(T object, HashBuilder builder);
    void render(T object, PoseStack poseStack, MultiBufferSource bufferSource, int partialTick, int packedLight);
}
