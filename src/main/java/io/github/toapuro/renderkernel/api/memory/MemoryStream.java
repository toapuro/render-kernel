package io.github.toapuro.renderkernel.api.memory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lwjgl.system.MemoryUtil;

@AllArgsConstructor
@Getter
public final class MemoryStream {
    private long pointer;

    public void skip(long bytes) {
        pointer += bytes;
    }

    public void skipShort(long count) {
        pointer += count * Short.BYTES;
    }

    public void skipInt(long count) {
        pointer += count * Integer.BYTES;
    }

    public void skipLong(long count) {
        pointer += count * Long.BYTES;
    }

    public void skipFloat(long count) {
        pointer += count * Float.BYTES;
    }

    public void skipDouble(long count) {
        pointer += count * Double.BYTES;
    }

    public void writeByte(byte value) {
        MemoryUtil.memPutByte(pointer, value);
        pointer += Byte.BYTES;
    }

    public void writeShort(short value) {
        MemoryUtil.memPutShort(pointer, value);
        pointer += Short.BYTES;
    }

    public void writeInt(int value) {
        MemoryUtil.memPutInt(pointer, value);
        pointer += Integer.BYTES;
    }

    public void writeLong(long value) {
        MemoryUtil.memPutLong(pointer, value);
        pointer += Long.BYTES;
    }

    public void writeFloat(float value) {
        MemoryUtil.memPutFloat(pointer, value);
        pointer += Float.BYTES;
    }

    public void writeDouble(double value) {
        MemoryUtil.memPutDouble(pointer, value);
        pointer += Double.BYTES;
    }

    public void writeByteArray(byte[] values) {
        for (byte value : values) {
            MemoryUtil.memPutByte(pointer, value);
            pointer += Byte.BYTES;
        }
    }

    public void writeShortArray(short[] values) {
        for (short value : values) {
            MemoryUtil.memPutShort(pointer, value);
            pointer += Short.BYTES;
        }
    }

    public void writeIntArray(int[] values) {
        for (int value : values) {
            MemoryUtil.memPutInt(pointer, value);
            pointer += Integer.BYTES;
        }
    }

    public void writeLongArray(long[] values) {
        for (long value : values) {
            MemoryUtil.memPutLong(pointer, value);
            pointer += Long.BYTES;
        }
    }

    public void writeFloatArray(float[] values) {
        for (float value : values) {
            MemoryUtil.memPutFloat(pointer, value);
            pointer += Float.BYTES;
        }
    }

    public void writeDoubleArray(double[] values) {
        for (double value : values) {
            MemoryUtil.memPutDouble(pointer, value);
            pointer += Double.BYTES;
        }
    }
}
