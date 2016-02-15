package io.bugbuster;

import org.apache.commons.io.FileUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.io.IOException;

public class SaveComments implements Route {
    @Override
    public Object handle(Request request, Response response) {

        try {
            String fileName = request.queryParams("file");
            File file1 = new File(fileName);
            JSONObject file = new JSONObject(FileUtils.readFileToString(file1));
            JSONArray comments;

            if(!file.has("comments"))
            {
                comments = new JSONArray();

            }
            else
            {
                comments = file.getJSONArray("comments");
            }

            comments.put(request.queryParams("comment"));
            file.put("comments",comments);

            FileUtils.writeStringToFile(file1,file.toString());

            String[] split = fileName.split("/");
            response.redirect("/bug/"+ split[split.length-2]+"-"+split[split.length-1].replaceAll(".json",""));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}
