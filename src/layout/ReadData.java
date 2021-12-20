package layout;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class ReadData {
    Object obj;

    public  void read(){

        {
            try {
                obj = new JSONParser().parse(new FileReader("data/invoce.json"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        JSONObject jo = (JSONObject) obj;
        String numberInvoce = (String) jo.get("number");
        System.out.println("invoce actual = "+numberInvoce);
    }


}
