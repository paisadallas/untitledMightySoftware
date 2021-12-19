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
    private JButton button1;
    private JLabel imageLogo;
    private JFormattedTextField textLabor;
    private JFormattedTextField textParts;
    private JFormattedTextField textShopSuplies;
    private JLabel textSubtotal;
    private JLabel textTotalTax;
    private JButton saveButton;
    private JButton printButton;
    private DefaultTableModel tableModel;
    private float price;
    private float total;
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
            graphics.drawString("Total $"+String.valueOf(total),100,2200);

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

    public data(){


        setContentPane(mainPanel);
        setTitle("Mighty Care Care Center");
        setSize(1200,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addButton.addActionListener(this);
        printButton.addActionListener(this);
        String[]  columnNames = {"Qty Code/ Tech","Description","Condition","Unit price","price","Labor","Parts","Shop suplies"};
        Object [] [] data = {
        };
        tableModel = new DefaultTableModel(data,columnNames);
        tableProducts.setModel(tableModel);

        setVisible(true);





    }




    @Override
    public void actionPerformed(ActionEvent e) {



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

        if(e.getSource()== printButton){
            System.out.println("Hola mundo");

            UIManager.put("swing.boldMetal", Boolean.FALSE);
            JFrame f = new JFrame("Hello World Printer");
            f.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {System.exit(0);}
            });
            JButton printButton = new JButton("Print Hello World");
            printButton.addActionListener(new HelloWorldPrinter());
            f.add("Center", printButton);
            f.pack();
            f.setVisible(true);

            }
        }


    private void addData(String textPriceText, String textLaborText, String textPartsText, String textShopSuplies) {
        price = price + Float.parseFloat(textPriceText) + Float.parseFloat(textLaborText) + Float.parseFloat(textPartsText) + Float.parseFloat(textShopSuplies);
         total = price + (float) (price * .0825);
        textSubtotal.setText(String.valueOf(price));
        textTotalTax.setText(String.valueOf(total));
    }

    private  void cleanData(){
        textCode.setText("");
        textDescription.setText("");
        textCondition.setText("");
        textUnitPrice.setText("");
        textPrice.setText("");

    }

    private void createUIComponents() {
        imageLogo = new JLabel(new ImageIcon("logo.jpg"));
    }
}
