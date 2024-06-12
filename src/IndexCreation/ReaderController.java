package IndexCreation;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ReaderController {

    private static HashMap <Integer,String> documents;
    private static ConcurrentHashMap <String, ArrayList <Integer>> index;
    private static ArrayList <String> stopWords;
   // public ArrayList <Reader> threads;
    private static final int THREADS_NUMBER = 2;
   


    public ReaderController (String filename,String stopWordsFileName){


        documents = new HashMap<>();
        index = new ConcurrentHashMap<>();
        stopWords =  new ArrayList<>();

        // threads =  new ArrayList<>();

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

        try (BufferedReader br = new BufferedReader(new FileReader(stopWordsFileName))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                stopWords.add(line);
            }
        } 

        catch (IOException e) {
            e.printStackTrace();

        }


    }


    public static void main(String[] args){
        ReaderController main = new ReaderController("/Users/alessandrorotta/Desktop/DCR2/filenames.txt","/Users/alessandrorotta/Desktop/DCR2/stopWords.txt");

        
        
        
        Reader r = new Reader(documents,stopWords, 0,1,index);
        Reader r1 = new Reader(documents,stopWords, 1,2, index);
        r.start();
        r1.start();

        try {
            r.join();
            r1.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        try (FileOutputStream fileOut = new FileOutputStream("/Users/alessandrorotta/desktop/index.ser");

            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(index);
            System.out.println("HashMap saved to index.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(index.size());
        // printMap();
    }
    
}
