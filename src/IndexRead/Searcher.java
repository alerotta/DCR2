package IndexRead;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Searcher {
    
    private  ConcurrentHashMap <String, ArrayList <Integer>> index;
    private static HashMap <Integer,String> documents;

    public Searcher (){
    this.index = readfile("/Users/alessandrorotta/desktop/index.ser");

    documents = new HashMap<>();
    try (BufferedReader br = new BufferedReader(new FileReader("/Users/alessandrorotta/Desktop/DCR2/filenames.txt"))) {
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

    public ArrayList <Integer> search (String term){

        String[] terms = term.split(" and ");
        if (terms[0].length() != term.length()){
            System.out.println("and query");
            ArrayList  <ArrayList <Integer>> lists  =  new ArrayList<>();
            for (String t : terms) {
                ArrayList <Integer> temp = index.get(t);
                if (temp ==  null){return null;}
                    lists.add(index.get(t));
            }
            ArrayList <Integer> finalResult = lists.get(0);
             for (ArrayList <Integer> l : lists) {
                finalResult.retainAll(l);
             }

             if (finalResult.size() == 0){return null;}
            return finalResult;

        }

        terms = term.split(" or ");
        if (terms[0].length() != term.length()){
            System.out.println("or query");
            ArrayList  <ArrayList <Integer>> lists  =  new ArrayList<>();
            for (String t : terms) {
                ArrayList <Integer> temp = index.get(t);
                if (temp ==  null){return null;}
                    lists.add(index.get(t));
            }
            ArrayList <Integer> finalResult = lists.get(0);
            for (ArrayList <Integer> l : lists) {
                for (Integer element : l) {
                    if (!finalResult.contains(element)){
                        finalResult.add(element);
                    }
                    
                }
             }
            return finalResult;

        }

        System.out.println(term);


        ArrayList <Integer> postingList = index.get(term);

        if (postingList == null){
            return null;
        }
        else {
            return postingList;
        }
    }

    public  HashMap <Integer,String>  getDocuments (){
        return documents;
    };
    
}
