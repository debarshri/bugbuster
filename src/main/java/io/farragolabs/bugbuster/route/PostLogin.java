package io.farragolabs.bugbuster.route;

import com.google.common.collect.Lists;
import io.farragolabs.bugbuster.BugListConfigurationModel;
import io.farragolabs.bugbuster.JWT;
import org.apache.commons.io.FileUtils;
import org.mindrot.jbcrypt.BCrypt;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.util.List;

public class PostLogin implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {

        String username = request.queryParams("username");
        String password = request.queryParams("password");

        List<String> userList = Lists.newArrayList(BugListConfigurationModel.BUG_BUSTER_USER_DIR.list());

        if (userList.contains(username)) {
            String readSaltedHash = FileUtils.readFileToString(new File(BugListConfigurationModel.BUG_BUSTER_USER_DIR.getAbsolutePath() + "/" + username + "/info"));
            if (BCrypt.checkpw(password, readSaltedHash)) {
                response.cookie("user_auth", JWT.sign(username));
                response.cookie("user_name", username);
                response.redirect("/v1");
            }
        }

        return "Couldn't login";
    }
}
