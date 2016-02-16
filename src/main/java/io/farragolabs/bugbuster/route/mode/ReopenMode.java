package io.farragolabs.bugbuster.route.mode;

import io.farragolabs.bugbuster.route.runnable.WriterRunnable;
import org.apache.commons.io.FileUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import spark.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;


public class ReopenMode implements Mode {
    private final File file1;
    private final ExecutorService executorService;
    private Response response;

    public ReopenMode(File file1, ExecutorService executorService, Response response) {

        this.file1 = file1;
        this.executorService = executorService;
        this.response = response;
    }

    @Override
    public String body() throws JSONException, IOException {

        String data = new JSONObject(FileUtils.readFileToString(file1))
                .put("open", true)
                .put("modified_at", System.currentTimeMillis()).toString();


        executorService.submit(new WriterRunnable(file1, data));

        String[] split = file1.getAbsolutePath().split("/");
        response.redirect("/v1/bug/" + split[split.length - 2] + "-" + split[split.length - 1].replaceAll(".json", ""));
        return data;
    }
}
