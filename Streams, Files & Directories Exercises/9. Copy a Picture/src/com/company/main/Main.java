package com.company.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        File picture = new File(dir + "\\world_of_warcraft_shadowlands.jpg");

        try (FileInputStream in = new FileInputStream(picture);
        FileOutputStream out = new FileOutputStream(new File(dir + "\\copy-picture.jpg"))) {

            int bytesRead;
            while ((bytesRead = in.read()) != -1) {
                out.write(bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
