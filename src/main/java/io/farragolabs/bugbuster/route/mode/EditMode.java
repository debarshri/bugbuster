package io.farragolabs.bugbuster.route.mode;

import com.google.common.collect.Lists;
import io.farragolabs.bugbuster.PageUtils;
import org.apache.commons.io.FileUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static io.farragolabs.bugbuster.PageUtils.HEADER;

public class EditMode implements Mode {
    private final File file1;

    public EditMode(File file1) {
        this.file1 = file1;
    }

    @Override
    public String body() throws IOException, JSONException {
        JSONObject bug = new JSONObject(FileUtils.readFileToString(file1));

        JSONArray rawTags = bug.getJSONArray("tags");
        List<String> tags = Lists.newArrayList();

        for (int i = 0; i < rawTags.length(); i++) {
            tags.add(rawTags.getString(i));
        }

        return HEADER + PageUtils.editableBug(file1, bug.getString("title"),
                bug.getString("body"), tags.toArray(new String[tags.size()]));
    }
}
