package layout;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class ReadData {
    Object obj;

    String invoce ="";

    {
        try {
            obj = new JSONParser().parse(new FileReader("data/sample.json"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    ReadData(String noInvoce){
        invoce = noInvoce;
    }

    //CUSTOMER
    public String getInvoce(){
        JSONObject jo = (JSONObject) obj;
        String numberInvoce = (String) jo.get("invoce");
        System.out.println("invoce actual = "+numberInvoce);
        return numberInvoce;
    }

    public String getName(){
        JSONObject jo = (JSONObject) obj;
        Map<String,String> customer = (Map<String, String>) jo.get("customer");
        return customer.get("name");
    }

    public String getEmail(){
        JSONObject jo = (JSONObject) obj;
        Map<String,String> customer = (Map<String, String>) jo.get("customer");
        return customer.get("email");
    }

    public String getPhone(){
        JSONObject jo = (JSONObject) obj;
        Map<String,String> customer = (Map<String, String>) jo.get("customer");
        return customer.get("phone");
    }

    public String getAddres(){
        JSONObject jo = (JSONObject) obj;
        Map<String,String> customer = (Map<String, String>) jo.get("customer");
        return customer.get("address");
    }

    //VEHICLE
    public String getVehicle(){
        JSONObject jo = (JSONObject) obj;
        Map<String,String> vehicle = (Map<String, String>) jo.get("vehicle");
        return vehicle.get("vehicle");
    }

    public String getVin(){
        JSONObject jo = (JSONObject) obj;
        Map<String,String> vehicle = (Map<String, String>) jo.get("vehicle");
        return vehicle.get("VIN");
    }

    public String getMiles(){
        JSONObject jo = (JSONObject) obj;
        Map<String,String> vehicle = (Map<String, String>) jo.get("vehicle");
        return vehicle.get("miles");
    }

    public String getTag(){
        JSONObject jo = (JSONObject) obj;
        Map<String,String> vehicle = (Map<String, String>) jo.get("vehicle");
        return vehicle.get("tag");
    }

    //TOTAL
    public String getLabor(){
        JSONObject jo = (JSONObject) obj;
        Map<String,String> total = (Map<String, String>) jo.get("totals");
        return total.get("labor");
    }

    public String getParts(){
        JSONObject jo = (JSONObject) obj;
        Map<String,String> total = (Map<String, String>) jo.get("totals");
        return total.get("parts");
    }

    public String getShopSuplies(){
        JSONObject jo = (JSONObject) obj;
        Map<String,String> total = (Map<String, String>) jo.get("totals");
        return total.get("shopSuplies");
    }

    public String getSubTotal(){
        JSONObject jo = (JSONObject) obj;
        Map<String,String> total = (Map<String, String>) jo.get("totals");
        return total.get("subTotal");
    }

    public String getTax(){
        JSONObject jo = (JSONObject) obj;
        Map<String,String> total = (Map<String, String>) jo.get("totals");
        return total.get("tax");
    }

    public String getTotal(){
        JSONObject jo = (JSONObject) obj;
        Map<String,String> total = (Map<String, String>) jo.get("totals");
        return total.get("total");
    }

}
