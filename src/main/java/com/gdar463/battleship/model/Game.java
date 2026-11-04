package com.gdar463.battleship.model;

/**
 * Rappresenta una partita
 */
public class Game {
    // Teoricamente univoco per le partite
    int id;
    Field field1, field2;
    User player1, player2;
    GameTurn turn;
    GameStatus status;

    public Game(User player1, User player2) {
        this(player1, player2, GameTurn.PLAYER_1);
    }

    public Game(User player1, User player2, GameTurn turn) {
        this.id = 4; // temporaneamente
        this.player1 = player1;
        this.player2 = player2;
        this.turn = turn;
        this.status = GameStatus.PREPARAZIONE;
        // Costruisce campi vuoti
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
