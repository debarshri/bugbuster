package io.bugbuster.route;

import spark.Request;
import spark.Response;
import spark.Route;

public class Upload implements Route {
    @Override
    public Object handle(Request request, Response response) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "    <head>\n" +
                "        <title>File Upload</title>\n" +
                "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <form method=\"POST\" action=\"upload\" enctype=\"multipart/form-data\" >\n" +
                "            File:\n" +
                "            <input type=\"file\" name=\"file\" id=\"file\" /> <br/>\n" +
                "            Destination:\n" +
                "            <input type=\"text\" value=\"/tmp\" name=\"destination\"/>\n" +
                "            </br>\n" +
                "            <input type=\"submit\" value=\"Upload\" name=\"upload\" id=\"upload\" />\n" +
                "        </form>\n" +
                "    </body>\n" +
                "</html>";
    }
}
