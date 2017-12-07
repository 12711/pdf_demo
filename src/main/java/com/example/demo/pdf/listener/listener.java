package com.example.demo.pdf.listener;

import com.example.demo.pdf.bean.ReplaceRegion;
import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsm on 2017/12/6.
 */
public class listener implements RenderListener{
    List<String> replaceText;
    Map<String,ReplaceRegion> replaceDatas;

    public listener(List<String> replaceText,Map<String,ReplaceRegion> replaceDatas){
        this.replaceText = replaceText;
        this.replaceDatas = replaceDatas;
    }

    @Override
    public void beginTextBlock() {

    }

    public Map<String,ReplaceRegion> getReplaceDatas(){
        return this.getReplaceDatas();
    }

    @Override
    public void renderText(TextRenderInfo textRenderInfo) {
        ReplaceRegion region = null;
        Rectangle2D.Float aFloat = null;
        System.out.println("-------------"+textRenderInfo.getText());
        System.out.println(textRenderInfo.getText().equals("$"));

        System.out.println(textRenderInfo.getText().equals(replaceText.get(0)));
        for(int i = 0;i < replaceText.size(); i++ ){
            if( textRenderInfo.getText().equals(replaceText.get(i))){
                aFloat = textRenderInfo.getBaseline().getBoundingRectange();
                region = new ReplaceRegion();
                region.setH((float)aFloat.getHeight());
                region.setW((float)aFloat.getWidth());
                region.setX((float)aFloat.getX());
                region.setY((float)aFloat.getY());
                region.setContent("上海汉得信息技术股份有限公司");
                //需要替换的文本的位置信息
                replaceDatas.put(replaceText.get(i),region);
            }
        }
    }

    @Override
    public void endTextBlock() {

    }

    @Override
    public void renderImage(ImageRenderInfo imageRenderInfo) {

    }
}
