package edu.postech.csed332.homework1;

import java.util.*;

/**
 * An air tower that can attack nearby air monsters within 1 tile of distance.
 * For example, an air tower at (x,y) can attack any air monsters at (x-1, y),
 * (x+1, y), (x, y-1), and (x, y+1). The class AirTower implements the interface
 * Tower. TODO: implement all unimplemented methods.
 * Feel free to modify this file, e.g., adding new fields or methods. If needed,
 * you can define a new (abstract) super class that this class inherits.
 */
public class AirTower implements Tower {
    GameBoard board;

    public AirTower(GameBoard board) {
        // TODO: implement this
        this.board = board;
    }

    @Override
    public Set<Monster> attack() {
        // TODO: implement this
        Set<Monster> rst = new HashSet<Monster>();
        Position here = board.getPosition(this);
        Set<Position> adj = new HashSet<Position>();
        adj.add(here.getRelativePosition(-1, 0));
        adj.add(here.getRelativePosition(1, 0));
        adj.add(here.getRelativePosition(0, -1));
        adj.add(here.getRelativePosition(0, 1));

        for(Position tmp : adj){
            Set<Unit> atThere = board.getUnitsAt(tmp);
            if(atThere != null) {
                for (Unit unt : atThere) {
                    if (unt instanceof AirMob) {
                        rst.add((Monster) unt);
                    }
                }
            }
        }

        return rst;
    }

    @Override
    public GameBoard getBoard() {
        // TODO: implement this
        return board;
    }
}
