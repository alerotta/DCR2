package IndexCreation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;




public class Reader extends Thread {
    
    private HashMap <Integer,String>  documentsToReadMap;
    private ConcurrentHashMap <String, ArrayList <Integer>> index;
    private ArrayList<String> stopWords;
    private int startID;
    private int endID;
    private NounChecker nounChecker;
    private String biWord;

    public Reader (HashMap <Integer,String>  documentsToReadMap,ArrayList<String> stopWords, int startID,int endID,ConcurrentHashMap <String, ArrayList <Integer>> index){
        super();
        this.documentsToReadMap = documentsToReadMap;
        this.startID = startID;
        this.endID = endID;
        this.index = index;
        this.stopWords =  stopWords;
        nounChecker = new NounChecker();
        biWord = new String();

    }

    public void generateIndex (HashMap <Integer,String>  documentsToReadMap){


        for (int i = startID; i < endID; i++){

            int currentID = i;
            String filename = documentsToReadMap.get(currentID);
            
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
                while ((line = br.readLine()) != null) {

                    String[] tokens = line.split("\\s+");
                    for (String token : tokens) {

                        token = token.toLowerCase();
                        token =  token.replaceAll("^[\\p{Punct}]+|[\\p{Punct}]+$", "");
                        
                        // checks stopWords
                        
                        if (stopWords.contains(token)){
                            continue;
                        }

                        

                        if (index.get(token) == null){
                        
                            if (nounChecker.isNoun(token)){

                                if (biWord.length() != 0){
                                    biWord = biWord + " " + token;
                                    index.put(biWord, new ArrayList<>());
                                    ArrayList <Integer> postingsList;
                                    postingsList = index.get(biWord);
                                    postingsList.add(currentID);
                                    biWord = "";

                                }
                                else {
                                    biWord  += token;
                                }
                                


                            }
                            index.put(token, new ArrayList<>());
                            ArrayList <Integer> postingsList;
                            postingsList = index.get(token);
                            postingsList.add(currentID);

                            }
                            else{

                                if (nounChecker.isNoun(token)){
                                    
                                    if (biWord.length() != 0){
                                        biWord = biWord + " " + token;
                                        index.put(biWord, new ArrayList<>());
                                        ArrayList <Integer> postingsList;
                                        postingsList = index.get(biWord);
                                        postingsList.add(currentID);
                                        biWord = "";
    
                                    }
                                    else {
                                        biWord  += token;
                                    }
                                    
                                    
                                }

                                ArrayList <Integer> postingsList;
                                postingsList = index.get(token);
                                if (! postingsList.contains(currentID)) {
                                    postingsList.add(currentID);  
                                }
                            }
                    
                    }
                
                }

            } 
            catch (IOException e) {
                e.printStackTrace();
            }

        }
 

    }

    public void run() {
       generateIndex(documentsToReadMap);
    }

    

    


}
