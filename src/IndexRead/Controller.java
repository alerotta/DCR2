package IndexRead;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import GUI.MyPanel;




public class Controller {
    
    private JFrame mainFrame;
    private MyPanel mainPanel;
    private Searcher searcher;


    public Controller (){

        searcher = new Searcher();

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
                    ArrayList <Integer> postingList = searcher.search(term);
                    if (postingList != null) {
                        // System.out.println(postingList);
                        searchField.setText("");
                        resultArea.setText(postingList.toString());
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
