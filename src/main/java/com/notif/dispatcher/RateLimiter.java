package com.notif.dispatcher;

import org.springframework.stereotype.Component;

@Component
public class RateLimiter{

    private final double tokensPerSecond = 8.0; // max sustained rate
    private double availableTokens = tokensPerSecond; // start full
    private long lastRefillTimeNanos = System.nanoTime();

    // Blocks the calling thread until one token is available, then consumes it.
    public synchronized void acquire() {
        while (true) {
            refill();
            if (availableTokens >= 1.0) {
                availableTokens -= 1.0;
                return; // got a token, proceed
            }
            // not enough tokens yet - sleep briefly and check again
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    // Adds tokens based on how much time passed since we last checked.
    private void refill() {
        long now = System.nanoTime();
        double secondsElapsed = (now - lastRefillTimeNanos) / 1_000_000_000.0;
        double tokensToAdd = secondsElapsed * tokensPerSecond;

        availableTokens = Math.min(tokensPerSecond, availableTokens + tokensToAdd);
        lastRefillTimeNanos = now;
    }
}