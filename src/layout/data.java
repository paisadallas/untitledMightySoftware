package layout;

import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
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
    private JLabel textTotal;
    private JButton saveButton;
    private JButton printButton;
    private JPanel JPBtnAdd;
    private JPanel JPBtnDelete;
    private JPanel JPCustomer;
    private JPanel JPVehicle;
    private JLabel textTax;
    private JButton readTable;
    private DefaultTableModel tableModel;
   // private float price;

  //  private  float tax = (float) 0.0825;
    PrinterJob job = PrinterJob.getPrinterJob();


    //CONSTRUCTOR
    public data()  {

       TableOperator tableOperator = new TableOperator();
        setContentPane(mainPanel);
        setTitle("Mighty Care Care Center");
        setSize(1200,1100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        printButton.setEnabled(true);
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

                //Edit JTable
                tableModel = tableOperator.getPrice(tableProducts,tableModel,e.getFirstRow(),e.getColumn());
                tableModel = tableOperator.getTax(tableProducts, tableModel, e.getFirstRow(),e.getColumn());
                tableModel = tableOperator.getTotal(tableProducts,tableModel,e.getFirstRow(),e.getColumn());

                textTotal.setText(String.valueOf(tableOperator.getAllTotal(tableProducts)));
                textSubtotal.setText(String.valueOf(tableOperator.getAllSubtotal(tableProducts)));
                textTax.setText(String.valueOf(tableOperator.getAllTax(tableProducts)));

          }
      });

        setVisible(true);


    }



    @Override
    public void actionPerformed(ActionEvent e) {

        //Write data
         WriteData wd = new WriteData();
        if(e.getSource()== saveButton){
            printButton.setEnabled(true);

           // wd.addInfo();
          //  wd.addCustomer();
            wd.writeData();
        }

        //Read data
        if(e.getSource()==deleteButton){
          //  System.out.println("Read");
           // ReadData rd = new ReadData();
          //  rd.read();
         //   tableModel.setValueAt("valor",0,0);

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

            writeTable(wd);
            writeCustomer(wd);
            writeTotals(wd,tableProducts);

            // ADD Customer
            wd.writeData();
        }

        if(e.getSource()==printButton){
            try {
                Printer printer = new Printer("sample.json");
               // printer.noInvoce = "sample.json";
                printer.actionPerformed(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        }

        }

    private void writeTable(WriteData writeData){

        String qty,description,condition,unitPrice,quantity,price,labor,shopSuplies,tax,total;
        for (int row=0;row < tableProducts.getRowCount(); row++){
            for(int colum =0 ; colum <= 7;colum++){
                System.out.print(" "+tableProducts.getValueAt(row,colum));
                qty= (String) tableProducts.getValueAt(row,0);
                description= (String) tableProducts.getValueAt(row,1);
                condition = (String) tableProducts.getValueAt(row,2);
                unitPrice = (String) tableProducts.getValueAt(row,3);
                quantity = (String) tableProducts.getValueAt(row,4);
                price = (String) tableProducts.getValueAt(row,5);
                labor= (String) tableProducts.getValueAt(row,6);
                shopSuplies= (String) tableProducts.getValueAt(row,7);
                tax = (String) tableProducts.getValueAt(row,8);
                total = (String) tableProducts.getValueAt(row,9);
                writeData.writeTable(row,qty,description,condition,unitPrice,quantity,price,labor,shopSuplies,tax,total);
                //wd.addInfo(row);
            }
            System.out.println("");
        }
    }

    private void writeCustomer(WriteData writeData){
        String name, email,phone,address,vin,car,miles,tag;
        name = textName.getText();
        email=textEmail.getText();
        phone=textPhone.getText();
        address=textAddress.getText();
        vin=textVin.getText();
        car=textVehicle.getText();
        miles=textMiles.getText();
        tag=textTag.getText();
        writeData.addCustomer(name,email,phone,address,car,vin,miles,tag);
    }

    private void writeTotals(WriteData writeData,JTable tableProducts){
        TableOperator tableOperator = new TableOperator();
        String laborTotal="", price="", shopSuplies="",subTotal="", tax ="",total="";

        laborTotal = tableOperator.getAllLabor(tableProducts);
        price = tableOperator.getAllPrice(tableProducts);
        shopSuplies = tableOperator.getAllShopSuplies(tableProducts);
        subTotal = tableOperator.getAllSubtotal(tableProducts);
        tax = tableOperator.getAllTax(tableProducts);
        total = tableOperator.getAllTotal(tableProducts);

        writeData.addTotals(laborTotal,price,shopSuplies,subTotal,tax,total);
    }
    private  void cleanData(){



    }

    private void createUIComponents() {
        imageLogo = new JLabel(new ImageIcon("logo.jpg"));
    }
}
