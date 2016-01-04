package io.bugbuster;

import org.apache.commons.lang.StringUtils;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

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

    public static String bug(String title, String description, String...tags) {
        return "<h1>"+ escapeHtml(title)+"</h1>"+
                "<p>"+escapeHtml(description)+"</p>";
    }
}
