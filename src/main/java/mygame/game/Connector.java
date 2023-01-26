package mygame.game;

import mygame.exceptions.ListExceptions;
import mygame.structures.lists.UnorderedArrayList;

public class Connector extends Local {
    private int cooldown;
    private final UnorderedArrayList<PlayerInteraction> lastInteractions;

    public Connector(int energy, Coordinates coordinates, int cooldown) {
        super(energy, coordinates);
        this.cooldown = cooldown;
        this.lastInteractions = new UnorderedArrayList<>();
        super.setLocalType(LocalType.CONNECTOR);
    }

    public int getCooldown(Player player) {
        return this.cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public PlayerInteraction getLastInteraction() throws ListExceptions {
        return lastInteractions.first();
    }

    public boolean chargePlayer(Player player) throws ListExceptions { // TESTED
        for (PlayerInteraction interaction : lastInteractions) {
            if (interaction.getPlayer().equals(player)) { // Se jogador existir na lista
                if (!interaction.isCooldownOver()) { // Se o cooldown não tiver acabado
                    System.out.println("Cooldown not over. Elapsed time: " + interaction.getElapsedTime());
                    return false;
                } else if (interaction.isCooldownOver()) {
                    lastInteractions.remove(interaction);
                    break;
                }
            }
        }

        player.setEnergy(player.getEnergy() + super.getEnergy());
        System.out.println("Player charged!");

        PlayerInteraction interaction = new PlayerInteraction(player, this.cooldown);
        lastInteractions.addToRear(interaction);
        return true;
    }

    public String toString() {
        return "CONNECTOR\n" +
                "ID: " + super.getId() + "\n" +
                "Cooldown: " + this.cooldown + " minutes\n" +
                "Energy: " + super.getEnergy() + "\n" +
                "Coordinates: " + super.getCoordinates() + "\n";
    }
}
