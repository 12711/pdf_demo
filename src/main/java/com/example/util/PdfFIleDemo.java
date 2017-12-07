package com.example.util;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.RenderListener;

import javax.servlet.ReadListener;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lsm on 2017/12/6.
 */
public class PdfFIleDemo {
    public static void main(String[] args) throws Exception {

        modifyPdf();
    }

    public static void readerPdf() throws Exception{
        PdfReader reader = new PdfReader("C:\\Users\\lsm\\Desktop\\demo.pdf");
        int pages = reader.getNumberOfPages();
        for(int i=1; i<=pages; i++) {
            //Extract the page content using PdfTextExtractor.
            String pageContent = PdfTextExtractor.getTextFromPage(reader,i);

            //Print the page content on console.
            System.out.println("Content on Page "
                    + i + ": " + pageContent);
        }
    }

    public static void modifyPdf(){
        try {
            //Create PdfReader instance.
            PdfReader pdfReader =
                    new PdfReader("C:\\Users\\lsm\\Desktop\\demo.pdf");

            //Create PdfStamper instance.
            PdfStamper pdfStamper = new PdfStamper(pdfReader,
                    new FileOutputStream("C:\\Users\\lsm\\Desktop\\modifyDemo.pdf"));

            //Create BaseFont instance.
            BaseFont baseFont = BaseFont.createFont(
                    BaseFont.TIMES_ROMAN,
                    BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

            //Get the number of pages in pdf.
            int pages = pdfReader.getNumberOfPages();

            //Iterate the pdf through pages.
            for(int i=1; i<=pages; i++) {
                //Contain the pdf data.
                PdfContentByte pageContentByte =
                        pdfStamper.getOverContent(i);

                pageContentByte.beginText();
                //Set text font and size.
                pageContentByte.setFontAndSize(baseFont, 14);

                pageContentByte.setTextMatrix(50, 740);
                //Write text
                pageContentByte.showText("javawithease.com");
                pageContentByte.endText();
            }

            //Close the pdfStamper.
            pdfStamper.close();

            System.out.println("PDF modified successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void parse() throws IOException {
        PdfReader pdfReader =
                new PdfReader("C:\\Users\\lsm\\Desktop\\demo.pdf");

        TestPdfListen listener = new TestPdfListen();
        PdfReaderContentParser parser = new PdfReaderContentParser(pdfReader);
        parser.processContent(1, listener);
    }

}
