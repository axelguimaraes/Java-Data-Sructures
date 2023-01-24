package mygame.game;

public class Connector extends Local {
    private int cooldown;
    private long lastInteraction;

    public Connector(int id, double longitude, double latitude, double energy, int cooldown, long lastInteraction) {
        super(id, longitude, latitude, energy);
        this.cooldown = cooldown;
        this.lastInteraction = lastInteraction;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public long getLastInteraction() {
        return lastInteraction;
    }

    public void setLastInteraction(long lastInteraction) {
        this.lastInteraction = lastInteraction;
    }

    public boolean canRecharge(String playerId) {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastInteraction;
        if (elapsedTime >= cooldown * 1000) {
            lastInteraction = currentTime;
            return true;
        }
        return false;
    }


    public boolean recharge(Player player) {
        long currentTime = System.currentTimeMillis();
        String playerName = player.getName();
        if (lastInteraction.containsKey(playerName)) {
            long lastInteractionTime = lastInteraction.get(playerName);
            if (currentTime - lastInteractionTime < cooldown * 1000) {
                // Reject interaction
                System.out.println("Cannot recharge energy yet. Please wait.");
                return false;
            }
        }
        // Update last interaction time
        lastInteraction.put(playerName, currentTime);
        // Recharge energy
        player.setCurrentEnergy((int) (player.getCurrentEnergy() + super.getEnergy()));
        System.out.println("Energy recharged successfully!");
        return true;
    }



}
