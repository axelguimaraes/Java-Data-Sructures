package mygame.game;

import java.time.Duration;
import java.time.Instant;

public class PlayerInteraction {
    private final int cooldown;
    private final Instant interactionTime;
    private final Player player;

    public PlayerInteraction(Player player, int cooldown) {
        this.interactionTime = Instant.now();
        this.player = player;
        this.cooldown = cooldown;
    }

    public Player getPlayer() {
        return player;
    }

    public Instant getInteractionTime() {
        return this.interactionTime;
    }

    public long getElapsedTime() {
        return Duration.between(interactionTime, Instant.now()).toSeconds();
    }

    public boolean isCooldownOver() {
        return Duration.between(interactionTime, Instant.now()).toSeconds() >= this.cooldown;
    }
}
