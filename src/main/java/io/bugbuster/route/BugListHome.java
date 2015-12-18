package io.bugbuster.route;

import spark.Request;
import spark.Response;
import spark.Route;

public class BugListHome implements Route {
    public Object handle(Request request, Response response) {
        return "<html>\n" +
                "  <head>\n" +
                "    <title>Bug_buster</title>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\"\n" +
                "      href=\"http://yegor256.github.io/tacit/tacit.min.css\"/>\n" +
                "  </head>\n" +
                "  <body>" +
                "  <header>\n" +
                "        <nav>\n" +
                "          <ul>\n" +
                "            <li>bugbuster</li>\n" +
                "          </ul>\n" +
                "        </nav>\n" +
                "      </header>" +
                "    <section>\n" +
                "      <a href=\"/app\">See all apps</a>" +
                "<hr />" +
                "      <section> "+
                "           <form action=\"/search\">\n" +
                "                Search <input name=\"appname\" type=\"text\"/> " +
                "               <button type=\"submit\">Go</button>\n" +
                "           </form>" +
                "      </section>\n" +
                "<hr />" +
                "       <section> "+
                "           <form action=\"/create-app\">\n" +
                "                App name <input name=\"appname\" type=\"text\"/> " +
                "               <button type=\"submit\">Create</button>\n" +
                "           </form>" +
                "      </section>\n" +
                "  </body>\n" +
                "</html>";
    }
}
