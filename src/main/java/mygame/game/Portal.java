package mygame.game;

public class Portal extends Local {
    private String name;
    private Team team;
    private Player conqueror;
    private final int maxEnergy;

    public Portal(String name, int energy, Coordinates coordinates, Player conqueror, int maxEnergy) {
        super(energy, coordinates);
        this.team = Team.NONE;
        this.conqueror = conqueror;
        this.name = name;
        this.maxEnergy = maxEnergy;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getConqueror() {
        return this.conqueror;
    }

    public void setConqueror(Player player) {
        this.conqueror = player;
    }

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

    public boolean getConquered(Player player) { // TESTED
        if (this.team.equals(Team.NONE)) { // Se o portal nao tiver equipa
            this.team = player.getTeam();
            this.conqueror = player;

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

    @Override
    public String toString() {
        return "PORTAL\n" +
                "ID: " + super.getId() + "\n" +
                "Name: " + this.name + "\n" +
                "Team: " + this.team + "\n" +
                "Conquerer: " + this.conqueror + "\n" +
                "Energy: " + super.getEnergy() + "\n" +
                "Coordinates: " + super.getCoordinates() + "\n";
    }
}
