import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ReaderController {

    private static HashMap <Integer,String> documents;
    private static ConcurrentHashMap <String, ArrayList <Integer>> index;


    public ReaderController (String filename){

        documents = new HashMap<>();
        index = new ConcurrentHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int docCounter = 0 ;
            while ((line = br.readLine()) != null) {
                documents.put(docCounter, line);
                docCounter ++ ;  
            }
        } 

        catch (IOException e) {
            e.printStackTrace();

        }


    }


    public static void main(String[] args){
        ReaderController main = new ReaderController("/Users/alessandrorotta/Desktop/DCR2/filenames.txt");
        Reader r = new Reader(documents, 0,index);
        r.start();

        try {
            r.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        System.out.println(index.size());
    }
    
}
