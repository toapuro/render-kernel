package io.github.toapuro.renderkernel.api.util;

import it.unimi.dsi.fastutil.longs.Long2LongRBTreeMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectRBTreeMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectSortedMap;
import it.unimi.dsi.fastutil.longs.LongArrayList;

public final class FreeListAllocator {
    private final Long2LongRBTreeMap byOffset = new Long2LongRBTreeMap();
    private final Long2ObjectRBTreeMap<LongArrayList> bySize = new Long2ObjectRBTreeMap<>();

    public void add(long offset, long size) {
        insert(offset, size);
    }

    public long allocate(long size) {
        if (bySize.isEmpty()) {
            return -1;
        }

        Long2ObjectSortedMap<LongArrayList> candidates = bySize.tailMap(size);
        if (candidates.isEmpty()) {
            return -1;
        }

        long blockSize = candidates.firstLongKey();
        LongArrayList list = bySize.get(blockSize);

        long offset = list.removeLong(list.size() - 1);
        if (list.isEmpty()) {
            bySize.remove(blockSize);
        }

        byOffset.remove(offset);

        long remain = blockSize - size;
        if (remain > 0) {
            insert(offset + size, remain);
        }

        return offset;
    }

    public void release(long offset, long size) {
        insert(offset, size);
        merge(offset);
    }

    private void insert(long offset, long size) {
        byOffset.put(offset, size);

        bySize.computeIfAbsent(size, k -> new LongArrayList())
                .add(offset);
    }

    private void remove(long offset, long size) {
        byOffset.remove(offset);

        LongArrayList list = bySize.get(size);
        list.rem(offset);

        if (list.isEmpty()) {
            bySize.remove(size);
        }
    }

    private void merge(long offset) {
        long size = byOffset.get(offset);

        var before = byOffset.headMap(offset);

        if (!before.isEmpty()) {
            long prevOffset = before.lastLongKey();
            long prevSize = byOffset.get(prevOffset);

            if (prevOffset + prevSize == offset) {
                remove(prevOffset, prevSize);
                remove(offset, size);

                offset = prevOffset;
                size += prevSize;

                insert(offset, size);
            }
        }

        var after = byOffset.tailMap(offset + 1);

        if (!after.isEmpty()) {
            long nextOffset = after.firstLongKey();
            long nextSize = byOffset.get(nextOffset);

            if (offset + size == nextOffset) {
                remove(offset, size);
                remove(nextOffset, nextSize);

                insert(offset, size + nextSize);
            }
        }
    }
}
