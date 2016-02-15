package io.farragolabs.bugbuster;

import com.google.common.collect.FluentIterable;
import org.apache.commons.lang.StringUtils;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

public class PageUtils {
    private static PegDownProcessor processor;

    public static final String HEADER = "  <head>\n" +
            "    <title>Bug_buster</title>\n" +
            "    <link rel=\"stylesheet\" type=\"text/css\"\n" +
            "      href=\"http://yegor256.github.io/tacit/tacit.min.css\"/>\n" +
            "  </head>" +
            " <nav>Bug_buster</nav>\n";

    public static String form(String method, String url, String... inputs) {
        return "<form method='" + method + "' action='" + url + "'>" + StringUtils.join(inputs) + "</form>";
    }

    public static String bug(File file1, String url, String title, boolean isOpen, String description, String... tags) {

        List<String> transform = FluentIterable.from(Arrays.asList(tags))
                .filter(new TagFilter())
                .transform(new TagTransformer())
                .toList();

        String subscriptTag;
        String openClose;

        if(isOpen)
        {
            subscriptTag = "<span style='background-color:green; padding-left:5px; padding-right:5px;padding-top:2px;padding-bottom:2px; border-radius:5px;color:white;margin-bottom:-12px;font-size:12px;'>open</span>";
            openClose = "<button onclick=\"window.location.href='" + url + "?mode=close'\">Close</button>";
        }
        else {
            subscriptTag = "<span style='background-color:red; padding-left:5px; padding-right:5px;padding-top:2px;padding-bottom:2px; border-radius:5px;color:white;margin-bottom:-12px;font-size:12px;'>closed</span>";
            openClose = "<button onclick=\"window.location.href='" + url + "?mode=reopen'\">Reopen</button>";
        }

        return "<section>" +
                "<h1>" + title +"&nbsp;"+ subscriptTag+"</h1>" +
                "<button onclick=\"window.location.href='" + url + "?mode=edit'\" >Edit</button>&nbsp;" +
                openClose +
                "<br />" +
                StringUtils.join(transform, "&nbsp;") +
                "<br />" +
                "<br />" +
                "<hr />" +
                description +
                "<br />" +
                "<br />" +
                "<hr />" +
                "<form method='POST' action='/comments' >" +
                "<input type='hidden' name='file' value='"+file1.getAbsolutePath()+"' />" +
                "<textarea name='comment' style='width:80%;height:10%;''>" +
                "</textarea>" +
                "<button>Add comments</button>" +
                "</form>" +
                "</section>";
    }

    public static String markDownToHtml(String body) {
        if (processor == null) {
            processor = new PegDownProcessor(Extensions.ALL);
        }

        return processor.markdownToHtml(body);
    }

    public static String editableBug(File file1, String title, String body, String[] strings) {
        return "<section><form action='/bugSave' method='POST' >" +
                "<label>Title</label>" +
                "<input type='text' name='title' value='" + escapeHtml(title) + "' style='width:80%;' />" +

                "<input type='hidden' name='id' value='" + file1.getAbsolutePath() + "'  />" +
                "<br />" +
                "<label>Body</label>" +
                "<textarea name='body' style='width:80%;height:35%;'>" + body + "</textarea>" +
                "<br />" +
                "<label>Tags</label>" +
                "<input type='text' name='tags' value='" + StringUtils.join(Arrays.asList(strings), " ") + "' style='width:80%;'/>" +
                "<br /><button>Save</button>&nbsp;</section>";
    }

    private static class TagTransformer implements com.google.common.base.Function<String, String> {

        @Override
        public String apply(String s) {
            return "<span style=\"border-radius:5px;background-color:orange; padding:5px;color:white;\">" + s + "</span>";
        }
    }

    private static class TagFilter implements com.google.common.base.Predicate<String> {
        @Override
        public boolean apply(String s) {
            return !s.isEmpty();
        }
    }
}
