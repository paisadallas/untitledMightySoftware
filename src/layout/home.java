package layout;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.sun.glass.ui.Cursor.setVisible;

public class home extends JFrame implements ActionListener {
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Hello");
    private JButton checkInvoceButton;
    private JButton newInvoceButton;
    private JPanel mainPanel;

    public home(){

        setContentPane(mainPanel);
        setTitle("Mighty Care Care Center");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        newInvoceButton.addActionListener(this);
        checkInvoceButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newInvoceButton){
            data dataUser = new data();

        }
        if(e.getSource() == checkInvoceButton){
            Check check = new Check();
        }
    }
}
