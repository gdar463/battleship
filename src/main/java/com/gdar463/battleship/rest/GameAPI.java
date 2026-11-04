package com.gdar463.battleship.rest;

import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;

@Path("/api/game")
public class GameAPI {
    /**
     * Crea una nuova partita con un altro giocatore.
     * @param headers http headers, per token
     * @param body contiene "opponent" ovvero lo username di un altro giocatore
     * @return stato del nuovo gioco (id, stadio, turno, celle) in formato JSON
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String createGame(@Context HttpHeaders headers, JsonObject body) {
        return "{}";
    }

    /**
     * Ritorna lo stato del gioco.
     * @param headers http headers, per token
     * @param id id del gioco richiesto
     * @return stato del gioco (id, stadio, turno, avversario) in formato JSON
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getState(@Context HttpHeaders headers, @PathParam("id") int id) {
        return "{}";
    }

    /**
     * Piazza le navi del giocatore corrente e passa il turno, se il gioco è in preparazione ed è proprio turno.
     * <p>
     * Se entrambi i giocatori hanno piazzato le navi, il gioco inizia.
     * @param headers http headers, per token
     * @param id id del gioco richiesto
     * @param body contiene lista di navi come oggetti con "cell", "length", "direction" ("right"/"down")
     */
    @POST
    @Path("/{id}/ships")
    @Consumes("application/json")
    public void placeShips(@Context HttpHeaders headers, @PathParam("id") int id, String body) {
    }

    /**
     * Ritorna le navi del giocatore corrente.
     * @param headers http headers, per token
     * @param id id del gioco richiesto
     * @return contiene lista di navi come oggetti con "cells" (lista di celle con coordinata e stato)
     */
    @GET
    @Path("/{id}/ships")
    @Produces("application/json")
    public String getShips(@Context HttpHeaders headers, @PathParam("id") int id) {
        return "{}";
    }

    /**
     * Ritorna le celle di entrambi i giocatori.
     * @param headers http headers, per token
     * @param id id del gioco richiesto
     * @return contiene "yours" e "theirs" con i campi del giocatore corrente e dell'avversario, rispettivamente
     */
    @GET
    @Path("/{id}/cells")
    @Produces("application/json")
    public String getCells(@Context HttpHeaders headers, @PathParam("id") int id) {
        return "{}";
    }

    /**
     * Colpisce una cella e passa il turno, se il gioco è iniziato ed è il proprio turno.
     * <p>
     * Se tutte le navi dell'avversario sono affondate, il gioco finisce.
     * @param headers http headers, per token
     * @param id id del gioco richiesto
     * @param cell coordinata della cella richiesta
     * @return contiene "status" (del gioco) e "result" ("colpito"/"mancato")
     */
    @POST
    @Path("/{id}/play")
    @Produces("application/json")
    public String playMove(@Context HttpHeaders headers, @PathParam("id") int id, @QueryParam("cell") String cell) {
        return "{}";
    }
}
