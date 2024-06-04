package GUI;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javafx.event.ActionEvent;


public class MyPanel extends JPanel {

    private JTextField searchField;
    private JButton searchButton;
    
    public MyPanel (){
        super();
        searchField = new JTextField(30);
        searchButton = new JButton("search");
        add(searchField);
        add(searchButton);

    }

    public JButton getButton (){
        return searchButton;
    }

    public JTextField getTextField () {
        return searchField;
    }
}
