package com.company.main;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        try {
            //create .zip archive
            ZipOutputStream zipOS = new ZipOutputStream(
                    new FileOutputStream(dir + "\\text files.zip"));

            //read file
            FileInputStream in = new FileInputStream(
                    new File(dir + "\\input.txt"));

            int readByte;
            zipOS.putNextEntry(new ZipEntry("input.txt"));

            while ((readByte = in.read()) != -1) {
                zipOS.write(readByte);
            } zipOS.closeEntry();

            in = new FileInputStream(
                    new File(dir + "\\text.txt"));
            zipOS.putNextEntry(new ZipEntry("text.txt"));
            while ((readByte = in.read()) != -1) {
                zipOS.write(readByte);
            } zipOS.closeEntry();

            in = new FileInputStream(
                    new File(dir + "\\words.txt"));
            zipOS.putNextEntry(new ZipEntry("words.txt"));
            while ((readByte = in.read()) != -1) {
                zipOS.write(readByte);
            } zipOS.closeEntry();

            zipOS.finish();
            zipOS.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
