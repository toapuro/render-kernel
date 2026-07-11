package io.github.toapuro.renderkernel.api.draw.packet;

import io.github.toapuro.renderkernel.api.memory.pooling.ObjectPool;

public final class PacketObjectPools {
    public static final ObjectPool<ElementsDrawPacket> ELEMENTS_POOL = new ObjectPool<>(256, ElementsDrawPacket::new);
}
