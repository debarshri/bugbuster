package io.bugbuster.route;

import com.google.common.collect.Lists;
import io.bugbuster.BugListConfigurationModel;
import io.bugbuster.PageUtils;
import io.bugbuster.route.runnable.WriterRunnable;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.bugbuster.PageUtils.*;

public class Bug implements Route {
    public Object handle(Request request, Response response) {
        try {
            String v = StringEscapeUtils.escapeHtml(request.queryParams("v"));
            String data;
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            String[] bugids = request.params("bugid").split("-");

            if (v == null) {

                JSONObject bug = new JSONObject(FileUtils.readFileToString(new File(BugListConfigurationModel.BUG_BUSTER_HOME + "/" +
                        bugids[bugids.length - 2] + "/" +
                        bugids[bugids.length - 1] + ".json")));

                JSONArray rawTags = bug.getJSONArray("tags");
                List<String> tags = Lists.newArrayList();

                for(int i=0; i < rawTags.length(); i++)
                {
                    tags.add(rawTags.getString(i));
                }

                return HEADER+ bug(bug.getString("title"), bug.getString("body"),tags.toArray(new String[tags.size()]));

            } else if (v.toUpperCase().equals("REOPEN")) {

                String pathname = BugListConfigurationModel.BUG_BUSTER_HOME + "/" +
                        bugids[bugids.length - 2] + "/" +
                        bugids[bugids.length - 1] + ".json";

                File file = new File(pathname);
                data = new JSONObject(FileUtils.readFileToString(file))
                        .put("open", true)
                        .put("modified_at", System.currentTimeMillis()).toString();

                executorService.submit(new WriterRunnable(file, data));

                return data;
            } else if (v.toUpperCase().equals("CLOSE")) {
                String pathname = BugListConfigurationModel.BUG_BUSTER_HOME + "/" +
                        bugids[bugids.length - 2] + "/" +
                        bugids[bugids.length - 1] + ".json";
                File file = new File(pathname);

                data = new JSONObject(FileUtils.readFileToString(file))
                        .put("open", false)
                        .put("modified_at", System.currentTimeMillis()).toString();

                executorService.submit(new WriterRunnable(file, data));
                return data;

            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return "  <head>\n" +
                "    <title>Subscribe</title>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\"\n" +
                "      href=\"http://yegor256.github.io/tacit/tacit.min.css\"/>\n" +
                "  </head>\n" + "No result";
    }
}
