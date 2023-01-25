package mygame.interfaces;

import mygame.game.Team;
import mygame.structures.lists.UnorderedArrayList;

public interface IPlayer {
    public int getEnergy();
    public void setEnergy(int energy);
    public boolean conquerPortal(IPortal portal);
    public boolean rechargeEnergy(IConnector connector);
    public Team getTeam();
    public void setTeam(Team team);
    public UnorderedArrayList<IPortal> getConqueredPortals();
    public ILocal getLocation();
    public  void navigateTo(ILocal destination);
    public boolean chargePortal(IPortal portal, int energy);
}
