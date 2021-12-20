package layout;
import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import sun.security.x509.OCSPNoCheckExtension;
import org.json.simple.JSONArray;
import java.io.BufferedWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

public class WriteData {

    JSONObject customer = new JSONObject();
    JSONObject vehicle = new JSONObject();
    JSONObject details = new JSONObject();
    JSONObject total = new JSONObject();
    JSONObject jo = new JSONObject();
    Map mapCustomer = new LinkedHashMap(4);
    Map mapVehicle = new LinkedHashMap(4);
    Map mapInfo = new LinkedHashMap(8);
    Map mapPaid = new LinkedHashMap(5);
    //Customer
    public  String name,email,phone,address;
    //Vehicle
    public String car, vin, miles,tag;
    //Information
    String qty,condition,price,parts,description,uniPrice,labor,shopSuplies;
    //Total
    float totalParts,totalLabor,totalPrice,totalShopSuplies,totalWithoutTax,totalWithTax;

    public void writeData() {

        jo.put("invoce","0001");
        write();

    }

    public void addInfo(int info){

            mapInfo = new LinkedHashMap(8);
            mapInfo.put("qty",qty);
            mapInfo.put("condition",condition);
            mapInfo.put("price",price);
            mapInfo.put("parts",parts);
            mapInfo.put("description",description);
            mapInfo.put("unitPrice",uniPrice);
            mapInfo.put("labor",labor);
            mapInfo.put("shopSuplies",shopSuplies);

            jo.put("information"+info,mapInfo);
    }

    public void addCustomer(){

        mapCustomer.put("name",name);
        mapCustomer.put("email",email);
        mapCustomer.put("phone",phone);
        mapCustomer.put("address",address);
        jo.put("customer",mapCustomer);

        mapVehicle.put("vehicle",car);
        mapVehicle.put("VIN",vin);
        mapVehicle.put("miles",miles);
        mapVehicle.put("tag",tag);

        jo.put("vehicle",mapVehicle);

    }

    public void addPaid(){
        mapPaid.put("labor",totalLabor);
        mapPaid.put("parts",totalParts);
        mapPaid.put("shopSuplies",totalShopSuplies);
        mapPaid.put("total less tax",totalWithoutTax);
        mapPaid.put("price",totalPrice);
        mapPaid.put("total tax",totalWithTax);
        jo.put("paid",mapPaid);

    }


    private void write(){
        try {

            //  PrintWriter pw = new PrintWriter(new FileOutputStream( new File("JSONExample.json"),true));
            PrintWriter pw = new PrintWriter("data/sample.json");
            pw.write(jo.toJSONString());
            pw.println();
            // pw.append(jo.toJSONString());
            // pw.append(",");
            // pw.flush();
            pw.close();

        } catch (FileNotFoundException  e) {

        }
    }



}
