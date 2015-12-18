package io.bugbuster;

import io.bugbuster.route.*;
import spark.Spark;

import java.io.File;

public class ServerMain {
    public static void main(String[] args) {

        setup();
        Spark.setPort(9090);
        Spark.get("/",new BugListHome());
        Spark.get("/app",new AppList());
        Spark.get("/app/:appname",new App());
        Spark.get("/create-app",new CreateApp());
        Spark.get("/create-issue/:appname",new CreateIssuePage());
        Spark.get("/creating-issue/:appname",new CreateIssue());
        Spark.get("/bug/:bugid",new Bug());
        Spark.get("/link/:bug_id1/to/:bug_id2",new CreateLink());

    }

    private static void setup() {
        BugListConfigurationModel.BUGLIST_HOME = System.getProperty("bugify.home");
        if(BugListConfigurationModel.BUGLIST_HOME == null)
        {
            BugListConfigurationModel.BUGLIST_HOME = "bugify-workspace/";
        }

        BugListConfigurationModel.BUGLIST_DIR = new File(BugListConfigurationModel.BUGLIST_HOME);
        System.out.println(BugListConfigurationModel.BUGLIST_DIR.mkdir());
    }
}
