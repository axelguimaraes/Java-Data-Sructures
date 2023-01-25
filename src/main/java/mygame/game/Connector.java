package mygame.game;

import mygame.exceptions.EmptyCollectionException;
import mygame.interfaces.IConnector;
import mygame.structures.queues.LinkedQueue;
import mygame.structures.queues.QueueADT;

public class Connector extends Local implements IConnector {
    private int cooldown;
    private final QueueADT<PlayerInteraction> lastInteractions;

    public Connector(int energy, Coordinates coordinates) {
        super(energy, coordinates);
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
}
