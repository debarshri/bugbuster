package io.farragolabs.bugbuster.route;

import io.farragolabs.bugbuster.route.mode.*;
import spark.Request;
import spark.Response;

import java.io.File;
import java.util.concurrent.ExecutorService;

public class ModeFactory {
    public static Mode createInstance(String mode, File file1,
                                      ExecutorService executorService,
                                      Request request, Response response) {

        if (mode != null && mode.toUpperCase().equals("EDIT")) {
            return new EditMode(file1);
        } else if (mode == null || mode.isEmpty()) {
            return new ReadOnlyMode(file1, request);
        } else if (mode.toUpperCase().equals("REOPEN")) {
            return new ReopenMode(file1, executorService, response);
        } else if (mode.toUpperCase().equals("CLOSE")) {
            return new CloseMode(file1, executorService, response);
    } else if (mode.toUpperCase().equals("DELETE")) {

        return new DeleteMode(file1, response);
    }else {
            return new NoResultMode();
        }
    }
}
