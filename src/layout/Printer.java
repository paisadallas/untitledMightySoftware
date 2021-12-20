package layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;


 public    class Printer implements Printable, ActionListener {
        ImageIcon printImage = new javax.swing.ImageIcon("logo.jpg");
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            if (pageIndex>0){
                return NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D)graphics;
            g2d.translate(pageFormat.getImageableX(),pageFormat.getImageableY());

            graphics.drawImage(printImage.getImage(),110,50,null);
            graphics.drawString("Total $"+String.valueOf(50000),100,200);
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

