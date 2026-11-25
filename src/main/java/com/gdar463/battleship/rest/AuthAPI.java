package com.gdar463.battleship.rest;

import com.gdar463.battleship.database.BucketType;
import com.gdar463.battleship.database.Database;
import com.gdar463.battleship.model.User;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;

@Path("/auth")
public class AuthAPI {
    /**
     * Permette il login attraverso username e password, inviando nel header Set-Cookie: "Authentication: Bearer {JWT}".
     *
     * @param body contiene "username" e "password" come stringhe in formato JSON
     * @return JSON con "accessToken" che rappresenta JWT firmato dal server con payload lo username
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(JsonObject body) {
        User user = (User) Database.INSTANCE.get(BucketType.USER, body.getString("username"));
        if (user == null) {
            return Response.status(401).build();
        }
        if (!user.verifyPassword(body.getString("password"))) {
            return Response.status(401).build();
        }
        String jwt = user.createJWT();
        NewCookie tokenCookie = new NewCookie.Builder("accessToken")
                .value(jwt)
                .path("/")
                .maxAge(60 * 60 * 24)
                .sameSite(NewCookie.SameSite.LAX)
                .build();
        return Response.ok("{\"accessToken\":\"" + jwt + "\"}")
                .cookie(tokenCookie)
                .build();
    }

    /**
     * Permette la registrazione attraverso username e password.
     *
     * @param body contiene "username" e "password" come stringhe in formato JSON
     * @return JSON con "accessToken" che rappresenta JWT firmato dal server con payload lo username
     */
    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(JsonObject body) {
        return Response.accepted().build();
    }

    /**
     * Permette il cambio della password
     *
     * @param headers http headers, per token
     * @param body    contiene "old_password" e "new_password"
     */
    @POST
    @Path("/change-password")
    @Consumes(MediaType.APPLICATION_JSON)
    public void changePassword(@Context HttpHeaders headers, String body) {
    }
}
