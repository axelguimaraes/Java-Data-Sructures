package mygame.game;

/**
 * Class that represents a {@link Portal}
 */
public class Portal extends Local {

    private String name;
    private Team team;
    private Player conqueror;
    private final int maxEnergy;

    public Portal(String name, Coordinates coordinates, int energy, int maxEnergy, Player conqueror){
        super(energy, coordinates);
        this.team = Team.NONE;
        this.conqueror = conqueror;
        this.name = name;
        this.maxEnergy = maxEnergy;
        super.setLocalType(LocalType.PORTAL);
    }

    /**
     * Constructor for the {@link Portal}. It automatically sets the {@link Team} as 'NONE' and the
     * {@link LocalType} as 'PORTAL'
     * @param name name of the {@link Portal}
     * @param energy starting energy of the {@link Portal}
     * @param coordinates geographic location of {@link Portal} in {@link Coordinates}
     * @param maxEnergy maximum energy that the {@link Portal} is able to contain
     */
    public Portal(String name, int energy, Coordinates coordinates, int maxEnergy) {
        super(energy, coordinates);
        this.team = Team.NONE;
        this.conqueror = null;
        this.name = name;
        this.maxEnergy = maxEnergy;
        super.setLocalType(LocalType.PORTAL);
    }

    /**
     * Getter for the name
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for the name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the {@link Team}
     * @return {@link Team}
     */
    public Team getTeam() {
        return this.team;
    }

    /**
     * Setter for the {@link Team}
     * @param team {@link Team}
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Getter for the {@link Player conqueror}
     * @return {@link Player conqueror}
     */
    public Player getConqueror() {
        return this.conqueror;
    }

    /**
     * Setter for the {@link Player conqueror}
     * @param player {@link Player} conqueror
     */
    public void setConqueror(Player player) {
        this.conqueror = player;
    }

    /**
     * Getter for the {@link Portal's} max energy
     * @return maxEnergy
     */
    public int getMaxEnergy() {
        return maxEnergy;
    }

    /**
     * Recharges the {@link Portal}'s energy with the given ammount from the {@link Player}
     * @param player {@link Player} to recharge {@link Portal}
     * @param energy ammount of energy to recharge
     * @return true if {@link Portal} was recharged; false if {@link Portal} wasn't recharged
     */
    public boolean rechargeEnergy(Player player, int energy) { // TESTED
        if (this.getTeam().equals(Team.NONE)) {
            System.out.println("Portal not associated to a team");
            return false;
        }

        if (player.isFromDifferentTeam(this)) {
            System.out.println("Portal doesn't belong to player's team!");
            return false;
        }

        this.setEnergy(this.getEnergy() + energy);
        player.setEnergy(player.getEnergy() - energy);
        System.out.println("Portal recharged by " + energy + " points");
        return true;
    }

    /**
     * Get {@link Portal} conquered by a {@link Player}
     * @param player {@link Player} to conquer the {@link Portal}
     * @return true if the {@link Portal} was conquered; false if {@link Portal} wasn't conquered
     */
    public boolean getConquered(Player player) { // TESTED
        if (this.team.equals(Team.NONE)) { // Se o portal nao tiver equipa
            this.team = player.getTeam();
            this.conqueror = player;
            this.conqueror.addToConqueredPortalsList(this.getId());

            System.out.println("Portal conquered by " + player.getName() + " from team " + player.getTeam().toString());
            return true;
        }

        if (player.isFromDifferentTeam(this)) { // Se o jogador for de equipa diferente
            if (this.getEnergy() < player.getEnergy()) { // Se o jogador tiver mais energia que o portal
                player.setEnergy(player.getEnergy() - this.getEnergy());
                this.setEnergy(0);

                if (player.getEnergy() >= this.maxEnergy * 0.25) { // Se o jogador tiver mais de 25% da energia maxima do portal
                    this.setEnergy((int) (this.maxEnergy * 0.25));
                    player.setEnergy((int) (player.getEnergy() - this.maxEnergy * 0.25));
                    this.setTeam(player.getTeam());
                    this.setConqueror(player);
                    System.out.println("Portal conquered by " + player.getName() + " from team " + player.getTeam().toString());
                    return true;

                } else {
                    this.setTeam(Team.NONE);
                    System.out.println("Portal team set to NONE due to player not having enough energy to conquer");
                    return false;
                }
            } else {
                System.out.println("Player doesn't have enough energy to conquer portal. Portal team not changed");
                return false;
            }
        } else {
            System.out.println("Portal already belongs to player's team");
            return false;
        }
    }

    /**
     * Compares two {@link Portal Portals} by their name
     * @param other {@link Portal} to be compared to
     * @return '-1' if lesser; '0' if equal; '1' if greater
     */
    public int compareByName(Portal other) {
        return this.name.compareTo(other.getName());
    }

    /**
     * Compares two {@link Portal Portals} by their {@link Player conqueror}
     * @param other {@link Player conqueror} to be compared to
     * @return '-1' if lesser; '0' if equal; '1' if greater
     */
    public int compareByConqueror(Portal other) {
        return this.conqueror.compareByName(other.getConqueror());
    }

    /**
     * Lists all information relative to the {@link Portal}
     * @return {@link String} containing all the information
     */
    @Override
    public String toString() {
        return "PORTAL\n" +
                "ID: " + super.getId() + "\n" +
                "Name: " + this.name + "\n" +
                //"Team: " + this.conqueror.getTeam() + "\n" +
                "Conqueror: " + (this.conqueror != null ? this.conqueror.getName() : "null") + "\n" +
                "Energy: " + super.getEnergy() + "\n" +
                "Coordinates: " + super.getCoordinates() + "\n";
    }
}
