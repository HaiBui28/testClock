package com.tapbi.spark.testzip.model;

public class TextViewModel  {
    private String content;
    private String textColor;
    private int textSize;
    private float ratioLeft;

    private float ratioTop;

    public TextViewModel(String content, String textColor, int textSize, float ratioLeft, float ratioTop) {
        this.content = content;
        this.textColor = textColor;
        this.textSize = textSize;
        this.ratioLeft = ratioLeft;
        this.ratioTop = ratioTop;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public float getRatioLeft() {
        return ratioLeft;
    }

    public void setRatioLeft(float ratioLeft) {
        this.ratioLeft = ratioLeft;
    }

    public float getRatioTop() {
        return ratioTop;
    }

    public void setRatioTop(float ratioTop) {
        this.ratioTop = ratioTop;
    }
}
