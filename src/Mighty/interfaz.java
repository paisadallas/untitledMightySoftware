package Mighty;

import layout.home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class interfaz extends JFrame implements ActionListener {
    private JButton button1;
    private JPanel mainPanel;
    private JPasswordField passwordField1;
    private JFormattedTextField formattedTextField1;
    private JLabel jlabelTitle;

    public interfaz() {


        setContentPane(mainPanel);
        setTitle("Mighty Care Care Center");
        setSize(400,200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        button1.addActionListener(this);

    }

    public static void main(String[] args) {
        interfaz myWindows = new interfaz();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == button1){
            home open = new home();
            System.out.println("click");
            setVisible(false);
        }


    }



}

