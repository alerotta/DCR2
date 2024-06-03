import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class test {
    public static void main(String[] args) {
        try (FileInputStream fileIn = new FileInputStream("/Users/alessandrorotta/desktop/index.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            ConcurrentHashMap <String, ArrayList <Integer>> map = (ConcurrentHashMap <String, ArrayList <Integer>>) in.readObject();
            System.out.println("HashMap loaded from hashmap.ser");
            System.out.println("Contents: " + map);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
