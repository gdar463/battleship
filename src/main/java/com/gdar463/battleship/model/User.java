package com.gdar463.battleship.model;

/**
 * Rappresenta l'utente e giocatore
 */
public class User implements CsvSerializable<User> {
    int id;
    String username;
    /// Teoricamente contiene lo salted hash della password
    String password;

    public User()  {}
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public User fromCsv(String file) {
        String[] parts = file.split(",");
        return new User(Integer.parseInt(parts[0]), parts[1], parts[2]);
    }
    @Override
    public String toCsv() {
        return username + "," + password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
}
