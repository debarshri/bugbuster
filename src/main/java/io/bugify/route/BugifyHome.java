package io.bugify.route;

import io.bugify.BugifyConfigurationModel;
import spark.Request;
import spark.Response;
import spark.Route;

public class BugifyHome implements Route {
    public Object handle(Request request, Response response) {
        return "<a href=\"/app\">See all apps</a>";
    }
}
