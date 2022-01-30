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

     ImageIcon printImage = new javax.swing.ImageIcon("logo2.jpg");
     Object object = new JSONParser().parse(new FileReader("data/sample.json"));
     String invoce;
     Map customer,vehicleMap,paidMap,infoMap;
     String name, email,phone, address, vehicle, vin, miles, tag;
     Double labor, parts, shopSuplies,totalLessTax,totalTax;
     int space = 20;
     int spaceX = 275;
     int spaceXtable = 100;
     public Printer() throws IOException, ParseException {
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
         paidMap = ((Map) jo.get("paid"));

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
        labor = (Double) paidMap.get("labor");
        parts = (Double) paidMap.get("parts");
        shopSuplies = (Double) paidMap.get("shopSuplies");
        totalLessTax = (Double) paidMap.get("total less tax");
        totalTax = (Double) paidMap.get("total tax");
     }






     @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {


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
           graphics.drawString("Invoce #"+invoce,100,200);
           graphics.drawString("_____________________________________________________________",100,225);
            //CUSTOMER DATA
           graphics.drawString("name:    "+name,100,250);
           graphics.drawString("email:   "+email,100,250+(space));
           graphics.drawString("phone:   "+phone,100,250+(space*2));
           graphics.drawString("Address: "+address,100,250+(space*3));
            //VEHICLE DATA
         graphics.drawString("vehicle:    "+vehicle,100+spaceX,250);
         graphics.drawString("VIN:   "+vin,100+spaceX,250+(space));
         graphics.drawString("miles:   "+miles,100+spaceX,250+(space*2));
         graphics.drawString("TAG: "+tag,100+spaceX,250+(space*3));
         graphics.drawString("_____________________________________________________________",100,250+(space*4));
         //INFORMATION LABEL
         graphics.drawString("QTY Code",100,250+(space*6));
         graphics.drawString("Description",100+(spaceXtable),250+(space*6));
         graphics.drawString("Condition",100+(spaceXtable*2),250+(space*6));
         graphics.drawString("Unit Price",100+(spaceXtable*3),250+(space*6));
         graphics.drawString("Price",100+(spaceXtable*4),250+(space*6));


         //PAID DATA
         graphics.drawString("Labor:",375,250+(space*19));
         graphics.drawString("$"+labor,500,250+(space*19));
         graphics.drawString("parts:",375,250+(space*20));
         graphics.drawString("$"+parts,500,250+(space*20));
         graphics.drawString("Shop Suplies:",375,250+(space*21));
         graphics.drawString("$"+shopSuplies,500,250+(space*21));
         graphics.drawString("Subtotal:",375,250+(space*22));
         graphics.drawString("$"+totalLessTax,500,250+(space*22));
         graphics.drawString("Total:",375,250+(space*24));
         graphics.drawString("$"+totalTax,500,250+(space*24));
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

