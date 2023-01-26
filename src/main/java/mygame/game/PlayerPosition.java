package mygame.game;

import mygame.interfaces.ILocal;
import mygame.interfaces.IPlayer;

public class PlayerPosition {
    private IPlayer player;
    private ILocal position;

    public PlayerPosition(IPlayer player, ILocal position) {
        this.player = player;
        this.position = position;
    }

    public IPlayer getPlayer() {
        return player;
    }

    public void setPlayer(IPlayer player) {
        this.player = player;
    }

    public ILocal getPosition() {
        return position;
    }

    public void setPosition(ILocal position) {
        this.position = position;
    }
}
