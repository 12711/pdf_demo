package com.example.demo.pdf.bean;

/**
 * Created by lsm on 2017/12/7.
 * 记录需要替换的文本的位置
 */
public class ReplaceRegion {
    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getW() {
        return w;
    }

    public void setW(Float w) {
        this.w = w;
    }

    public Float getH() {
        return h;
    }

    public void setH(Float h) {
        this.h = h;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private Float x;
    private Float y;
    private Float w;
    private Float h;
    private String content;

}
