package layout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TableOperator {



    public DefaultTableModel getPrice(JTable tableProducts, DefaultTableModel tableModel,int firtsRow,int column) {
        float unitPrice =0;
        float quantity =0;
        float price =0;
        for (int i = 0; i < tableProducts.getRowCount(); i++) {
            for (int j = 0; j < 9; j++) {
            //    System.out.println(tableProducts.getValueAt(i,j));
                if((tableProducts.getValueAt(i,3) != null)
                        && tableProducts.getValueAt(i,4)!=null
                        ){
                    unitPrice = Float.parseFloat(tableProducts.getValueAt(i,3).toString()) ;
                    quantity = Float.parseFloat(tableProducts.getValueAt(i,4).toString()) ;
                    price = unitPrice*quantity;

                    //Edit table model
                    if(column==3 || column ==4 && (tableProducts.getValueAt(firtsRow,4) != null)){
                        System.out.println("value quantity = " +tableProducts.getValueAt(firtsRow,4));
                        if(tableProducts.getValueAt(firtsRow,4) != null){
                            tableModel.setValueAt(String.valueOf(price),firtsRow,5);
                        }

                    }

                    System.out.println("price is: "+price);
                }
            }
        }
        return  tableModel;
    }



}
