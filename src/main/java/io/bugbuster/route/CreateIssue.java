package io.bugbuster.route;

import io.bugbuster.BugListConfigurationModel;
import org.apache.commons.io.FileUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

public class CreateIssue implements Route {
    public Object handle(Request request, Response response) {

        JSONObject jsonObject = new JSONObject();
        long value = 0;
        try {

            String appname = URLDecoder.decode(request.params("appname"),"UTF-8");

            jsonObject.put("title", URLDecoder.decode(request.params("title"),"UTF-8"));
            jsonObject.put("body",URLDecoder.decode(request.params("body"),"UTF-8"));
            jsonObject.put("appname", appname);
            jsonObject.put("create_at", System.currentTimeMillis());
            jsonObject.put("modified_at", System.currentTimeMillis());
            jsonObject.put("open", true);

            value = new File(BugListConfigurationModel.BUG_BUSTER_HOME + "/" + appname).list().length+1;

            jsonObject.put("id", appname+"-"+value);

            File file = new File(BugListConfigurationModel.BUG_BUSTER_HOME + "/" + appname + "/" + value + ".json");
            FileUtils.writeStringToFile(file,jsonObject.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "  <head>\n" +
                "    <title>Subscribe</title>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\"\n" +
                "      href=\"http://yegor256.github.io/tacit/tacit.min.css\"/>\n" +
                "  </head>\n"+
                "<a href=\"/bug/\""+value+">"+value+"</a>";
    }
}
