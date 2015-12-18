package io.bugify.route;

import io.bugify.BugifyConfigurationModel;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;

public class CreateApp implements Route {
    public Object handle(Request request, Response response) {
        String appname1 = BugifyConfigurationModel.BUGIFY_HOME + "/" + request.params("appname");
        System.out.println(appname1);
        File appname = new File(appname1);
        return appname.mkdir();
    }
}
