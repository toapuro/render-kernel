package io.github.toapuro.renderkernel.api.gl.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.lwjgl.opengl.GL11;

@Getter
@RequiredArgsConstructor
public enum GlValueType {
    BYTE(GL11.GL_BYTE, 1),
    UBYTE(GL11.GL_UNSIGNED_BYTE, 1),
    SHORT(GL11.GL_SHORT, 2),
    USHORT(GL11.GL_UNSIGNED_SHORT, 2),
    INT(GL11.GL_INT, 4),
    UINT(GL11.GL_UNSIGNED_INT, 4),
    FLOAT(GL11.GL_FLOAT, 4),
    DOUBLE(GL11.GL_DOUBLE, 8);

    private final int gl;
    private final int bytes;
}
