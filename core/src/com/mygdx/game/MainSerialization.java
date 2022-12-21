package com.mygdx.game;

import java.io.*;

public class MainSerialization {
    public static void serialize() throws IOException {
        screen s1 = new screen();
        Manager m = new Manager(s1);

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("out.txt"));
            out.writeObject(s1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public static void deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("out.txt"));
            Manager m = (Manager) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }
}
