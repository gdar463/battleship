package com.gdar463.battleship.model;

import com.gdar463.battleship.database.BucketType;
import com.gdar463.battleship.database.Database;
import com.gdar463.battleship.database.DbObject;
import com.gdar463.battleship.service.CsvReader;
import com.gdar463.battleship.service.ShipDetector;

import java.util.List;

/**
 * Rappresenta una partita
 */
public class Game extends DbObject<Game> {
    // Teoricamente univoco per le partite
    Field field1, field2;
    User player1, player2;
    GameTurn turn;
    GameStatus status;

    public Game() {
    }

    @Override
    public Game fromCsv(String file, String id) {
        this.id = id;
        String[] lines = file.split("\n");
        String[] parts = lines[0].split(",");

        Game out = new Game();
        out.player1 = (User) Database.INSTANCE.get(BucketType.USER, parts[0]);
        out.player2 = (User) Database.INSTANCE.get(BucketType.USER, parts[1]);
        out.turn = GameTurn.valueOf(parts[6]);
        out.status = GameStatus.valueOf(parts[7]);

        out.field1 = new Field(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        String[] field1_s = new String[out.field1.getY()];
        System.arraycopy(lines, 1, field1_s, 0, out.field1.getY() - 1);
        out.field1.setCells(CsvReader.readCsv(field1_s));
        out.field1.setShips(ShipDetector.getShips(out.field1.getCells()));

        out.field2 = new Field(Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
        String[] field2_s = new String[out.field2.getY()];
        System.arraycopy(lines, out.field1.getY() + 1, field2_s, 0, out.field2.getY() - 1);
        out.field2.setCells(CsvReader.readCsv(field2_s));
        out.field2.setShips(ShipDetector.getShips(out.field2.getCells()));
        return out;
    }

    @Override
    public String toCsv() {
        StringBuilder out = new StringBuilder();
        out.append(this.id);
        out.append(",");
        out.append(this.player1.getId());
        out.append(",");
        out.append(this.player2.getId());
        out.append(",");
        out.append(this.field1.getX());
        out.append(",");
        out.append(this.field1.getY());
        out.append(",");
        out.append(this.field2.getX());
        out.append(",");
        out.append(this.field2.getY());
        out.append(",");
        out.append(this.turn.name());
        out.append(",");
        out.append(this.status.name());
        out.append("\n");
        List<Cell[]> field1_c = this.field1.getCells();
        for (int i = 0; i < this.field1.getY(); i++) {
            for (int j = 0; j < this.field1.getX(); i++) {
                out.append(field1_c.get(i)[j].getLetter());
                out.append(",");
            }
            out.append("\n");
        }
        List<Cell[]> field2_c = this.field2.getCells();
        for (int i = 0; i < this.field2.getY(); i++) {
            for (int j = 0; j < this.field2.getX(); i++) {
                out.append(field2_c.get(i)[j].getLetter());
                out.append(",");
            }
            out.append("\n");
        }
        return out.toString();
    }


    public void setTurn(GameTurn turn) {
        this.turn = turn;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
    }

    public void setField2(Field field2) {
        this.field2 = field2;
    }

    public void setField1(Field field1) {
        this.field1 = field1;
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
