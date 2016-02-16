package io.farragolabs.bugbuster.route;

import io.farragolabs.bugbuster.BugListConfigurationModel;
import org.apache.commons.io.FileUtils;
import org.mindrot.jbcrypt.BCrypt;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;

import static org.apache.commons.lang3.StringUtils.containsWhitespace;

public class PostCreateUser implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {

        String username = request.queryParams("username");
        String password = request.queryParams("password");
        String passwordAgain = request.queryParams("password-again");

        if (containsWhitespace(password) || containsWhitespace(passwordAgain) || containsWhitespace(username)) {
            return "Dude seriously? Whitespaces in password and username?";
        } else if (!password.equals(passwordAgain)) {
            return "You are blind..Write passwords correctly.";
        } else {
            String pathname = BugListConfigurationModel.BUG_BUSTER_USER_DIR.getAbsolutePath() + "/" + username;
            File file = new File(pathname);
            if (!file.exists()) {
                if (file.mkdir()) {
                    File info = new File(pathname + "/info");
                    FileUtils.writeStringToFile(info, BCrypt.hashpw(password, BCrypt.gensalt()));
                    response.redirect("/login");
                }
            } else {
                return "User already exists";
            }
        }

        return null;
    }
}
