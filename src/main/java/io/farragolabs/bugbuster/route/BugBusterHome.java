package io.farragolabs.bugbuster.route;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;

public class BugBusterHome implements Route {

    private static final String SEARCH = "  <section> " +
            "           <form action=\"/search\">\n" +
            "                Search <input name=\"appname\" type=\"text\"/> " +
            "               <button type=\"submit\">Go</button>\n" +
            "           </form>" +
            "      </section>\n";

    private static final String CREATE_APP = "<section> " +
            "<button >Search</button>" +
            "           <form id='createapp' action=\"/create-app\">\n" +
            "                App name <input name=\"appname\" type=\"text\"/> " +
            "               <button type=\"submit\">Create</button>\n" +
            "           </form>" +
            "      </section>\n";

    public String handle(Request request, Response response) {
        HandlebarsTemplateEngine handlebarsTemplateEngine = new HandlebarsTemplateEngine();
        return handlebarsTemplateEngine.render(new ModelAndView(new HashMap<>(), "index.hs"));

        /*
        return "<html>\n" +
                PageUtils.HEADER +
                "    <section>\n" +
                "      <a href=\"/app\">See all apps</a>" +
                "<hr />" +
                SEARCH +
                "<hr />" +
                CREATE_APP +
                "  </body>\n" +
                "</html>";
                */
    }
}
