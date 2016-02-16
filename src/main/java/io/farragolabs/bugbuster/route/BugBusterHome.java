package io.farragolabs.bugbuster.route;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;

public class BugBusterHome implements Route {

    private HandlebarsTemplateEngine handlebarsTemplateEngine;

    public BugBusterHome(HandlebarsTemplateEngine handlebarsTemplateEngine) {

        this.handlebarsTemplateEngine = handlebarsTemplateEngine;
    }

    public String handle(Request request, Response response) {
        return handlebarsTemplateEngine.render(new ModelAndView(new HashMap<>(), "index.hbs"));
    }
}
