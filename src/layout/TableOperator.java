package layout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.text.DecimalFormat;
import java.text.Format;

import static javax.swing.JOptionPane.showMessageDialog;

public class TableOperator {
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
                if((tableProducts.getValueAt(i,3) != null)
                        && tableProducts.getValueAt(i,4)!=null
                        ){

                    //Edit table model waranty that the last cell edit wasn't
                    if(column==3 || column ==4 && (tableProducts.getValueAt(firstRow,4) != null)){


                            try {
                            unitPrice = Float.parseFloat(tableProducts.getValueAt(i,3).toString()) ;

                            }catch (Exception e){
                                System.out.println("error");
                                showMessageDialog(null, "Error Unit price");
                                tableModel.setValueAt(0,i,3);

                            }
                            try {
                                quantity = Float.parseFloat(tableProducts.getValueAt(i,4).toString()) ;
                            }catch (Exception e){
                                showMessageDialog(null, "Error Quantity");
                                tableModel.setValueAt(0,i,4);
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
        }

        return  tableModel;
    }
}
