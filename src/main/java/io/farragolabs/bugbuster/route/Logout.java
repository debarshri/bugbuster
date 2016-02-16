package io.farragolabs.bugbuster.route;

import spark.Request;
import spark.Response;
import spark.Route;

public class Logout implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.removeCookie("user_auth");
        response.removeCookie("user_name");
        response.redirect("/");
        return null;
    }
}
