package io.bugbuster.route.runnable;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class WriterRunnable implements Runnable {
    private final File file;
    private final String data;

    public WriterRunnable(File file, String data) {

        this.file = file;
        this.data = data;
    }

    @Override
    public void run() {
        try {
            FileUtils.writeStringToFile(file, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
