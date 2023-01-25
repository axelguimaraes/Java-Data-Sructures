package mygame.interfaces;

import mygame.game.Player;
import mygame.game.Team;

public interface IPortal extends ILocal {
    public String getName();

    public void setName(String name);
    public Team getTeam();
    public void setTeam(Team team);
    public Player getConqueror();
    public void setConqueror(Player player);
    public boolean rechargeEnergy(IPlayer player, int energy);
}
