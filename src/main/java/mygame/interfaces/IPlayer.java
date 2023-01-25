package mygame.interfaces;

import mygame.game.Team;
import mygame.structures.lists.UnorderedArray;

public interface IPlayer {
    public int getEnergy();
    public void setEnergy(int energy);
    public void conquerPortal(IPortal portal);
    public void rechargeEnergy(IConnector connector);
    public Team getTeam();
    public void setTeam(Team team);
    public UnorderedArray<IPortal> getConqueredPortals();
    public ILocal getLocation();
    public  void navigateTo(ILocal destination);
    public void chargePortal(IPortal portal, int energy);
}
