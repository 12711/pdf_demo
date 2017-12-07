package com.example.util;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.HeaderStories;
import org.apache.poi.hwpf.usermodel.Range;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * Created by lsm on 2017/12/6.
 */
public class TestUtils {
//    public static void main(String[] args) {
//        File file = new File("C:\\Users\\lsm\\Desktop\\demo12.docx");
//        if(file.exists()){
//            System.out.println(file.getName());
//        }
//
//        String line = "";
//        InputStream is = null;
//        try {
//            is = new FileInputStream(file);
//            StringBuilder sb = new StringBuilder();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
//            while ((line = reader.readLine()) != null){
//                sb.append(line);
//            }
//            System.out.println(new String(sb.toString()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
   public static void main(String[] args) throws Exception{
//       File file = new File("C:\\Users\\lsm\\Desktop\\demo.rft");
//        if(file.exists()){
//            System.out.println(file.getName());
//        }
//       InputStream is = null;
//
//       is = new FileInputStream(file);
//       System.out.println(readByteRtf(is));
       File file = new File("C:\\Users\\lsm\\Desktop\\demo.rtf");
       InputStream is = null;

       is = new FileInputStream(file);
       HWPFDocument doc = new HWPFDocument(is);
       readDocumentSummary(doc);
       System.out.println("=============");
//       readHeader(doc,0);
       System.out.println("=============");
//       readFooter(doc,0);
       System.out.println("=============");
       test();


   }

    public static  String test() throws Exception{
        File file = new File("C:\\Users\\lsm\\Desktop\\demo.rtf");
        if(file.exists()){
            System.out.println(file.getName());
        }
        InputStream is = null;

        is = new FileInputStream(file);
        HWPFDocument doc = new HWPFDocument(is);
        WordExtractor we = new WordExtractor(doc);
       String [] texts = we.getParagraphText();
       for(String str : texts){
           System.out.println(str);
       }

        System.out.println(doc.getDocumentText());
        return "";
    }

    public static void readHeader(HWPFDocument doc, int pageNumber){
        HeaderStories headerStore = new HeaderStories( doc);
        String header = headerStore.getHeader(pageNumber);
        System.out.println("Header Is: "+header);

    }

    public static void readFooter(HWPFDocument doc, int pageNumber){
        HeaderStories headerStore = new HeaderStories( doc);
        String footer = headerStore.getFooter(pageNumber);
        System.out.println("Footer Is: "+footer);

    }

    public static void readDocumentSummary(HWPFDocument doc) {
        DocumentSummaryInformation summaryInfo=doc.getDocumentSummaryInformation();
        String category = summaryInfo.getCategory();
        String company = summaryInfo.getCompany();
        int lineCount=summaryInfo.getLineCount();
        int sectionCount=summaryInfo.getSectionCount();
        int slideCount=summaryInfo.getSlideCount();
        System.out.println("---------------------------");
        System.out.println("Category: "+category);
        System.out.println("Company: "+company);
        System.out.println("Line Count: "+lineCount);
        System.out.println("Section Count: "+sectionCount);
        System.out.println("Slide Count: "+slideCount);

    }

}
