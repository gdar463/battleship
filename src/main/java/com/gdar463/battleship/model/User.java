package com.gdar463.battleship.model;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gdar463.battleship.database.BucketType;
import com.gdar463.battleship.database.Database;
import com.gdar463.battleship.database.DbObject;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonWriter;

import java.io.StringReader;
import java.io.StringWriter;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Rappresenta l'utente e giocatore
 */
public class User extends DbObject<User> {
    private static final Algorithm JWT_ALGO = Algorithm.HMAC256("supersecretkey"); // TODO: leggere da file locale
    private static final String JWT_ISSUER = "com.gdar463.battleship";
    String username;
    String password;

    public User()  {}

    @Override
    public User fromCsv(String file, int id) {
        this.id = id;
        String[] parts = file.split(",");
        User out = new User();
        out.username = parts[0];
        out.password = parts[1];
        return out;
    }
    @Override
    public String toCsv() {
        return username + "," + password;
    }

    public String createJWT() {
        JsonObject payload = Json.createObjectBuilder()
                .add("username", this.username)
                .build();
        StringWriter out = new StringWriter();
        JsonWriter writer = Json.createWriter(out);
        writer.writeObject(payload);
        writer.close();
        return JWT.create()
                .withIssuer(JWT_ISSUER)
                .withExpiresAt(Instant.now().plus(7, ChronoUnit.DAYS))
                .withPayload(out.toString())
                .sign(JWT_ALGO);
    }
    public static User verifyJWT(String jwt) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(JWT_ALGO)
                .withIssuer(JWT_ISSUER)
                .build();
        DecodedJWT decoded = verifier.verify(jwt);
        JsonReader jsonReader = Json.createReader(new StringReader(decoded.getPayload()));
        JsonObject obj = jsonReader.readObject();
        jsonReader.close();
        String username = obj.getString("username");
        return (User) Database.INSTANCE.get(BucketType.USER, username);
    }
    public boolean verifyPassword(String password) {
        return BCrypt.checkpw(password, this.password);
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
