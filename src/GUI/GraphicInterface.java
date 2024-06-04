package GUI;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import javafx.event.ActionEvent;


public class GraphicInterface {
    
    private JFrame mainFrame;
    private MyPanel mainPanel;


    public GraphicInterface (){

        // frame settings 
        mainFrame =  new JFrame("Search Engine");
        mainFrame.setSize(700,100);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // panel settings
        mainPanel = new MyPanel();
        mainFrame.add(mainPanel);

        //set button action

        JButton searchButton = mainPanel.getButton();
        JTextField searchField = mainPanel.getTextField();
        searchButton.addActionListener(new ActionListener (){

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                String term = searchField.getText();
                if (!term.isEmpty()){
                    // to complete 
                }

                
                
            }
            
        } );


        // frame to visible
        mainFrame.setVisible(true);



    }

    public static void main(String[] args) {
        GraphicInterface test =  new GraphicInterface();
    }
    
}
