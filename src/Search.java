import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Search {
    
    private  ConcurrentHashMap <String, ArrayList <Integer>> index;

    public Search (){
    this.index = readfile("/Users/alessandrorotta/desktop/index.ser");
    }

    public static ConcurrentHashMap <String, ArrayList <Integer>> readfile(String filename){
        try (FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn)) {
                ConcurrentHashMap <String, ArrayList <Integer>> index = (ConcurrentHashMap <String, ArrayList <Integer>>) in.readObject();
                return index;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void search (String term){
        ArrayList <Integer> postingList = index.get(term);

        if (postingList == null){
            System.out.println("no match found");
        }
        else {
            System.out.println(postingList);
        }
    }
    
}
