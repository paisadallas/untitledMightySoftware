package layout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.text.DecimalFormat;

import static javax.swing.JOptionPane.showMessageDialog;

public class TableOperator {

    float price =0.0f;
    float totalNoTax =0;
    float total=0;

    public DefaultTableModel getPrice(JTable tableProducts, DefaultTableModel tableModel,int firstRow,int column) {
        float unitPrice =0;
        float quantity =0;

        for (int i = 0; i < tableProducts.getRowCount(); i++) {
            for (int j = 0; j < 9; j++) {
            //    System.out.println(tableProducts.getValueAt(i,j));
                if((tableProducts.getValueAt(i,3) != null)
                        && tableProducts.getValueAt(i,4)!=null
                        ){


                    //Edit table model
                    if(column==3 || column ==4 && (tableProducts.getValueAt(firstRow,4) != null)){
                        System.out.println("value quantity = " +tableProducts.getValueAt(firstRow,4));
                        if(tableProducts.getValueAt(firstRow,4) != null){
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
                            tableModel.setValueAt(String.valueOf(price),i,5);



                        }

                    }

                }
            }
        }
        return  tableModel;
    }


    public DefaultTableModel getTotal(JTable tableProducts, DefaultTableModel tableModel, int firstRow, int column) {
        for (int i = 0; i < tableProducts.getRowCount(); i++) {
            for (int j = 0; j < 9; j++) {
                //    System.out.println(tableProducts.getValueAt(i,j));
                if((tableProducts.getValueAt(i,3) != null)
                        && tableProducts.getValueAt(i,4)!=null
                ){


                    //Edit table model
                    if(column==3 || column ==4 || column== 6 || column==7 && (tableProducts.getValueAt(i,4) != null)){

                        if(tableProducts.getValueAt(firstRow,4) != null ){
                            try {
                              //  float tax =   Float.parseFloat((String) tableProducts.getValueAt(i,8));
                                DecimalFormat decimalFormat = new DecimalFormat("#,##");
                             //   float newTax = Float.valueOf(decimalFormat.format(tax*0.0826));

                                totalNoTax = price + Float.parseFloat(tableProducts.getValueAt(i,6).toString())+
                                        Float.parseFloat(tableProducts.getValueAt(i,7).toString());

                                float tax = totalNoTax*0.0825f;
                                total = price + Float.parseFloat(tableProducts.getValueAt(i,6).toString())+
                                        Float.parseFloat(tableProducts.getValueAt(i,7).toString())+tax;


                                tableModel.setValueAt(String.format("%.2f",tax),i,8);

                              //  tableModel.setValueAt(newTax,i,8);
                                tableModel.setValueAt(total,firstRow,9);
                            }catch (Exception e){
                                System.out.println("error in getTotal");

                                tableModel.setValueAt(0,i,9);

                            }

                            try {
                               // quantity = Float.parseFloat(tableProducts.getValueAt(i,4).toString()) ;
                            }catch (Exception e){
                                showMessageDialog(null, "Error Quantity");
                                tableModel.setValueAt(0,i,4);
                            }


                        }

                    }

                  //  System.out.println("price is: "+price);
                }
            }
        }
        return  tableModel;
    }
}
