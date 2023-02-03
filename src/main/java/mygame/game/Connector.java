package mygame.game;

import mygame.exceptions.ListExceptions;
import mygame.structures.classes.ArrayUnorderedList;

import java.util.Scanner;

public class Connector extends Local {
    private int cooldown;
    private final ArrayUnorderedList<PlayerInteraction> lastInteractions;

    public Connector(int energy, Coordinates coordinates, int cooldown) {
        super(energy, coordinates);
        this.cooldown = cooldown;
        this.lastInteractions = new ArrayUnorderedList<>();
        super.setLocalType(LocalType.CONNECTOR);
    }

    public int getCooldown() {
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
                    long remainingTime = this.cooldown - interaction.getElapsedTime();
                    System.out.println("You must wait " + remainingTime + " minute(s) before you can recharge in this connector again!");
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

    public void addPlayerInteraction(GameMap map) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player name:");
        String name = scanner.nextLine();

        for (Player player : map.getPlayersInGame()) {
            if (player.getName().equals(name)) {
                this.lastInteractions.addToRear(new PlayerInteraction(player, this.cooldown));
                return;
            }
        }
        System.err.println("Player not found!");
    }

    public void removePlayerInteraction(GameMap map) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player name:");
        String name = scanner.nextLine();

        for (int i = 0; i < this.lastInteractions.size(); i++) {
            if (this.lastInteractions.get(i).getPlayer().getName().equals(name)) {
                this.lastInteractions.remove(this.lastInteractions.get(i));
                return;
            }
        }
        System.err.println("Player not found!");
    }

    public String toString() {
        return "CONNECTOR\n" +
                "ID: " + super.getId() + "\n" +
                "Cooldown: " + this.cooldown + " minutes\n" +
                "Energy: " + super.getEnergy() + "\n" +
                "Coordinates: " + super.getCoordinates() + "\n";
    }

    public int compareByCooldown(Connector other) {
        return Integer.compare(this.cooldown, other.getCooldown());
    }
}
