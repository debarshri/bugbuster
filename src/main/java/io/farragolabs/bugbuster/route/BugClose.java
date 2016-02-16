package io.farragolabs.bugbuster.route;

import io.farragolabs.bugbuster.PageUtils;
import spark.Request;
import spark.Response;
import spark.Route;

public class BugClose implements Route {
    public Object handle(Request request, Response response) {
        return PageUtils.HEADER;
    }
}
