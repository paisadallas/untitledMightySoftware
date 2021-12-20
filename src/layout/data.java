package layout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private JFormattedTextField textCode;
    private JFormattedTextField textDescription;
    private JFormattedTextField textCondition;
    private JTextField textUnitPrice;
    private JFormattedTextField textPrice;
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

    class HelloWorldPrinter implements Printable, ActionListener {
        ImageIcon printImage = new javax.swing.ImageIcon("logo.jpg");
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            if (pageIndex>0){
                return NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D)graphics;
            g2d.translate(pageFormat.getImageableX(),pageFormat.getImageableY());

            graphics.drawImage(printImage.getImage(),110,50,null);
            graphics.drawString("Total $"+String.valueOf(total),100,200);
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

    //CONSTRUCTOR
    public data(){


        setContentPane(mainPanel);
        setTitle("Mighty Care Care Center");
        setSize(1200,1100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        printButton.setEnabled(false);
        addButton.addActionListener(this);
        printButton.addActionListener(new Printer());
        saveButton.addActionListener(this);
        deleteButton.addActionListener(this);
        readTable.addActionListener(this);
        String[]  columnNames = {"Qty Code/ Tech","Description","Condition","Unit price","price","Labor","Parts","Shop suplies"};
        Object [] [] data = {
        };
        tableModel = new DefaultTableModel(data,columnNames);
        tableProducts.setModel(tableModel);

        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        //Write data
         WriteData wd = new WriteData();
        if(e.getSource()== saveButton){
            printButton.setEnabled(true);
            wd.name="PEPE";
            wd.email="carlitos@gmail.com";
            wd.phone="311545538";
            wd.address="calle 85#225";
            wd.vin="corolla 2000";
            wd.car="camaro";
            wd.miles="598645";
            wd.tag="55VDS";
           // wd.addInfo();
            wd.addCustomer();
            wd.writeData();
        }

        //Read data
        if(e.getSource()==deleteButton){
//            System.out.println("Read");
//            ReadData rd = new ReadData();
//            rd.read();

        }

        if(e.getSource()==addButton){

            String[]  columnNames = {"Qty Code/ Tech","Description","Condition","Unit price","price","Labor","Parts","Shop suplies"};
            Object [] [] data = {
            };
            int rowsTable = tableProducts.getRowCount();

            tableModel.insertRow(rowsTable,
                    new Object[]{
                            textCode.getText(),
                            textDescription.getText(),
                            textCondition.getText(),
                            textUnitPrice.getText(),
                            textPrice.getText(),
                            textLabor.getText(),
                            textParts.getText(),
                            textShopSuplies.getText()
                    });

            addData(textPrice.getText(),textLabor.getText(),textParts.getText(),textShopSuplies.getText());
            cleanData();

            Object [] row = new Object[4];
            System.out.println(rowsTable);
        }

        if(e.getSource()==readTable){
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

        }


    private void addData(String textPriceText, String textLaborText, String textPartsText, String textShopSuplies) {
        price = price + Float.parseFloat(textPriceText) + Float.parseFloat(textLaborText) + Float.parseFloat(textPartsText) + Float.parseFloat(textShopSuplies);
         total = price + (float) (price * tax);
        textSubtotal.setText(String.valueOf(price));
        textTotalTax.setText(String.valueOf(total));
    }

    private  void cleanData(){
        textCode.setText("");
        textDescription.setText("");
        textCondition.setText("");
        textUnitPrice.setText("");
        textPrice.setText("");
        textLabor.setText("");
        textParts.setText("");
        textShopSuplies.setText("");

    }

    private void createUIComponents() {
        imageLogo = new JLabel(new ImageIcon("logo.jpg"));
    }
}
