package com.tapbi.spark.testzip.model;

public class ImageViewModel {
    private int imgContent;
    private int marginTop;
    private int marginLeft;

    public ImageViewModel(int imgContent, int marginTop, int marginLeft) {
        this.imgContent = imgContent;
        this.marginTop = marginTop;
        this.marginLeft = marginLeft;
    }

    public int getImgContent() {
        return imgContent;
    }

    public void setImgContent(int imgContent) {
        this.imgContent = imgContent;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }
}
