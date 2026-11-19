package com.gdar463.battleship.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthAPI {
    /**
     * Permette il login attraverso username e password, inviando nel header Set-Cookie: "Authentication: Bearer {JWT}".
     * @param body contiene "username" e "password" come stringhe in formato JSON
     * @return JSON con "accessToken" che rappresenta JWT firmato dal server con payload lo username
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String body) {
        return Response.accepted().build();
    }

    /**
     * Permette la registrazione attraverso username e password.
     * @param body contiene "username" e "password" come stringhe in formato JSON
     * @return JSON con "accessToken" che rappresenta JWT firmato dal server con payload lo username
     */
    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(String body) {
        return Response.accepted().build();
    }

    /**
     * Permette il cambio della password
     * @param headers http headers, per token
     * @param body contiene "old_password" e "new_password"
     */
    @POST
    @Path("/change-password")
    @Consumes(MediaType.APPLICATION_JSON)
    public void changePassword(@Context HttpHeaders headers, String body) {
    }
}
