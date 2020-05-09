package com.example.swarm;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {


    private boolean enabled = true;

    public void disable() {
        enabled = false;
    }


    @Override
    public Health health() {
        return enabled ? Health.up().build() : Health.down().build();
    }
}
