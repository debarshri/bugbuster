package io.farragolabs.bugbuster.route;

import io.farragolabs.bugbuster.JWT;
import spark.Filter;
import spark.Request;
import spark.Response;


public class AuthenticationFilter implements Filter {
    @Override
    public void handle(Request request, Response response) throws Exception {

        if (request.cookie("user_auth") == null) {
            response.redirect("/login");
        }

        if (!JWT.verify(request.cookie("user_auth"))) {
            response.redirect("/login");
        }
    }
}
