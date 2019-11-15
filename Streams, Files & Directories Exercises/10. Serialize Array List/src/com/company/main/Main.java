package com.company.main;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Double> listToSerialize = new ArrayList<>();
        listToSerialize.add(Math.PI);
        listToSerialize.add(42.);
        listToSerialize.add(-Math.PI);
        listToSerialize.add(Math.E);
        listToSerialize.add(-Math.E);
        listToSerialize.add(-42.);

        //save serialized list
        try (FileOutputStream out = new FileOutputStream("D:\\ALEX\\Java Advanced\\" +
        "9. 10. Streams, Files & Directories + Exercise\\" +
        "Exercise\\10. Serialize Array List\\list.ser");
        ObjectOutputStream writer = new ObjectOutputStream(out)) {
            writer.writeObject(listToSerialize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //load serialized list
        try (FileInputStream in = new FileInputStream("D:\\ALEX\\Java Advanced\\" +
        "9. 10. Streams, Files & Directories + Exercise\\" +
        "Exercise\\10. Serialize Array List\\list.ser");
        ObjectInputStream reader = new ObjectInputStream(in)) {
            ArrayList<Double> deserialized = (ArrayList<Double>) reader.readObject();
            for (double d : deserialized) {
                System.out.printf("%s ", d);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
