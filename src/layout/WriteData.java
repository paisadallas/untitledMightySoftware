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


    JSONObject jo = new JSONObject();
    Map mapCustomer = new LinkedHashMap(4);
    Map mapVehicle = new LinkedHashMap(4);
    Map mapInfo = new LinkedHashMap(8);
    Map mapTotals = new LinkedHashMap(5);
    //Customer
    public  String name,email,phone,address;
    //Vehicle
    public String car, vin, miles,tag;
    //Information
   // String qty,condition,price,parts,description,uniPrice,labor,shopSuplies;
    //Total
    float totalParts,totalLabor,totalPrice,totalShopSuplies,totalWithoutTax,totalWithTax;

    public void writeData() {

        jo.put("invoce","0001");
        write();

    }

    public void  writeTable(       int position,String qty,String description,String condition,String unitPrice,
                                   String quantity,
                                   String price,
                                   String labor,
                                   String shopSuplies,
                                   String tax,
                                   String total){

       // mapInfo = new LinkedHashMap(8);
        mapInfo.put("qty",qty);
        mapInfo.put("condition",description);
        mapInfo.put("price",condition);
        mapInfo.put("parts",unitPrice);
        mapInfo.put("description",quantity);
        mapInfo.put("unitPrice",price);
        mapInfo.put("labor",labor);
        mapInfo.put("shopSuplies",shopSuplies);
        mapInfo.put("shopSuplies",tax);
        mapInfo.put("shopSuplies",total);

        jo.put("information"+position,mapInfo);

    }



    public void addCustomer(String name,String email,String phone,String address,String car,String vin,String miles,String tag){

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

    public void addTotals(String labor,String parts,String shopSuplies,String subTotal,String tax,String total){
        mapTotals.put("labor",labor);
        mapTotals.put("parts",parts);
        mapTotals.put("shopSuplies",shopSuplies);
        mapTotals.put("subTotal",subTotal);
        mapTotals.put("tax",tax);
        mapTotals.put("total",total);
        jo.put("totals",mapTotals);

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
