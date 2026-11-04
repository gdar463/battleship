package com.gdar463.battleship.model;

/**
 * Rappresenta l'utente e giocatore
 */
public class User {
    String username;
    /// Teoricamente contiene lo salted hash della password
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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
