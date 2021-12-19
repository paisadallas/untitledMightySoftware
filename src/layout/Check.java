package layout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class Check extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JButton buttonSample;
    private JButton buttonRead;
    JSONObject jo = new JSONObject();


    public Check(){
        setContentPane(mainPanel);
        setTitle("Mighty Care Care Center");
        setSize(1200,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        buttonSample.addActionListener(this);
        buttonRead.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== buttonSample){
            System.out.println("Hello world");
            jo.put("firstName","John");
            try {
                PrintWriter pw = new PrintWriter("JSONExample.json");
                pw.write(jo.toJSONString());
                pw.flush();
                pw.close();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }

        //READ
        if(e.getSource()==buttonRead){
            try {
                Object obj = new JSONParser().parse(new FileReader("JSONExample.json"));
                JSONObject jo = (JSONObject) obj;
                String firstName = (String) jo.get("firstName");
                System.out.println("first Name is ="+firstName);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                System.out.println("Cathc two");
            } catch (ParseException parseException) {
                parseException.printStackTrace();
                System.out.println("catch One");
            }
        }
    }
}
