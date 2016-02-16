package io.farragolabs.bugbuster.route;

import io.farragolabs.bugbuster.BugListConfigurationModel;
import org.apache.commons.lang.StringEscapeUtils;
import org.codehaus.jettison.json.JSONException;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.io.IOException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Bug implements Route {

    public Object handle(Request request, Response response) {
        try {
            String mode = StringEscapeUtils.escapeHtml(request.queryParams("mode"));
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            String[] bugids = request.params("bugid").split("-");

            File file1 = new File(BugListConfigurationModel.BUG_BUSTER_HOME + "/" +
                    bugids[bugids.length - 2] + "/" +
                    bugids[bugids.length - 1] + ".json");

            return ModeFactory
                    .createInstance(mode,file1,executorService,request, response)
                    .body();

        } catch (IOException | JSONException e) {
            e.printStackTrace();

            return "  <head>\n" +
                    "    <title>Subscribe</title>\n" +
                    "    <link rel=\"stylesheet\" type=\"text/css\"\n" +
                    "      href=\"http://yegor256.github.io/tacit/tacit.min.css\"/>\n" +
                    "  </head>\n" + "No result";
        }
    }
}
