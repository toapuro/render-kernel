package io.github.toapuro.renderkernel.api.pipeline;

import io.github.toapuro.renderkernel.api.gl.buffer.array.GlArrayBuffer;
import io.github.toapuro.renderkernel.api.gl.buffer.array.GlAttributeType;
import io.github.toapuro.renderkernel.api.gl.buffer.array.GlVertexElement;
import io.github.toapuro.renderkernel.api.gl.enums.GlValueType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public final class VertexLayout {
    private final List<GlVertexElement> elements;
    private final int[] pointers;
    private final int vertexStrides;

    private VertexLayout(List<GlVertexElement> elements) {
        this.elements = elements;

        int pointer = 0;
        this.pointers = new int[elements.size()];

        for (int i = 0; i < elements.size(); i++) {
            pointers[i] = pointer;
            pointer += elements.get(i).getBytes();
        }
        vertexStrides = pointer;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void apply(GlArrayBuffer buffer) {
        buffer.enableAttributes(0, elements.size());

        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).apply(buffer, vertexStrides, pointers[i]);
        }
    }

    public static class Builder {
        private final List<GlVertexElement> elements = new ArrayList<>();

        public Builder floatAttribute(GlValueType bufferType, int size, boolean normalized) {
            int index = elements.size();
            elements.add(new GlVertexElement(GlAttributeType.FLOAT, index, size, bufferType, normalized));
            return this;
        }

        public Builder intAttribute(GlValueType bufferType, int size) {
            int index = elements.size();
            elements.add(new GlVertexElement(GlAttributeType.INT, index, size, bufferType, false));
            return this;
        }

        public Builder doubleAttribute(GlValueType bufferType, int size) {
            int index = elements.size();
            elements.add(new GlVertexElement(GlAttributeType.DOUBLE, index, size, bufferType, false));
            return this;
        }

        public VertexLayout build() {
            return new VertexLayout(Collections.unmodifiableList(elements));
        }
    }
}
