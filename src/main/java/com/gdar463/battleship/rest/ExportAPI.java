package com.gdar463.battleship.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;

@Path("/api/game/{id}")
public class ExportAPI {
    /**
     * Ritorna le celle di un gioco in csv.
     * @param headers http headers, per token
     * @param id id del gioco richiesto
     * @param side che campo usare "mine" o "theirs"
     * @return celle del campo richiesto
     */
    @GET
    @Path("/export")
    @Produces("text/csv")
    public String exportCSV(@Context HttpHeaders headers, @PathParam("id") int id, @QueryParam("side") String side) {
        return "";
    }
}
