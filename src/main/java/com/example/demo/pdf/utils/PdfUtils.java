package com.example.demo.pdf.utils;

import com.example.demo.pdf.bean.ReplaceRegion;
import com.example.demo.pdf.listener.listener;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lsm on 2017/12/7.
 * pdf 文本查找以及替换的工具类
 */
public class PdfUtils {
    private List<String> replaceText;
    private Map<String,ReplaceRegion> resultMap = new HashMap<>();
    private ByteArrayOutputStream output;
    private PdfReader reader;
    private PdfStamper stamper;
    private PdfContentByte canvas;
    private Font font;
    private PdfReaderContentParser parser;

    public PdfUtils(String filePath,List<String> replaceText) throws Exception{
        FileInputStream in = null;
        this.replaceText = replaceText;
        try{
            in =new FileInputStream(filePath);
            byte[] pdfBytes = new byte[in.available()];
            in.read(pdfBytes);
            init(pdfBytes);
        }finally{
            in.close();
        }
    }

    public void init(byte [] bytes) throws Exception{
        System.out.println("初始化开始");
//        reader = new PdfReader(bytes);
        reader = new PdfReader("C:\\Users\\lsm\\Desktop\\demoAfterModfiy.pdf");
        output = new ByteArrayOutputStream();
        stamper = new PdfStamper(reader, output);
        canvas = stamper.getOverContent(1);
        parser = new PdfReaderContentParser(reader);
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        font = new Font(bf,10,Font.BOLD);
        System.out.println("初始化成功");
    }

    /**
     * 替换文本
     * @throws DocumentException
     * @throws IOException
     */
    public void process() throws Exception {
        try{
            getResultMap();
            canvas.saveState();
            Set<Map.Entry<String, ReplaceRegion>> entrys = resultMap.entrySet();
            for (Map.Entry<String, ReplaceRegion> entry : entrys) {
                ReplaceRegion value = entry.getValue();
                canvas.setColorFill(BaseColor.WHITE);
                canvas.rectangle(value.getX(),value.getY(),66, 10);
            }
            canvas.fill();
            canvas.restoreState();
            //开始写入文本
            canvas.lineTo(20,30);
            canvas.beginText();

            for (Map.Entry<String, ReplaceRegion> entry : entrys) {
                ReplaceRegion value = entry.getValue();
                //设置字体
                canvas.setFontAndSize(font.getBaseFont(), 10);
                canvas.setTextMatrix(value.getX(),value.getY()+1/*修正背景与文本的相对位置*/);
                canvas.showText((String) value.getContent());
            }
            canvas.endText();
        }finally{
            if(stamper != null){
                stamper.close();
            }
        }
    }


    public Map<String,ReplaceRegion> getResultMap() throws Exception{
        listener listener = new listener(replaceText,resultMap);
        parser.processContent(1,listener);
        return resultMap;
    }


    public void toPdf(String targetPath) throws Exception{
        FileOutputStream fileOutputStream = null;
        try{
            process();
            fileOutputStream = new FileOutputStream(targetPath);
            fileOutputStream.write(output.toByteArray());
            fileOutputStream.flush();
        }catch(IOException e){
            throw e;
        }finally{
            if(fileOutputStream != null){
                fileOutputStream.close();
            }
        }
        System.out.println("文件生成成功");
    }

}
