package io.github.toapuro.renderkernel.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import io.github.toapuro.renderkernel.api.gl.GlApi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GlStateManager.class)
public class GlStateManagerMixin {
    @Inject(method = "_glBindBuffer", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL15;glBindBuffer(II)V"))
    private static void _glBindBuffer(int pTarget, int pBuffer, CallbackInfo ci) {
        GlApi.getState()._setBufferBound(pTarget, pBuffer);
    }

    @Inject(method = "_glBindVertexArray", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL30;glBindVertexArray(I)V"))
    private static void _glBindVertexArray(int pArray, CallbackInfo ci) {
        GlApi.getState()._setVertexArray(pArray);
    }
}
