package io.farragolabs.bugbuster.route.mode;


import org.codehaus.jettison.json.JSONException;

import java.io.IOException;

public interface Mode {
    String body() throws JSONException, IOException;
}
