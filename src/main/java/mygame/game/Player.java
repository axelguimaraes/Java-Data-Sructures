package mygame.game;

public class Player {
    private String name;
    private Team team;
    private int level;
    private int experiencePoints;
    private int currentEnergy;

    public Player(String name, Team team, int level, int experiencePoints, int currentEnergy) {
        this.name = name;
        this.team = team;
        this.level = level;
        this.experiencePoints = experiencePoints;
        this.currentEnergy = currentEnergy;
    }

    public Player(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public boolean conquerPortal(Portal portal, double energy) {
        if (portal.attack(this)) {
            this.currentEnergy -= energy;
            return true;
        } else {
            return false;
        }
    }

    public boolean rechargeEnergy(Connector connector) {
        long currentTime = System.currentTimeMillis();
        if (connector.getCooldown().containsKey(connector.getId())) {
            long lastInteraction = connector.getCooldown().get(connector.getId());
            if (currentTime - lastInteraction >= connector.getCooldown()) {
                //this.energy += connector.getEnergy(); verificar linha abaixo, tendo em conta esta
                connector.setEnergy(connector.getEnergy() + connector.getEnergy());
                connector.getCooldown().put(connector.getId(), currentTime);
                return true;
            } else {
                return false;
            }
        } else {
            //this.energy += connector.getEnergy();
            connector.setEnergy( + connector.getEnergy()); //dá assim?
            connector.getCooldown().put(connector.getId(), currentTime);
            return true;
        }
    }

    public int calculateExperiencePoints() {
        int experiencePoints = 0;
        for (Portal portal : portals) {
            if (portal.getState().equals(this.team)) {
                experiencePoints += portal.getEnergy();
            }
        }
        experiencePoints += connectors.size() * 10;
        return experiencePoints;
    }
}
