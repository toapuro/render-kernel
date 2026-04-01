package io.github.toapuro.renderkernel.api;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RenderProperties {
    private RenderUsage renderUsage;

    enum RenderUsage {
        STATIC,
        DYNAMIC
    }
}
