package io.bugbuster.route;

import com.google.common.collect.Lists;
import io.bugbuster.BugListConfigurationModel;
import io.bugbuster.PageUtils;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class AppList implements Route {
    public Object handle(Request request, Response response) {
        String body = "";
        List<String> strings = Lists.newArrayList(BugListConfigurationModel.BUGLIST_DIR.list());

        for(String element : strings)
        {
            body = body + "<a href=\"/app/"+element+"\">"+element+"</a><br />";
        }

        return   PageUtils.HEADER +
                body;
    }
}
