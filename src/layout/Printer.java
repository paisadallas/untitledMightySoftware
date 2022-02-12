package layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


 public class Printer implements Printable, ActionListener  {

     String  invoce ="",folder="data";
     public String noInvoce ="";

     ImageIcon printImage = new javax.swing.ImageIcon("logo2.jpg");

    // String invoce;
     Map customer,vehicleMap,paidMap,infoMap;
     String name, email,phone, address, vehicle, vin, miles, tag;
     Double labor, parts, shopSuplies,totalLessTax,totalTax;
     int space = 20;
     int spaceX = 275;
     int spaceXtable = 100;
     public Printer(String name) throws IOException, ParseException {
         noInvoce = name;
         Object object = new JSONParser().parse(new FileReader(folder+"/"+noInvoce));
         ReadData readData = new ReadData(noInvoce);
         System.out.println("invoce is = "+ readData.getInvoce());
         System.out.println("total ="+readData.getName());
         JSONObject jo = (JSONObject) object;
         //Getting invoce
          invoce = (String) jo.get("invoce");
         System.out.println("invoce"+invoce);
         //Getting customer
         customer = ((Map) jo.get("customer"));
         //iterating customer map
         Iterator<Map.Entry> itr1 = customer.entrySet().iterator();
         while (itr1.hasNext()){
             Map.Entry pair = itr1.next();
             System.out.println(pair.getKey()+ " : "+ pair.getValue());
         vehicleMap = ((Map) jo.get("vehicle"));
         paidMap = ((Map) jo.get("information0"));

         }
        //CUSTOMER DATA
         name = (String) customer.get("name");
         email = (String) customer.get("email");
         phone = (String) customer.get("phone");
         address = (String) customer.get("address");
         System.out.println("name = " +name);

         //VEHICLE DATA
         vehicle = (String) vehicleMap.get("vehicle");
         vin = (String) vehicleMap.get("VIN");
         miles = (String) vehicleMap.get("miles");
         tag = (String) vehicleMap.get("tag");

         //PAID DATA

       // shopSuplies = (Double) paidMap.get("shopSuplies");
    //    totalLessTax = (Double) paidMap.get("total less tax");
    //    totalTax = (Double) paidMap.get("total tax");
     }






     @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

            ReadData readData = new ReadData(noInvoce);
            if (pageIndex>0){
                return NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D)graphics;
            g2d.translate(pageFormat.getImageableX(),pageFormat.getImageableY());

         graphics.drawImage(printImage.getImage(),230,20,150,40,null);
         graphics.drawString("1817 South Josey Lane",250,70);
         graphics.drawString("Carrollton, TX 75006",260,70+space);
         graphics.drawString("(972) 820 88 55",268,70+(space*2));
           // graphics.drawString("Total $"+String.valueOf(50000),100,200);
         graphics.drawString("Invoce #"+invoce,100,(space * 7));  //140Y
         graphics.drawString("_________________________________________________________________",100,(space*8));
            //CUSTOMER DATA
           graphics.drawString("name:    "+readData.getName(),100,(space*9));
           graphics.drawString("email:   "+readData.getEmail(),100,(space*10));
           graphics.drawString("phone:   "+readData.getPhone(),100,(space*11));
           graphics.drawString("Address: "+readData.getAddres(),100,(space*12));
            //VEHICLE DATA
         graphics.drawString("vehicle:    "+readData.getVehicle(),100+spaceX,(space*9));
         graphics.drawString("VIN:   "+readData.getVin(),100+spaceX,(space*10));
         graphics.drawString("miles:   "+readData.getMiles(),100+spaceX,(space*11));
         graphics.drawString("TAG: "+readData.getTag(),100+spaceX,(space*12));
         graphics.drawString("_________________________________________________________________",100,(space*13));
         //INFORMATION LABEL
         graphics.drawString("QTY Code",100,(space*14));
         graphics.drawString("Description",100+(spaceXtable),(space*14));
         graphics.drawString("Condition",100+(spaceXtable*2),(space*14));
         graphics.drawString("Unit Price",100+(spaceXtable*3),(space*14));
         graphics.drawString("Price",100+(spaceXtable*4),(space*14));


         //PAID DATA
         graphics.drawString("Labor:",375,250+(space*17));
         graphics.drawString("$"+readData.getLabor(),500,250+(space*17));

         graphics.drawString("parts:",375,250+(space*18));
         graphics.drawString("$"+readData.getParts(),500,250+(space*18));

         graphics.drawString("Shop Suplies:",375,250+(space*19));
         graphics.drawString("$"+readData.getShopSuplies(),500,250+(space*19));

         graphics.drawString("__________________________",375,250+(space*20));

         graphics.drawString("Subtotal:",375,250+(space*21));
         graphics.drawString("$"+readData.getSubTotal(),500,250+(space*21));

         graphics.drawString("tax:",375,250+(space*23));
         graphics.drawString("$"+readData.getTax(),500,250+(space*23));

         graphics.drawString("Total:",375,250+(space*25));
         graphics.drawString("$"+readData.getTotal(),500,250+(space*25));
            return PAGE_EXISTS;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);
            boolean ok = job.printDialog();
            if (ok) {
                try {
                    job.print();
                } catch (PrinterException ex) {
                    /* The job did not successfully complete */
                }
            }
        }


    }

