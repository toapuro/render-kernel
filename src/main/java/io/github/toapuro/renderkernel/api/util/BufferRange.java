package io.github.toapuro.renderkernel.api.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BufferRange {
    private final long start;
    private final int size;

    public long getEnd() {
        return start + size;
    }
}
