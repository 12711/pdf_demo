package com.example.demo.pdf.testDemo;

import com.example.demo.pdf.utils.PdfUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsm on 2017/12/7.
 */
public class TestDemo {

    public static void main(String[] args) throws Exception{
        List<String> replaceText = new ArrayList<>();
        replaceText.add("$");
        PdfUtils pdfUtils = new PdfUtils("C:\\Users\\lsm\\Desktop\\demoAfterModfiy.pdf",replaceText);
        pdfUtils.process();
//        pdfUtils.toPdf("C:\\Users\\lsm\\Desktop\\demoAfterModfiy.pdf");
    }
}
