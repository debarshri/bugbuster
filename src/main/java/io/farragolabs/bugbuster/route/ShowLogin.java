package io.farragolabs.bugbuster.route;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;

public class ShowLogin implements Route {
    private HandlebarsTemplateEngine handlebarsTemplateEngine;

    public ShowLogin(HandlebarsTemplateEngine handlebarsTemplateEngine) {

        this.handlebarsTemplateEngine = handlebarsTemplateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return handlebarsTemplateEngine.render(new ModelAndView(new HashMap<>(), "login.hbs"));
    }
}
