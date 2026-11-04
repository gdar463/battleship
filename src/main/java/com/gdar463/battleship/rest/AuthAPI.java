package com.gdar463.battleship.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;

@Path("/api/auth")
public class AuthAPI {
    /**
     * Permette il login attraverso username e password.
     * @param body contiene "username" e "password" come stringhe in formato JSON
     * @return JSON con "accessToken" che rappresenta JWT firmato dal server con payload lo username
     */
    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public String login(String body) {
        return "{}";
    }

    /**
     * Permette la registrazione attraverso username e password.
     * @param body contiene "username" e "password" come stringhe in formato JSON
     * @return JSON con "accessToken" che rappresenta JWT firmato dal server con payload lo username
     */
    @POST
    @Path("/signup")
    @Consumes("application/json")
    @Produces("application/json")
    public String signup(String body) {
        return "{}";
    }

    /**
     * Permette il cambio della password
     * @param headers http headers, per token
     * @param body contiene "old_password" e "new_password"
     */
    @POST
    @Path("/change-password")
    @Consumes("application/json")
    public void changePassword(@Context HttpHeaders headers, String body) {
    }
}
