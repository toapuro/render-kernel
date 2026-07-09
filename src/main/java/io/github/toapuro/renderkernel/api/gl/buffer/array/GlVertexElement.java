package io.github.toapuro.renderkernel.api.gl.buffer.array;

import io.github.toapuro.renderkernel.api.gl.enums.GlValueType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GlVertexElement {
    private final GlAttributeType attributeType;
    private final int index;
    private final int count;
    private final GlValueType type;
    private final boolean normalized;

    public void apply(GlArrayBuffer buffer, int vertexStride, long pointer) {
        buffer.setAttribute(attributeType, index, count, type, normalized, vertexStride, pointer);
    }

    public int getBytes() {
        return type.getBytes() * count;
    }
}
