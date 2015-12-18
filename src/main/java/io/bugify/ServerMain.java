package io.bugify;

import io.bugify.route.*;
import spark.Spark;

import java.io.File;

public class ServerMain {
    public static void main(String[] args) {

        setup();
        Spark.setPort(9090);
        Spark.get("/",new BugifyHome());
        Spark.get("/app",new AppList());
        Spark.get("/app/:appname",new App());
        Spark.get("/create-app/:appname",new CreateApp());
        Spark.get("/create-issue/:appname/title/:title/body/:body",new CreateIssue());
        Spark.get("/bug/:bugid",new Bug());
        Spark.get("/bug/:bugid/close",new BugClose());
        Spark.get("/bug/:bugid/reopen",new BugReopen());


        Spark.get("/link/:bug_id1/to/:bug_id2",new CreateLink());


    }

    private static void setup() {
        BugifyConfigurationModel.BUGIFY_HOME = System.getProperty("bugify.home");
        if(BugifyConfigurationModel.BUGIFY_HOME == null)
        {
            BugifyConfigurationModel.BUGIFY_HOME = "bugify-workspace/";
        }

        BugifyConfigurationModel.BUGIFY_DIR = new File(BugifyConfigurationModel.BUGIFY_HOME);
        System.out.println(BugifyConfigurationModel.BUGIFY_DIR.mkdir());
    }
}
