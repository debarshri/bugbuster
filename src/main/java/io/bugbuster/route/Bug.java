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

public class Bug implements Route {
    public Object handle(Request request, Response response) {

        String v = request.queryParams("v");

        if(v == null)
        {
            String[] bugids = request.params("bugid").split("-");
            try {
                String pathname = BugListConfigurationModel.BUGLIST_HOME + "/" + bugids[bugids.length - 2]+"/"+ bugids[bugids.length-1]+".json";

                System.out.println(pathname);
                return FileUtils.readFileToString(new File(pathname));
            } catch (IOException e) {
                e.printStackTrace();
            }        }
        else if (v.toUpperCase().equals("REOPEN"))
            {
                String[] bugids = request.params("bugid").split("-");
                try {
                    String pathname = BugListConfigurationModel.BUGLIST_HOME + "/" + bugids[bugids.length - 2]+"/"+ bugids[bugids.length-1]+".json";
                    File file = new File(pathname);
                    JSONObject jsonObject = new JSONObject(FileUtils.readFileToString(file));
                    jsonObject.put("open",true);
                    jsonObject.put("modified_at",System.currentTimeMillis());

                    String data = jsonObject.toString();
                    FileUtils.writeStringToFile(file, data);

                    return data;

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if (v.toUpperCase().equals("CLOSE"))
            {String[] bugids = request.params("bugid").split("-");
                try {
                    String pathname = BugListConfigurationModel.BUGLIST_HOME + "/" + bugids[bugids.length - 2]+"/"+ bugids[bugids.length-1]+".json";
                    File file = new File(pathname);
                    System.out.println(pathname);
                    JSONObject jsonObject = new JSONObject(FileUtils.readFileToString(file));
                    jsonObject.put("open",false);
                    jsonObject.put("modified_at",System.currentTimeMillis());

                    String data = jsonObject.toString();
                    FileUtils.writeStringToFile(file, data);

                    return data;

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        return  "  <head>\n" +
                "    <title>Subscribe</title>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\"\n" +
                "      href=\"http://yegor256.github.io/tacit/tacit.min.css\"/>\n" +
                "  </head>\n" +"No result";
    }

}
