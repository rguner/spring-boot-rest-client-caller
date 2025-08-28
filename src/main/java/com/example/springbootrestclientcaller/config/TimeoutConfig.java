package com.example.springbootrestclientcaller.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeoutConfig {

    @Builder.Default
    private long requestTimeout = 6000;

    @Builder.Default
    private long responseTimeout = 6000;

    @Builder.Default
    private long socketTimeout = 1000;
}