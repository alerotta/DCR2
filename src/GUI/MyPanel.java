package GUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.color.*;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;





public class MyPanel extends JPanel {

    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;
    
    public MyPanel (){
        super();

        setLayout(new BorderLayout());
        searchField = new JTextField(30);
        searchButton = new JButton("search");
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        JPanel container =  new JPanel();
        container.add(searchField);
        container.add(searchButton);
        add(container, BorderLayout.NORTH);

        LineBorder border1 = new LineBorder(new Color(238,238,238), 15);
        LineBorder border2 = new LineBorder(new Color(0,0,0), 1);
        CompoundBorder finalBorder = new CompoundBorder(border1,border2);


        scrollPane.setBorder(finalBorder);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JButton getButton (){
        return searchButton;
    }

    public JTextField getTextField () {
        return searchField;
    }

    public JTextArea getTextArea (){
        return resultArea;
    }
}
