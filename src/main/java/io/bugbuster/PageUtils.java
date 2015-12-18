package io.bugbuster;

import org.apache.commons.lang.StringUtils;

public class PageUtils {
    public static final String HEADER = "  <head>\n" +
                "    <title>Bug_buster</title>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\"\n" +
                "      href=\"http://yegor256.github.io/tacit/tacit.min.css\"/>\n" +
                "  </head>" +
                " <nav>Bug_buster</nav>\n";

    public static String form(String method, String url, String...inputs) {
        return "<form method='"+method+"' action='"+url+"'>"+StringUtils.join(inputs)+"</form>";
    }
}
