package io.bugbuster;

import com.google.common.collect.FluentIterable;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

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

        List<String> transform  = FluentIterable.from(Arrays.asList(tags))
                .filter(new TagFilter())
                .transform(new TagTransformer())
                .toList();

        return "<section><h1>"+ escapeHtml(title)+"</h1>"+
                "<p>"+escapeHtml(description)+"</p>"+
                StringUtils.join(transform,"&nbsp;")+
                "<button>Edit</button>&nbsp;"+
                "<button>Delete</button>"+
                "</section>" ;
    }

    private static class TagTransformer implements com.google.common.base.Function< String, String> {

        @Override
        public String apply(String s) {
            return "<span style=\"border-radius:5px;background-color:orange; padding:5px;color:white;\">"+s+"</span>";
        }
    }

    private static class TagFilter implements com.google.common.base.Predicate<String> {
        @Override
        public boolean apply(String s) {
            return !s.isEmpty();
        }
    }
}
