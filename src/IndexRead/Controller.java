package IndexRead;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import GUI.MyPanel;




public class Controller {
    
    private JFrame mainFrame;
    private MyPanel mainPanel;
    private Searcher searcher;
    private HashMap <Integer,String> documents;


    public Controller (){

        searcher = new Searcher();
        documents = searcher.getDocuments();

        // frame settings 
        mainFrame =  new JFrame("Search Engine");
        mainFrame.setSize(700,400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // panel settings
        mainPanel = new MyPanel();
        mainFrame.add(mainPanel);

        //set button action

        JButton searchButton = mainPanel.getButton();
        JTextField searchField = mainPanel.getTextField();
        JTextArea resultArea = mainPanel.getTextArea();
        searchButton.addActionListener(new ActionListener (){

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                String term = searchField.getText();
                if (!term.isEmpty()){
                    term.toLowerCase();
                    ArrayList <Integer> postingList = searcher.search(term);
                    if (postingList != null) {
                        // System.out.println(postingList);
                        searchField.setText("");
                        String documentListString =  new String();
                        for (Integer docID : postingList) {
                            String docName;
                            docName = documents.get(docID);
                            documentListString += docName ;
                            documentListString += "\n" ;

                        }
                        resultArea.setText(documentListString);
                    }
                    else{
                        //System.out.println("no match found");
                        searchField.setText("");
                        resultArea.setText("no match found");
                    }
                   
                }
                else {
                    resultArea.setText("");
                }


                
                
            }
            
        } );




        // frame to visible
        mainFrame.setVisible(true);



    }

    public static void main(String[] args) {
        Controller test =  new Controller();
    }
    
}
