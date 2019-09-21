package edu.postech.csed332.homework1;

import java.util.*;

/**
 * A game board contains a set of units and a goal position. A game consists
 * of a number of rounds. For each round, all units perform their actions:
 * a monster can escape or move, and a tower can attack nearby monsters.
 * The following invariant conditions must be maintained throught the game:
 * <p>
 * (a) The position of each unit is inside the boundaries.
 * (b) Different ground units cannot be on the same tile.
 * (c) Different air units cannot be on the same tile.
 * <p>
 * TODO: implements all the unimplemented methods (marked with TODO)
 * NOTE: do not modify the signature of public methods (which will be
 * used for GUI and grading). But you can feel free to add new fields
 * or new private methods if needed.
 */
public class GameBoard {
    private final Position goal;
    private final int width, height;

    private final Map<Position, Set<Unit> > units;
    private final Set<Tower> towers;
    private final Set<Monster> mobs;

    private int numMobs;
    private int numTowers;
    private int numMobsKilled;
    private int numMobsEscaped;

    // TODO: add more fields to implement this class

    /**
     * Creates a game board with a given width and height. The goal position
     * is set to the middle of the end column.
     *
     * @param width  of this board
     * @param height of this board
     */
    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        goal = new Position(width - 1, height / 2);

        // TODO: add more lines if needed.

        units = new HashMap<Position, Set<Unit> >();
        towers = new HashSet<Tower>();
        mobs = new HashSet<Monster>();

        numMobs = 0;
        numTowers = 0;
        numMobsKilled = 0;
        numMobsEscaped = 0;
    }

    /**
     * Places a unit at a given position into this board.
     *
     * @param obj a unit to be placed
     * @param p   the position of obj
     * @throws IllegalArgumentException if p is outside the bounds of the board.
     */
    public void placeUnit(Unit obj, Position p) throws IllegalArgumentException {
        // TODO: implement this
        Set<Unit> newObjSet;
        if(units.containsKey(p)){
            newObjSet = units.get(p);
        }
        else {
            newObjSet = new HashSet();
        }
        newObjSet.add(obj);
        units.put(p, newObjSet);

        if(obj instanceof Tower){
            towers.add((Tower)obj);
            numTowers++;
        }
        else {
            mobs.add((Monster) obj);
            numMobs++;
        }

        if(!isValid()){
            System.out.println("IllegalArgumentException");
            throw new IllegalArgumentException();
        }
    }

    /**
     * Clears this game board. That is, all units are removed, and all numbers
     * for game statistics are reset to 0.
     */
    public void clear() {
        // TODO: implement this
        units.clear();
        towers.clear();
        mobs.clear();

        numMobs = 0;
        numTowers = 0;
        numMobsKilled = 0;
        numMobsEscaped = 0;
    }

    /**
     * Returns the set of units at a given position in the board. Note that
     * multiple units can exist at the same position (e.g., ground and air)
     *
     * @param p a position
     * @return the set of units at position p
     */
    public Set<Unit> getUnitsAt(Position p) {
        // TODO: implement this
        Set<Unit> rst = units.get(p);

        if(rst != null) {
            for (Unit cur : rst) {
                System.out.println(cur);
            }
        }

        return rst;
    }

    /**
     * Returns the set of all monsters in this board.
     *
     * @return the set of all monsters
     */
    public Set<Monster> getMonsters() {
        // TODO: implement this
        return mobs;
    }

    /**
     * Returns the set of all towers in this board.
     *
     * @return the set of all towers
     */
    public Set<Tower> getTowers() {
        // TODO: implement this
        return towers;
    }

    /**
     * Returns the position of a given unit
     *
     * @param obj a unit
     * @return the position of obj
     */
    public Position getPosition(Unit obj) {
        // TODO: implement this
        System.out.println("getPosition");
        for(Position key: units.keySet()){
            for(Unit cur: units.get(key)) {
                if (cur == obj) {
                    return key;
                }
            }
        }
        System.out.println("return null");
        return null;
    }

    /**
     * Proceeds one round of a game. The behavior of this method is as follows:
     * (1) Every monster at the goal position escapes from the game.
     * (2) Each tower attacks nearby remaining monsters (using the attack method).
     * (3) All remaining monsters (neither escaped nor attacked) moves (using the goal method).
     */
    public void step() {
        // TODO: implement this
    }

    /**
     * Checks whether the following invariants hold in the current game board:
     * (a) All units are in the boundaries.
     * (b) Different ground units cannot be on the same tile.
     * (c) Different air units cannot be on the same tile.
     *
     * @return true if there is no problem. false otherwise.
     */
    public boolean isValid() {
        // TODO: implement this
        for(Position pos : units.keySet()){
            Set<Unit> curSet = units.get(pos);
            boolean isGround = false;
            boolean isAir = false;

            if(pos.getX() < 0 || pos.getX() >= width || pos.getY() < 0 || pos.getY() >= height){
                return false;
            }

            for(Unit cur : curSet){
                if(cur instanceof Tower){
                    if(isGround) return false;
                    isGround = true;
                }
                else if(cur instanceof GroundMob){
                    if(isGround) return false;
                    isGround = true;
                }
                else if(cur instanceof AirMob){
                    if(isAir) return false;
                    isAir = true;
                }
            }
        }
        return true;
    }

    /**
     * Returns the number of the monsters in this board.
     *
     * @return the number of the monsters
     */
    public int getNumMobs() {
        // TODO: implement this
        return numMobs;
    }

    /**
     * Returns the number of the towers in this board.
     *
     * @return the number of the towers
     */
    public int getNumTowers() {
        // TODO: implement this
        return numMobs;
    }

    /**
     * Returns the number of the monsters removed so far in this game.
     * This number will be reset to 0 by the clear method.
     *
     * @return the number of the monsters removed
     */
    public int getNumMobsKilled() {
        // TODO: implement this
        return numMobsKilled;
    }

    /**
     * Returns the number of the monsters escaped so far in this game
     * This number will be reset to 0 by the clear method.
     *
     * @return the number of the monsters escaped
     */
    public int getNumMobsEscaped() {
        // TODO: implement this
        return numMobsEscaped;
    }

    /**
     * Returns the width of this board.
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of this board.
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the goal position where the monster can escape.
     *
     * @return the goal position of this game
     */
    public Position getGoalPosition() {
        return goal;
    }
}
