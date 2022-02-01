package layout;

import javafx.scene.control.TableCell;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;

public class data extends JFrame implements ActionListener {
    private JTextField textName;
    private JTextField textEmail;
    private JTextField textPhone;
    private JTextField textAddress;
    private JTextField textVehicle;
    private JTextField textVin;
    private JTextField textMiles;
    private JTextField textTag;
    private JPanel mainPanel;
    private JTable tableProducts;
    private JButton addButton;

    private JButton deleteButton;
    private JLabel imageLogo;
    private JFormattedTextField textLabor;
    private JFormattedTextField textParts;
    private JFormattedTextField textShopSuplies;
    private JLabel textSubtotal;
    private JLabel textTotalTax;
    private JButton saveButton;
    private JButton printButton;
    private JPanel JPBtnAdd;
    private JPanel JPBtnDelete;
    private JPanel JPCustomer;
    private JPanel JPVehicle;
    private JButton readTable;
    private DefaultTableModel tableModel;
    private float price;
    private float total;
    private  float tax = (float) 0.0825;
    PrinterJob job = PrinterJob.getPrinterJob();


    //CONSTRUCTOR
    public data()  {

       TableOperator tableOperator = new TableOperator();
        setContentPane(mainPanel);
        setTitle("Mighty Care Care Center");
        setSize(1200,1100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        printButton.setEnabled(false);
        addButton.addActionListener(this);
      //  printButton.addActionListener(new Printer());
        printButton.addActionListener(this);
        saveButton.addActionListener(this);
        deleteButton.addActionListener(this);
//        readTable.addActionListener(this);
        String[]  columnNames = {"Qty Code/ Tech","Description","Condition","Unit price","Quantity","price","Labor","Shop suplies","tax","total"};
        Object [] [] data = {
        };
        tableModel = new DefaultTableModel(data,columnNames);
        tableProducts.setModel(tableModel);
        tableModel.insertRow(0,
                new Object[]{

                });

        //Detect any change



        tableModel.addTableModelListener(new TableModelListener() {


            @Override
          public void tableChanged(TableModelEvent e) {
              System.out.println("any change!");

                // tableModel =   tableOperator.getPrice(tableProducts,tableModel);
                System.out.println(e.getColumn());
                System.out.println(e.getFirstRow());

                tableModel = tableOperator.getPrice(tableProducts,tableModel,e.getFirstRow(),e.getColumn());
                tableModel = tableOperator.getTax(tableProducts, tableModel, e.getFirstRow(),e.getColumn());
                tableModel = tableOperator.getTotal(tableProducts,tableModel,e.getFirstRow(),e.getColumn());
          }
      });
/*
        tableProducts.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                System.out.println("press any key!");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                System.out.println("pressed Enter!");
            }
        });
*/





  //tableModel.setValueAt("hola",0,0);





        //detected v2

        setVisible(true);


    }



    @Override
    public void actionPerformed(ActionEvent e) {

        //Write data
         WriteData wd = new WriteData();
        if(e.getSource()== saveButton){
            printButton.setEnabled(true);

           // wd.addInfo();
            wd.addCustomer();
            wd.writeData();
        }

        //Read data
        if(e.getSource()==deleteButton){
//            System.out.println("Read");
//            ReadData rd = new ReadData();
//            rd.read();

            tableModel.setValueAt("valor",0,0);

        }

        if(e.getSource()==addButton){

            String[]  columnNames = {"Qty Code/ Tech","Description","Condition","Unit price","price","Labor","Parts","Shop suplies"};
            Object [] [] data = {
            };
            int rowsTable = tableProducts.getRowCount();

            tableModel.insertRow(rowsTable,
                    new Object[]{
                    });


            cleanData();

            Object [] row = new Object[4];
            System.out.println(rowsTable);
        }

        if(e.getSource()==saveButton){
            System.out.println("readTABEL");
            //Write table
            for (int row=0;row < tableProducts.getRowCount(); row++){
                for(int colum =0 ; colum <= 7;colum++){
                System.out.print(" "+tableProducts.getValueAt(row,colum));
                wd.qty= (String) tableProducts.getValueAt(row,0);
                wd.condition= (String) tableProducts.getValueAt(row,1);
                wd.price = (String) tableProducts.getValueAt(row,2);
                wd.parts = (String) tableProducts.getValueAt(row,3);
                wd.description = (String) tableProducts.getValueAt(row,4);
                wd.uniPrice = (String) tableProducts.getValueAt(row,5);
                wd.labor= (String) tableProducts.getValueAt(row,6);
                wd.shopSuplies= (String) tableProducts.getValueAt(row,7);
                wd.addInfo(row);
                }
                System.out.println("");
            }
            //Write paid

            for (int row=0; row < tableProducts.getRowCount(); row++){
                wd.totalPrice = wd.totalPrice + Float.parseFloat((String) tableProducts.getValueAt(row,6));
                wd.totalLabor = wd.totalLabor + Float.parseFloat((String) tableProducts.getValueAt(row,5));
                wd.totalShopSuplies = wd.totalShopSuplies + Float.parseFloat((String) tableProducts.getValueAt(row,7));
                wd.totalParts = wd.totalParts + Float.parseFloat((String) tableProducts.getValueAt(row,4));
            }
            System.out.println("total Price = "+wd.totalPrice);
            System.out.println("total labor = "+wd.totalLabor);
            System.out.println("total shopSuplies = "+wd.totalShopSuplies);
            wd.totalWithoutTax = wd.totalPrice+wd.totalLabor+wd.totalShopSuplies+wd.totalParts;
            wd.totalWithTax = wd.totalWithoutTax + (wd.totalWithoutTax * tax);
            System.out.println("total without tax = "+wd.totalWithoutTax);
            System.out.println("total with tax = "+wd.totalWithTax);
            wd.addPaid();


            // ADD Customer
            wd.name = textName.getText();
            wd.email=textEmail.getText();
            wd.phone=textPhone.getText();
            wd.address=textAddress.getText();
            wd.vin=textVin.getText();
            wd.car=textVehicle.getText();
            wd.miles=textMiles.getText();
            wd.tag=textTag.getText();
            wd.addCustomer();
            wd.writeData();
        }

        if(e.getSource()==printButton){
            try {
                Printer printer = new Printer();
                printer.actionPerformed(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        }

        }




    private void addData(String textPriceText, String textLaborText, String textPartsText, String textShopSuplies) {
        price = price + Float.parseFloat(textPriceText) + Float.parseFloat(textLaborText) + Float.parseFloat(textPartsText) + Float.parseFloat(textShopSuplies);
         total = price + (float) (price * tax);
        textSubtotal.setText(String.valueOf(price));
        textTotalTax.setText(String.valueOf(total));
    }

    private  void cleanData(){



    }

    private void createUIComponents() {
        imageLogo = new JLabel(new ImageIcon("logo.jpg"));
    }
}
