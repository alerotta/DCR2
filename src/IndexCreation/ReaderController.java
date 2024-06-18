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
   


    public ReaderController (String filename,String stopWordsFileName){


        documents = new HashMap<>();
        index = new ConcurrentHashMap<>();
        stopWords =  new ArrayList<>();

      

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

        int startindex = 0;
        int finalindex = 0;
        int numberOfThreads = 8;
        ArrayList <Reader> threads = new ArrayList();
        for (int i =  0; i < numberOfThreads; i++){
            int numberOfDocuments = documents.size();
            int documentsEach = numberOfDocuments / numberOfThreads;

            if ( i != 7){
            finalindex = startindex + documentsEach;
            }
            else{
                finalindex = documents.size() - 1;
            }

            threads.add(new Reader(documents, stopWords, startindex, finalindex, index));
            startindex = finalindex + 1;
    
        }
        


        for (Reader r : threads) {
            r.start();
        }

        for (Reader r : threads) {
            try {
                r.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted");
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream("/Users/alessandrorotta/desktop/index.ser");

            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(index);
            System.out.println("HashMap saved to index.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(index);
    }
    
}
