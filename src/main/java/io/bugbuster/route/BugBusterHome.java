package io.bugbuster.route;

import io.bugbuster.PageUtils;
import spark.Request;
import spark.Response;
import spark.Route;

public class BugBusterHome implements Route {

        private static final String SEARCH = "  <section> "+
                "           <form action=\"/search\">\n" +
                "                Search <input name=\"appname\" type=\"text\"/> " +
                "               <button type=\"submit\">Go</button>\n" +
                "           </form>" +
                "      </section>\n";

        private static final String CREATE_APP =   "<section> " +
                "<button >Search</button>"+
                "           <form id='createapp' action=\"/create-app\">\n" +
                "                App name <input name=\"appname\" type=\"text\"/> " +
                "               <button type=\"submit\">Create</button>\n" +
                "           </form>" +
                "      </section>\n";
    public Object handle(Request request, Response response) {
        return "<html>\n" +
                PageUtils.HEADER+
                "    <section>\n" +
                "      <a href=\"/app\">See all apps</a>" +
                "<hr />" +
                SEARCH+
                "<hr />" +
                CREATE_APP+
                "  </body>\n" +
                "</html>";
    }
}
