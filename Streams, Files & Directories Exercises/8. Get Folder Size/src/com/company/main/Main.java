package com.company.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        String dir = System.getProperty("user.dir");
        File file = new File(dir + "\\.idea");

        File[] files = file.listFiles();
        assert files != null;
        long size = 0;
        for (File f : files) {
            Path path = Paths.get(f.getPath());
            size += Files.size(path);
        }
        System.out.printf("Folder size: %d%n", size);
    }
}
