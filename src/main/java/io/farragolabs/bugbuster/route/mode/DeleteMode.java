package io.farragolabs.bugbuster.route.mode;

import org.codehaus.jettison.json.JSONException;
import spark.Response;

import java.io.File;
import java.io.IOException;

public class DeleteMode implements Mode {
    private final File file1;
    private Response response;

    public DeleteMode(File file1, Response response) {
        this.file1 = file1;
        this.response = response;
    }

    @Override
    public String body() throws JSONException, IOException {
        String[] split = file1.getParent().split("/");
        file1.delete();
        response.redirect("/v1/app/" + split[split.length - 1]);
        return null;
    }
}
