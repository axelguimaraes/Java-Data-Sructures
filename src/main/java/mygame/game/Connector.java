package mygame.game;

import mygame.exceptions.EmptyCollectionException;
import mygame.structures.queues.LinkedQueue;
import mygame.structures.queues.QueueADT;

public class Connector extends Local {
    private int cooldown;
    private final QueueADT<PlayerInteraction> lastInteractions;

    public Connector(int energy, Coordinates coordinates, int cooldown) {
        super(energy, coordinates);
        this.cooldown = cooldown;
        this.lastInteractions = new LinkedQueue<>();
    }

    public int getCooldown(Player player) {
        return this.cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public PlayerInteraction getLastInteraction() throws EmptyCollectionException {
        return lastInteractions.first();
    }

    public boolean chargePlayer(Player player) { // TODO: this
        player.setEnergy(player.getEnergy() + super.getEnergy());
        PlayerInteraction interaction = new PlayerInteraction(player, this.cooldown);
        lastInteractions.enqueue(interaction);

        return false;
    }

    public String toString() {
        return "CONNECTOR\n" +
                "ID: " + super.getId() + "\n" +
                "Cooldown: " + this.cooldown + " minutes\n" +
                "Energy: " + super.getEnergy() + "\n" +
                "Coordinates: " + super.getCoordinates() + "\n";
    }
}
