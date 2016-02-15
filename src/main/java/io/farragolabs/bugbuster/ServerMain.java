package io.farragolabs.bugbuster;

import io.farragolabs.bugbuster.route.*;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.File;

public class ServerMain {
    public static void main(String[] args) {

        HandlebarsTemplateEngine handlebarsTemplateEngine = new HandlebarsTemplateEngine();

        setup();
        Spark.port(9090);
        Spark.get("/", new BugBusterHome(handlebarsTemplateEngine));
        Spark.get("/app", new AppList(handlebarsTemplateEngine));
        Spark.get("/app/:appname", new App());
        Spark.get("/create-app", new CreateApp());
        Spark.post("/comments", new SaveComments());
        Spark.get("/create-issue/:appname", new CreateIssuePage());
        Spark.post("/create-issue/:appname", new CreateIssue());
        Spark.get("/creating-issue/:appname", new CreateIssue());
        Spark.get("/bug/:bugid", new Bug());
        Spark.get("/upload", new Upload());
        Spark.post("/bugSave", new BugSave());
        Spark.get("/link/:bug_id1/to/:bug_id2", new CreateLink());
    }

    private static void setup() {
        BugListConfigurationModel.BUG_BUSTER_HOME = System.getProperty("bugify.home");
        if (BugListConfigurationModel.BUG_BUSTER_HOME == null) {
            BugListConfigurationModel.BUG_BUSTER_HOME = "bugify-workspace/";
        }

        BugListConfigurationModel.BUG_BUSTER_DIR = new File(BugListConfigurationModel.BUG_BUSTER_HOME);
        System.out.println(BugListConfigurationModel.BUG_BUSTER_DIR.mkdir());
    }
}
