package io.farragolabs.bugbuster.route;

import io.farragolabs.bugbuster.BugListConfigurationModel;
import org.apache.commons.io.FileUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

public class CreateIssue implements Route {
    public Object handle(Request request, Response response) {

        JSONObject jsonObject = new JSONObject();
        long value;

        String appId = "";
        try {
            String appname = URLDecoder.decode(request.params("appname"), "UTF-8");
            String[] tagses = URLDecoder.decode(request.queryParams("tags"), "UTF-8").split(" ");

            JSONArray jsonArray = new JSONArray();

            for (String tag : tagses) {
                jsonArray.put(tag);
            }

            jsonObject.put("title", URLDecoder.decode(request.queryParams("title"), "UTF-8"));
            jsonObject.put("body", URLDecoder.decode(request.queryParams("description"), "UTF-8"));
            jsonObject.put("tags", jsonArray);
            jsonObject.put("appname", escapeHtml(appname));
            jsonObject.put("create_at", System.currentTimeMillis());
            jsonObject.put("modified_at", System.currentTimeMillis());
            jsonObject.put("open", true);

            value = new File(BugListConfigurationModel.BUG_BUSTER_HOME + "/" + appname).list().length + 1;

            appId = appname + "-" + value;
            jsonObject.put("id", appId);

            File file = new File(BugListConfigurationModel.BUG_BUSTER_HOME + "/" + appname + "/" + value + ".json");

            while(file.exists())
            {
                value = value+1;
                file = new File(BugListConfigurationModel.BUG_BUSTER_HOME + "/" + appname + "/" + value + ".json");
                appId = appname + "-" + value;
            }

            FileUtils.writeStringToFile(file, jsonObject.toString());

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        response.redirect("/bug/" + appId);

        return "  <head>\n" +
                "    <title>Subscribe</title>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\"\n" +
                "      href=\"http://yegor256.github.io/tacit/tacit.min.css\"/>\n" +
                "  </head>\n" +
                "<a href=\"/bug/" + appId + "\">" + appId + "</a>";
    }
}
