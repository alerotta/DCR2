import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


public class Reader extends Thread {
    
    private HashMap <Integer,String>  documentsToReadMap;
    private int startID;
    private ConcurrentHashMap <String, ArrayList <Integer>> index;

    public Reader (HashMap <Integer,String>  documentsToReadMap, int startID,ConcurrentHashMap <String, ArrayList <Integer>> index){
        super();
        this.documentsToReadMap = documentsToReadMap;
        this.startID = startID;
        this.index = index;


    }

    public void generateIndex (HashMap <Integer,String>  documentsToReadMap){


        for (int i = startID; i < documentsToReadMap.size(); i++){

            int currentID = i;
            String filename = documentsToReadMap.get(currentID);
            
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
                while ((line = br.readLine()) != null) {

                    String[] tokens = line.split("\\s+");
                    for (String token : tokens) {

                        if (index.get(token) == null){
                        index.put(token, new ArrayList<>());
                        }
                        else{
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
