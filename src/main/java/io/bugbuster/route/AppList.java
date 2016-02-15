package io.bugbuster.route;

import com.google.common.collect.Lists;
import io.bugbuster.BugListConfigurationModel;
import io.bugbuster.PageUtils;
import org.apache.commons.lang.StringUtils;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.List;

public class AppList implements Route {
    public Object handle(Request request, Response response) {
        List<String> strings = Lists.newArrayList(BugListConfigurationModel.BUG_BUSTER_DIR.list());

        return PageUtils.HEADER +
                "<section>" +
                "<hr />" +
                body(strings) + "</section>";
    }

    private String body(List<String> strings) {

        List<String> body = Lists.newArrayList();

        for (String element : strings) {
            body.add("<a href=\"/app/" + element + "\">" + element + "</a><br />");
        }

        return StringUtils.join(body, "");
    }
}
