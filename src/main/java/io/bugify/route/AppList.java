package io.bugify.route;

import com.google.common.collect.Lists;
import io.bugify.BugifyConfigurationModel;
import io.github.debarshri.Bootstrap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class AppList implements Route {
    public Object handle(Request request, Response response) {
        String body = "";
        List<String> strings = Lists.newArrayList(BugifyConfigurationModel.BUGIFY_DIR.list());

        for(String element : strings)
        {
            body = body + "<a href=\"/app/"+element+"\">"+element+"</a><br />";
        }

        return body;
    }
}
