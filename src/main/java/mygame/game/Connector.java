package mygame.game;

import mygame.exceptions.EmptyCollectionException;
import mygame.interfaces.IConnector;
import mygame.interfaces.IPlayer;
import mygame.structures.queues.LinkedQueue;
import mygame.structures.queues.QueueADT;

public class Connector extends Local implements IConnector {
    private int cooldown;
    private final QueueADT<PlayerInteraction> lastInteractions;

    public Connector(int energy, Coordinates coordinates, int cooldown) {
        super(energy, coordinates);
        this.cooldown = cooldown;
        this.lastInteractions = new LinkedQueue<>();
    }

    @Override
    public int getCooldown(Player player) {
        return this.cooldown;
    }

    @Override
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    @Override
    public PlayerInteraction getLastInteraction() throws EmptyCollectionException {
        return lastInteractions.first();
    }

    public boolean chargePlayer(IPlayer player) { // TODO: this
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
