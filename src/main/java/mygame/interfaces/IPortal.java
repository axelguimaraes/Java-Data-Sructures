package mygame.interfaces;

import mygame.game.Player;
import mygame.game.Team;

public interface IPortal extends ILocal {
    public Team getTeam();
    public void setTeam(Team team);
    public Player getConqueror();
    public void setConqueror(Player player);
    public void rechargeEnergy(int energy);
}
