package io.github.toapuro.renderkernel.api.memory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.lwjgl.system.MemoryUtil;

@RequiredArgsConstructor
@Getter
public class MemoryBuffer {
    private final long address;
    private final long size;

    private long pointer;

    public void step(long steps) {
        pointer += steps;
    }

    public void reset() {
        pointer = 0;
    }

    public long remaining() {
        return size - (pointer - address);
    }

    public void writeByte(byte value) {
        MemoryUtil.memPutByte(address, value);
        pointer += Byte.BYTES;
    }

    public void writeShort(short value) {
        MemoryUtil.memPutShort(address, value);
        pointer += Short.BYTES;
    }

    public void writeInt(int value) {
        MemoryUtil.memPutInt(address, value);
        pointer += Integer.BYTES;
    }

    public void writeLong(long value) {
        MemoryUtil.memPutLong(address, value);
        pointer += Long.BYTES;
    }

    public void writeFloat(float value) {
        MemoryUtil.memPutFloat(address, value);
        pointer += Float.BYTES;
    }

    public void writeDouble(double value) {
        MemoryUtil.memPutDouble(address, value);
        pointer += Double.BYTES;
    }

    public void writeByteArray(byte[] values) {
        for(byte value : values) {
            MemoryUtil.memPutByte(address, value);
            pointer += Byte.BYTES;
        }
    }

    public void writeShortArray(short[] values) {
        for(short value : values) {
            MemoryUtil.memPutShort(address, value);
            pointer += Short.BYTES;
        }
    }

    public void writeIntArray(int[] values) {
        for(int value : values) {
            MemoryUtil.memPutInt(address, value);
            pointer += Integer.BYTES;
        }
    }

    public void writeLongArray(long[] values) {
        for(long value : values) {
            MemoryUtil.memPutLong(address, value);
            pointer += Long.BYTES;
        }
    }

    public void writeFloatArray(float[] values) {
        for(float value : values) {
            MemoryUtil.memPutFloat(address, value);
            pointer += Float.BYTES;
        }
    }

    public void writeDoubleArray(double[] values) {
        for(double value : values) {
            MemoryUtil.memPutDouble(address, value);
            pointer += Double.BYTES;
        }
    }
}
