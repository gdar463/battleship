package com.gdar463.battleship.model;

import com.gdar463.battleship.database.BucketType;
import com.gdar463.battleship.database.Database;

/**
 * Rappresenta una partita
 */
public class Game implements CsvSerializable<Game> {
    // Teoricamente univoco per le partite
    int id;
    Field field1, field2;
    User player1, player2;
    GameTurn turn;
    GameStatus status;

    public Game() {}
    public Game(int id, User player1, User player2) {
        this(id, player1, player2, GameTurn.PLAYER_1);
    }
    public Game(int id, User player1, User player2, GameTurn turn) {
        this.player1 = player1;
        this.player2 = player2;
        this.turn = turn;
        this.status = GameStatus.PREPARAZIONE;
    }

    @Override
    public Game fromCsv(String file) {
        return new Game((User) Database.INSTANCE.get(BucketType.USER, "0"), (User) Database.INSTANCE.get(BucketType.USER, "1"));
    }
    @Override
    public String toCsv() {
        return "";
    }

    public int getId() {
        return id;
    }
    public Field getField1() {
        return field1;
    }
    public Field getField2() {
        return field2;
    }
    public User getPlayer1() {
        return player1;
    }
    public User getPlayer2() {
        return player2;
    }
    public GameTurn getTurn() {
        return turn;
    }
    public GameTurn switchTurn() {
        if (turn == GameTurn.PLAYER_1) {
            turn = GameTurn.PLAYER_2;
        } else {
            turn = GameTurn.PLAYER_1;
        }
        return turn;
    }
    public GameStatus getStatus() {
        return status;
    }
    public GameStatus nextStep() {
        if (status == GameStatus.PREPARAZIONE) {
            status = GameStatus.IN_PARTITA;
        } else {
            status = GameStatus.FINITA;
        }
        return status;
    }
}
