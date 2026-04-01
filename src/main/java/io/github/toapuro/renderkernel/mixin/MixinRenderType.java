package io.github.toapuro.renderkernel.mixin;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.VertexSorting;
import net.minecraft.client.renderer.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(RenderType.class)
public class MixinRenderType {
    @Inject(method = "end", at = @At(value = "INVOKE_ASSIGN", target = "Lcom/mojang/blaze3d/vertex/BufferBuilder;end()Lcom/mojang/blaze3d/vertex/BufferBuilder$RenderedBuffer;"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    public void onEndBatch(BufferBuilder pBufferBuilder, VertexSorting pQuadSorting, CallbackInfo ci,
                           BufferBuilder.RenderedBuffer renderedBuffer) {
    }
}
