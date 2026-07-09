package io.github.toapuro.renderkernel.api.gl.buffer.array;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL41;

@RequiredArgsConstructor
@Getter
public enum GlAttributeType {
    FLOAT(GL20::glVertexAttribPointer),
    INT((index, size, type, normalized, stride, pointer) ->
            GL30.glVertexAttribIPointer(index, size, type, stride, pointer)),
    DOUBLE((index, size, type, normalized, stride, pointer) ->
            GL41.glVertexAttribLPointer(index, size, type, stride, pointer));

    private final SetupConsumer setup;

    interface SetupConsumer {
        void setup(int index, int size, int type, boolean normalized, int stride, long pointer);
    }
}
