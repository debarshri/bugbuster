package io.bugbuster;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.net.URLDecoder;
import java.util.Collection;

public class App implements Route {
    public Object handle(Request request, Response response) {
        final String appname = URLDecoder.decode(request.params("appname"));
        Collection<String> transform = Collections2
                .transform(
                        Lists.newArrayList(
                                new File(BugListConfigurationModel.BUG_BUSTER_HOME + "/"
                                        + appname)
                                        .list()), new Function<String, String>() {
                            public String apply(String s) {
                                return appname + "-" + s.replaceAll(".json", "");
                            }
                        });

        String body = "";

        for(String collection : transform)
        {
            body = body + "<a href=\"/bug/"+collection+"\">"+collection+"</a>";
        }

        return PageUtils.HEADER+
                "<section>"+
                "  <a href='/create-issue/"+appname+"'>Create issue<a/>" +
                " <hr />"
                +body
        +"</section>";
    }
}
