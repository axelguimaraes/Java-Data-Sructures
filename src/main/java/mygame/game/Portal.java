package mygame.game;

import mygame.interfaces.IPlayer;
import mygame.interfaces.IPortal;

public class Portal extends Local implements IPortal {
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

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Team getTeam() {
        return this.team;
    }

    @Override
    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public Player getConqueror() {
        return this.conqueror;
    }

    @Override
    public void setConqueror(Player player) {
        this.conqueror = player;
    }

    @Override
    public boolean rechargeEnergy(IPlayer player, int energy) {
        if (this.getTeam().equals(Team.NONE)) {
            System.out.println("Portal not associated to a team");
            return false;
        }

        Player playerRecharge = (Player) player;
        if (playerRecharge.isFromDifferentTeam(this)) {
            System.out.println("Portal doesn't belong to player's team!");
            return false;
        }

        this.setEnergy(this.getEnergy() + energy);
        System.out.println("Portal recharged by " + energy + " points");
        return true;
    }

    public boolean getConquered(IPlayer player) {
        if (this.team.equals(Team.NONE)) {
            this.team = player.getTeam();
            this.conqueror = (Player) player;

            System.out.println("Portal conquered by " + ((Player) player).getName() + " from team " + player.getTeam().toString());
            return true;
        }

        Player playerConqueror = (Player) player;
        if (playerConqueror.isFromDifferentTeam(this)) { // Se o jogador for de equipa diferente
            if (this.getEnergy() < playerConqueror.getEnergy()) { // Se o jogador tiver mais energia que o portal
                playerConqueror.setEnergy(playerConqueror.getEnergy() - this.getEnergy());
                this.setEnergy(0);

                if (playerConqueror.getEnergy() >= this.getEnergy() * 0.25) { // Se o jogador tiver mais de 25% da energia maxima do portal
                    this.setEnergy((int) (this.maxEnergy * 0.25));
                    playerConqueror.setEnergy((int) (playerConqueror.getEnergy() - this.maxEnergy * 0.25));
                    this.setTeam(playerConqueror.getTeam());
                    this.setConqueror(playerConqueror);
                    System.out.println("Portal conquered by " + ((Player) player).getName() + " from team " + player.getTeam().toString());
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
