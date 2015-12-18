package io.bugbuster.route;

import io.bugbuster.BugListConfigurationModel;
import io.bugbuster.PageUtils;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;

public class CreateApp implements Route {
    public Object handle(Request request, Response response) {
        String appname2 = request.params("appname");

        if(appname2 == null)
        {
            String appname = request.queryParams("appname");
            String appname1 = BugListConfigurationModel.BUGLIST_HOME + "/" + appname;
            System.out.println(appname1);
            File appBugDir = new File(appname1);
            boolean mkdir = appBugDir.mkdir();

            if(mkdir)
            {
                response.redirect("/app/"+appname);
            }
            return PageUtils.HEADER+ mkdir;
        }
        else
        {
            return "Cannot create";
        }
    }
}
