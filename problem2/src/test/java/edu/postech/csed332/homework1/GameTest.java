package edu.postech.csed332.homework1;

import javafx.geometry.Pos;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    void testGameBoard() {
        System.out.println("1st test");
        GameBoard board = new GameBoard(5, 3);
        assertEquals(board.getWidth(), 5);
        assertEquals(board.getHeight(), 3);
        System.out.println("1st test");
    }

    @Test
    void testPlaceUnit() {
        System.out.println("2nd test");
        GameBoard board = new GameBoard(5, 3);
        Unit obj = new GroundMob(board);
        Position pos = new Position(0, 0);

        board.placeUnit(obj, pos);
        assertTrue(board.getUnitsAt(pos).contains(obj));
        assertEquals(board.getNumMobs(), 1);
        System.out.println("2nd test");
    }

    @Test
    void testPlaceUnitInvalid() {
        System.out.println("3rd test");
        GameBoard board = new GameBoard(5, 3);
        Unit obj = new GroundMob(board);
        Position pos = new Position(5, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            board.placeUnit(obj, pos);
        });
        System.out.println("3rd test");
    }

    @Test
    void testPlaceTwoUnits() {
        System.out.println("4th test");
        GameBoard board = new GameBoard(5, 3);
        Unit o1 = new AirMob(board);
        Unit o2 = new GroundTower(board);
        Position p = new Position(0, 0);

        board.placeUnit(o1, p);
        board.placeUnit(o2, p);
        assertTrue(board.getUnitsAt(p).contains(o1));
        assertTrue(board.getUnitsAt(p).contains(o2));
        assertEquals(board.getNumMobs(), 1);
        assertEquals(board.getNumTowers(), 1);
        System.out.println("4th test");
    }

    @Test
    void testGroundAttack() {
        System.out.println("5th test");
        GameBoard board = new GameBoard(5, 3);
        Tower o1 = new GroundTower(board);
        Position p1 = new Position(1, 1);
        Monster o2 = new GroundMob(board);
        Position p2 = new Position(1, 2);

        board.placeUnit(o1, p1);
        board.placeUnit(o2, p2);
        assertTrue(o1.attack().contains(o2));
        System.out.println("5th test");
    }

    @Test
    void testAttacks() {
        System.out.println("6th test");
        GameBoard board = new GameBoard(5, 3);
        Tower o1 = new GroundTower(board);
        Position p1 = new Position(1, 1);
        Monster o2 = new GroundMob(board);
        Position p2 = new Position(1, 2);
        Monster o3 = new AirMob(board);
        Position p3 = new Position(2, 1);
        Tower o4 = new AirTower(board);
        Position p4 = new Position(2, 2);

        board.placeUnit(o1, p1);
        board.placeUnit(o2, p2);
        board.placeUnit(o3, p3);
        board.placeUnit(o4, p4);
        assertTrue(o1.attack().contains(o2));
        assertTrue(o4.attack().contains(o3));
        assertFalse(o1.attack().contains(o3));
        assertFalse(o4.attack().contains(o2));
        System.out.println("6th test");
    }

    @Test
    void ariMobMove() {
        System.out.println("7th test");
        GameBoard board = new GameBoard(5, 3);
        Tower o1 = new GroundTower(board);
        Position p1 = new Position(1, 1);
        Monster o2 = new GroundMob(board);
        Position p2 = new Position(1, 2);
        Monster o3 = new AirMob(board);
        Position p3 = new Position(2, 1);
        Tower o4 = new AirTower(board);
        Position p4 = new Position(2, 2);

        board.placeUnit(o1, p1);
        board.placeUnit(o2, p2);
        board.placeUnit(o3, p3);
        board.placeUnit(o4, p4);
        assertTrue(o1.attack().contains(o2));
        assertTrue(o4.attack().contains(o3));
        assertFalse(o1.attack().contains(o3));
        assertFalse(o4.attack().contains(o2));
        o3.move();
        System.out.println("6th test");
    }
}
