package edu.postech.csed332.homework1;

import java.util.*;

/**
 * A ground monster that moves towards to the goal position of the board, while
 * satisfying the game board invariants. The class GroundMob implements the interface
 * Monster. TODO: implement all unimplemented methods.
 * Feel free to modify this file, e.g., adding new fields or methods. If needed,
 * you can define a new (abstract) super class that this class inherits.
 *
 * @see GameBoard#isValid
 */
public class GroundMob implements Monster {
    GameBoard board;

    public GroundMob(GameBoard board) {
        // TODO: implement this
        this.board = board;
    }

    public List<Position> getAdj(Position cur){
        List<Position> adj = new ArrayList<Position>();
        adj.add(cur.getRelativePosition(-1, 0));
        adj.add(cur.getRelativePosition(1, 0));
        adj.add(cur.getRelativePosition(0, -1));
        adj.add(cur.getRelativePosition(0, 1));
        return adj;
    }

    public List<Position> getArround(Position cur){
        List<Position> arr = new ArrayList<Position>();
        arr.add(cur.getRelativePosition(-1, -1));
        arr.add(cur.getRelativePosition(-1, 0));
        arr.add(cur.getRelativePosition(-1, 1));
        arr.add(cur.getRelativePosition(0, -1));
        arr.add(cur.getRelativePosition(0, 1));
        arr.add(cur.getRelativePosition(1, -1));
        arr.add(cur.getRelativePosition(1, 0));
        arr.add(cur.getRelativePosition(1, 1));
        return arr;
    }

    public boolean isValid(Position pos){
        Set<Unit> curSet = board.getUnitsAt(pos);
        boolean isGround = true;

        if(pos.getX() < 0 || pos.getX() >= board.getWidth() || pos.getY() < 0 || pos.getY() >= board.getHeight()){
            return false;
        }
        if(curSet != null){
            for(Unit cur : curSet){
                if(cur instanceof Tower){
                    if(isGround) return false;
                    isGround = true;
                }
                else if(cur instanceof GroundMob){
                    if(isGround) return false;
                    isGround = true;
                }
            }
        }
        return true;
    }

    @Override
    public Position move() {
        // TODO: implement this
        Position cur = board.getPosition(this);
        Position next;
        List<Position> adj = getAdj(cur);

        Collections.sort(adj, new Comparator<Position>(){
            @Override
            public int compare (Position o1, Position o2) {
                return o1.getDistance(board.getGoalPosition()) - o2.getDistance(board.getGoalPosition());
            }
        });

        for(Position at : adj){
            System.out.println(at.getX()+" "+at.getY()+" "+at.getDistance(board.getGoalPosition()));
            List<Position> arr = getArround(at);
            int flag = 0;
            if(!isValid(at)) continue;
            for(Position t : arr){
                Set<Unit> atThere = board.getUnitsAt(t);
                if(atThere != null){
                    for(Unit u : atThere){
                        if(u instanceof GroundTower) {
                            flag = 1;
                            break;
                        }
                    }
                }
                if(flag == 1){
                    break;
                }
            }
            if(flag == 1) continue;
            else return at;
        }
        return cur;
    }

    @Override
    public boolean isGround() {
        // TODO implement this
        return true;
    }

    @Override
    public GameBoard getBoard() {
        // TODO implement this
        return board;
    }
}
