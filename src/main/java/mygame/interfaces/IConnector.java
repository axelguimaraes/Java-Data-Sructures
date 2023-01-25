package mygame.interfaces;

import mygame.exceptions.EmptyCollectionException;
import mygame.game.Player;
import mygame.game.PlayerInteraction;

public interface IConnector extends ILocal {
    public int getCooldown(Player player);
    public void setCooldown(int cooldown);
    public PlayerInteraction getLastInteraction() throws EmptyCollectionException;
}
