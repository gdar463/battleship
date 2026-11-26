package com.gdar463.battleship.rest;

import com.gdar463.battleship.database.BucketType;
import com.gdar463.battleship.database.Database;
import com.gdar463.battleship.model.Field;
import com.gdar463.battleship.model.Game;
import com.gdar463.battleship.model.GameTurn;
import com.gdar463.battleship.model.User;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.*;

import java.util.UUID;

@Path("/game")
public class GameAPI {
    /**
     * Crea una nuova partita con un altro giocatore.
     *
     * @param headers http headers, per token
     * @param body    contiene opponent, x, y
     * @return stato del nuovo gioco (id, stadio, turno, celle) in formato JSON
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGame(@Context HttpHeaders headers, JsonObject body) {
        String jwt = headers.getHeaderString("Authorization").split(" ")[1];
        User user = User.verifyJWT(jwt);
        if (user == null) {
            return Response.status(401).build();
        }
        User player2 = (User) Database.INSTANCE.get(BucketType.USER, body.getString("opponent"));
        if (player2 == null) {
            return Response.status(400)
                    .entity("{\"error\":\"opponent not found\"}")
                    .build();
        }
        int x = body.getInt("x");
        int y = body.getInt("y");
        Game out = new Game();
        out.setPlayer1(user);
        out.setPlayer2(player2);
        out.setField1(new Field(x, y));
        out.setField2(new Field(x, y));
        out.setTurn(GameTurn.PLAYER_1);

        String id = Database.INSTANCE.createOrUpdate(BucketType.GAME, out, UUID.randomUUID().toString());
        out.setId(id);

        return Response.status(201).build();
    }

    /**
     * Ritorna lo stato del gioco.
     *
     * @param headers http headers, per token
     * @param id      id del gioco richiesto
     * @return stato del gioco (id, stadio, turno, avversario) in formato JSON
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getState(@Context HttpHeaders headers, @PathParam("id") int id) {
        return "{}";
    }

    /**
     * Piazza le navi del giocatore corrente e passa il turno, se il gioco è in preparazione ed è proprio turno.
     * <p>
     * Se entrambi i giocatori hanno piazzato le navi, il gioco inizia.
     *
     * @param headers http headers, per token
     * @param id      id del gioco richiesto
     * @param body    contiene lista di navi come oggetti con "cell", "length", "direction" ("right"/"down")
     */
    @POST
    @Path("/{id}/ships")
    @Consumes(MediaType.APPLICATION_JSON)
    public void placeShips(@Context HttpHeaders headers, @PathParam("id") int id, String body) {
    }

    /**
     * Ritorna le navi del giocatore corrente.
     *
     * @param headers http headers, per token
     * @param id      id del gioco richiesto
     * @return contiene lista di navi come oggetti con "cells" (lista di celle con coordinata e stato)
     */
    @GET
    @Path("/{id}/ships")
    @Produces(MediaType.APPLICATION_JSON)
    public String getShips(@Context HttpHeaders headers, @PathParam("id") int id) {
        return "{}";
    }

    /**
     * Ritorna le celle di entrambi i giocatori.
     *
     * @param headers http headers, per token
     * @param id      id del gioco richiesto
     * @return contiene "yours" e "theirs" con i campi del giocatore corrente e dell'avversario, rispettivamente
     */
    @GET
    @Path("/{id}/cells")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCells(@Context HttpHeaders headers, @PathParam("id") int id) {
        return "{}";
    }

    /**
     * Colpisce una cella e passa il turno, se il gioco è iniziato ed è il proprio turno.
     * <p>
     * Se tutte le navi dell'avversario sono affondate, il gioco finisce.
     *
     * @param headers http headers, per token
     * @param id      id del gioco richiesto
     * @param cell    coordinata della cella richiesta
     * @return contiene "status" (del gioco) e "result" ("colpito"/"mancato")
     */
    @POST
    @Path("/{id}/play")
    @Produces(MediaType.APPLICATION_JSON)
    public String playMove(@Context HttpHeaders headers, @PathParam("id") int id, @QueryParam("cell") String cell) {
        return "{}";
    }
}
