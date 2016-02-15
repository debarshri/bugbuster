package io.bugbuster.route.mode;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import spark.Request;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static io.bugbuster.PageUtils.HEADER;
import static io.bugbuster.PageUtils.bug;
import static io.bugbuster.PageUtils.markDownToHtml;

public class ReadOnlyMode implements Mode {
    private final File file1;
    private final Request request;

    public ReadOnlyMode(File file1, Request request) {

        this.file1 = file1;
        this.request = request;
    }

    @Override
    public String body() throws JSONException, IOException {
        JSONObject bug = new JSONObject(FileUtils.readFileToString(file1));

        JSONArray rawTags = bug.getJSONArray("tags");
        List<String> tags = Lists.newArrayList();

        for (int i = 0; i < rawTags.length(); i++) {
            tags.add(rawTags.getString(i));
        }

        String body = markDownToHtml(bug.getString("body"));

        return HEADER + bug(file1,
                request.url(),
                bug.getString("title"),
                bug.getBoolean("open"),
                body,
                tags.toArray(new String[tags.size()]));
    }
}
