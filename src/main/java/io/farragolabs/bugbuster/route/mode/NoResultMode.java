package io.farragolabs.bugbuster.route.mode;

import org.codehaus.jettison.json.JSONException;

import java.io.IOException;


public class NoResultMode implements Mode {
    @Override
    public String body() throws JSONException, IOException {
        return "  <head>\n" +
                "    <title>Subscribe</title>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\"\n" +
                "      href=\"http://yegor256.github.io/tacit/tacit.min.css\"/>\n" +
                "  </head>\n" + "Issue does not exist";
    }
}
