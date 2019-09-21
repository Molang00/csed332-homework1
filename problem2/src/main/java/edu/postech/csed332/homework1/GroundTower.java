package edu.postech.csed332.homework1;

import java.util.*;

/**
 * A ground tower that can attack nearby ground monsters within 1 tile of distance.
 * For example, a ground tower at (x,y) can attack any ground monsters at (x-1, y),
 * (x+1, y), (x, y-1), and (x, y+1). The class GroundTower implements the interface
 * Tower. TODO: implement all unimplemented methods.
 * Feel free to modify this file, e.g., adding new fields or methods. If needed,
 * you can define a new (abstract) super class that this class inherits.
 */
public class GroundTower implements Tower {
    GameBoard board;

    public GroundTower(GameBoard board) {
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
                    if (unt instanceof GroundMob) {
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
