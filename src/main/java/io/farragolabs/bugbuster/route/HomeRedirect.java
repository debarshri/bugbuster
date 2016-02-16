package io.farragolabs.bugbuster.route;

import spark.Request;
import spark.Response;
import spark.Route;

public class HomeRedirect implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        response.redirect("/v1");
        return null;
    }
}
