package com.example.util;

import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import org.springframework.stereotype.Component;

/**
 * Created by lsm on 2017/12/6.
 */
@Component
public class TestPdfListen implements RenderListener {
    @Override
    public void beginTextBlock() {

    }

    @Override
    public void renderText(TextRenderInfo textInfo) {
        String text = textInfo.getText();
        System.out.println("================"+text);
    }

    @Override
    public void endTextBlock() {

    }

    @Override
    public void renderImage(ImageRenderInfo imageRenderInfo) {

    }
}
