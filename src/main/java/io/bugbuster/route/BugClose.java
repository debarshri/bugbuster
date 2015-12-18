package io.bugbuster.route;

import spark.Request;
import spark.Response;
import spark.Route;

public class BugClose implements Route {
    public Object handle(Request request, Response response) {
        return  "  <head>\n" +
                "    <title>Subscribe</title>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\"\n" +
                "      href=\"http://yegor256.github.io/tacit/tacit.min.css\"/>\n" +
                "  </head>\n";
    }
}
