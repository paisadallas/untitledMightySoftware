package layout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.text.DecimalFormat;
import java.text.Format;

import static javax.swing.JOptionPane.showMessageDialog;

public class TableOperator {
    int columnUnitPrice = 3;
    int columnQuantity = 4;
    int columnPrice = 5;
    int columnLabor = 6;
    int columnShop = 7;
    int columnTax = 8;
    int columnTotal =9;



    public DefaultTableModel getPrice(JTable tableProducts, DefaultTableModel tableModel,int firstRow,int column) {
        float unitPrice =0;
        float quantity =0;
        float price =0.0f;
        for (int i = 0; i < tableProducts.getRowCount(); i++) {
            for (int j = 0; j < 9; j++) {
            //    Cells no empty
                if((tableProducts.getValueAt(i,columnUnitPrice) != null)
                        && tableProducts.getValueAt(i,4)!=null
                        ){

                    //Edit table model waranty that the last cell edit wasn't
                    if(column==3 || column ==4 && (tableProducts.getValueAt(firstRow,columnQuantity) != null)){


                            try {
                            unitPrice = Float.parseFloat(tableProducts.getValueAt(i,columnUnitPrice).toString()) ;

                            }catch (Exception e){
                                System.out.println("error");
                                showMessageDialog(null, "Error Unit price");
                                tableModel.setValueAt(0,i,columnUnitPrice);

                            }
                            try {
                                quantity = Float.parseFloat(tableProducts.getValueAt(i,columnQuantity).toString()) ;
                            }catch (Exception e){
                                showMessageDialog(null, "Error Quantity");
                                tableModel.setValueAt(0,i,columnQuantity);
                            }
                            //Edit price
                            price = unitPrice*quantity;
                            tableModel.setValueAt(String.valueOf(price),i,columnPrice);


                    }

                }
            }
        }
        return  tableModel;
    }


    public DefaultTableModel getTotal(JTable tableProducts, DefaultTableModel tableModel, int firstRow, int column) {
        float price = 0.0f;

        for (int i = 0; i < tableProducts.getRowCount(); i++) {

              if(tableProducts.getValueAt(i,columnPrice) != null)
                  price = Float.parseFloat(tableProducts.getValueAt(i,columnPrice).toString());
              if(tableProducts.getValueAt(i,columnLabor) != null)
                  price = price+ Float.parseFloat(tableProducts.getValueAt(i,columnLabor).toString());
              if(tableProducts.getValueAt(i,columnShop) != null)
                  price = price+ Float.parseFloat(tableProducts.getValueAt(i,columnShop).toString());
                if(tableProducts.getValueAt(i,columnTax) != null)
                    price = price+ Float.parseFloat(tableProducts.getValueAt(i,columnTax).toString());
                System.out.println("Total in column "+i+" is "+price);
                if(column == columnPrice || column == columnLabor || column == columnShop || column==columnTax)
                 tableModel.setValueAt(String.valueOf(price),i,columnTotal);
                price = 0.0f;
        }

        return  tableModel;
    }

    public DefaultTableModel getTax( JTable tableProducts, DefaultTableModel tableModel, int firstRow, int column) {
        float tax =0.0f;

        float price =0.0f;
        for (int i = 0; i < tableProducts.getRowCount(); i++) {
            if(tableProducts.getValueAt(i,columnPrice) != null)
                price = Float.parseFloat(tableProducts.getValueAt(i,columnPrice).toString());
            if(tableProducts.getValueAt(i,columnLabor) != null)
                price = price+ Float.parseFloat(tableProducts.getValueAt(i,columnLabor).toString());
            if(tableProducts.getValueAt(i,columnShop) != null)
                price = price+ Float.parseFloat(tableProducts.getValueAt(i,columnShop).toString());
            tax = price*0.0825f;

            if(column == columnPrice || column == columnLabor || column == columnShop )
                tableModel.setValueAt(String.format("%.2f",tax),i,columnTax);
            price = 0.0f;
        }

        return  tableModel;
    }

    public String getAllTotal(JTable tableProducts){
        float totalFloat= 0.0f;

        totalFloat = getAll(tableProducts,columnTotal);

        return String.valueOf(String.format("%.2f",totalFloat));
    }

    public String getAllSubtotal(JTable tableProducts){
        float total = 0.0f;
        float tax = 0.0f;
        float subTotal = 0.0f;

        for (int i = 0; i < tableProducts.getRowCount(); i++) {
            if(tableProducts.getValueAt(i,columnTotal) != null)
                total = total + Float.parseFloat(tableProducts.getValueAt(i,columnTotal).toString());
            if(tableProducts.getValueAt(i,columnTax) != null)
                tax = tax + Float.parseFloat(tableProducts.getValueAt(i,columnTax).toString());
        }
        subTotal = total - tax;
        return String.valueOf(String.format("%.2f",subTotal));
    }

    public String getAllTax(JTable tableProducts) {
        float totalTax= 0.0f;

        totalTax = getAll(tableProducts,columnTax);

        return String.valueOf(String.format("%.2f",totalTax));
    }

    public String getAllLabor(JTable tableProducts){
        float labor = 0.0f;

        labor = getAll(tableProducts,columnLabor);

        return  String.valueOf(String.format("%.2f",labor));
    }

    public String getAllPrice(JTable tableProducts){
        float parts = 0.0f;

        parts = getAll(tableProducts,columnPrice);

        return  String.valueOf(String.format("%.2f",parts));
    }

    public String getAllShopSuplies(JTable tableProducts){
        float shopSuplies = 0.0f;

        shopSuplies = getAll(tableProducts,columnShop);

        return  String.valueOf(String.format("%.2f",shopSuplies));
    }

    private float getAll(JTable table, int column){
        float result = 0.0f;

        for (int i = 0; i < table.getRowCount(); i++) {
            if(table.getValueAt(i,column) != null)
                result = result + Float.parseFloat(table.getValueAt(i,column).toString());
        }

        return result;
    }
}
