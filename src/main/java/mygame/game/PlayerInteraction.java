package mygame.game;

import java.time.Duration;
import java.time.Instant;

/**
 * Class that represents a {@link PlayerInteraction}. This class is meant to be used in {@link Connector Connectors}
 * when a {@link Player} interacts with it
 */
public class PlayerInteraction {
    private final int cooldown;
    private final Instant interactionTime;
    private final Player player;

    /**
     * Constructor for the {@link PlayerInteraction}. It instanciates the interaction time as the moment of creation
     * @param player {@link Player} that made the interaction
     * @param cooldown {@link Connector}'s cooldown
     */
    public PlayerInteraction(Player player, int cooldown) {
        this.interactionTime = Instant.now();
        this.player = player;
        this.cooldown = cooldown;
    }

    /**
     * Getter for the {@link Player}
     * @return {@link Player}
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Getter for the interaction time
     * @return {@link Instant} of interaction
     */
    public Instant getInteractionTime() {
        return this.interactionTime;
    }

    /**
     * Getter for the elapsed time between the interaction and the end of cooldown
     * @return ammount of {@link Duration time} passed, in seconds
     */
    public long getElapsedTime() {
        return Duration.between(interactionTime, Instant.now()).toSeconds();
    }

    /**
     * Checks if the interaction cooldown for the {@link Player} is over
     * @return true if the cooldown is over; false if the cooldown is not over
     */
    public boolean isCooldownOver() {
        return Duration.between(interactionTime, Instant.now()).toSeconds() >= this.cooldown;
    }
}
