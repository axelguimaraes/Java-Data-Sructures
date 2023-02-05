package mygame.game;

import mygame.structures.classes.ArrayUnorderedList;

import java.util.Scanner;

/**
 * Class that represents a {@link Connector}
 */
public class Connector extends Local {
    private int cooldown;
    private final ArrayUnorderedList<PlayerInteraction> lastInteractions;

    /**
     * Constructor of the {@link Connector} class
     *
     * @param energy      ammount of energy that the {@link Connector} has
     * @param coordinates geographic position of the {@link Connector}
     * @param cooldown    time in minutes of the charging cooldown
     */
    public Connector(int energy, Coordinates coordinates, int cooldown) {
        super(energy, coordinates);
        this.cooldown = cooldown;
        this.lastInteractions = new ArrayUnorderedList<>();
        super.setLocalType(LocalType.CONNECTOR);
    }

    /**
     * Getter for cooldown
     *
     * @return the {@link Connector Connector's} cooldown
     */
    public int getCooldown() {
        return this.cooldown;
    }

    /**
     * Setter for cooldown
     *
     * @param cooldown time in minutes for the {@link Connector connector's} cooldown
     */
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    /**
     * Getter for the {@link Player players} that interacted with this {@link Connector}
     *
     * @return list of {@link PlayerInteraction}
     */
    public PlayerInteraction getLastInteraction() {
        return lastInteractions.first();
    }

    /**
     * Charges a {@link Player} with the ammount of energy that the {@link Connector} can provide.
     * First it checks wether a {@link PlayerInteraction} for the {@link Player} already exists. If so, it notifies the user
     * of the remaining cooldown before being able to charge again. If it doesn't exist, it charges the player and adds a
     * new {@link PlayerInteraction} to the list.
     *
     * @param player {@link Player} to be charged
     * @return true if charged; false if not charged
     */
    public boolean chargePlayer(Player player) { // TESTED
        for (PlayerInteraction interaction : lastInteractions) {
            if (interaction.getPlayer().equals(player)) { // Se jogador existir na lista
                if (!interaction.isCooldownOver()) { // Se o cooldown não tiver acabado
                    long remainingTime = this.cooldown - interaction.getElapsedTime();
                    System.err.println("You must wait " + remainingTime + " minute(s) before you can recharge in this connector again!");
                    return false;
                } else if (interaction.isCooldownOver()) {
                    lastInteractions.remove(interaction);
                    break;
                }
            }
        }

        player.setEnergy(player.getEnergy() + super.getEnergy());
        System.err.println("Player charged!");

        PlayerInteraction interaction = new PlayerInteraction(player, this.cooldown);
        lastInteractions.addToRear(interaction);
        return true;
    }

    /**
     * Adds a new {@link PlayerInteraction} to the list
     *
     * @param map {@link GameMap} instance, for checking existing {@link Player players}
     * @return true if added; false if not added
     */
    public boolean addPlayerInteraction(GameMap map) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player name:");
        String name = scanner.nextLine();

        for (Player player : map.getPlayersInGame()) {
            if (player.getName().equals(name)) {
                this.lastInteractions.addToRear(new PlayerInteraction(player, this.cooldown));
                return true;
            }
        }
        System.err.println("Player not found!");
        return false;
    }

    /**
     * Removes a {@link PlayerInteraction} from the list
     *
     * @return true if removed; false if not removed
     */
    public boolean removePlayerInteraction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player name:");
        String name = scanner.nextLine();

        for (int i = 0; i < this.lastInteractions.size(); i++) {
            if (this.lastInteractions.get(i).getPlayer().getName().equals(name)) {
                this.lastInteractions.remove(this.lastInteractions.get(i));
                return true;
            }
        }
        System.err.println("Player not found!");
        return false;
    }

    /**
     * Lists all information about the {@link Connector}
     *
     * @return {@link String} with the information
     */
    public String toString() {
        return "CONNECTOR\n" +
                "ID: " + super.getId() + "\n" +
                "Cooldown: " + this.cooldown + " minutes\n" +
                "Energy: " + super.getEnergy() + "\n" +
                "Coordinates: " + super.getCoordinates() + "\n";
    }

    /**
     * Compares {@link Connector connectors} by their cooldown time
     *
     * @param other {@link Connector} to be compared to
     * @return '-1' if lesser; '0' if equal; '1' if greater
     */
    public int compareByCooldown(Connector other) {
        return Integer.compare(this.cooldown, other.getCooldown());
    }
}
