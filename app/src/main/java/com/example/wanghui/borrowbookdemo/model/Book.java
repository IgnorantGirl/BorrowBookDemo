package com.example.wanghui.borrowbookdemo.model;

public class Book {
    private  String name ;
    private  String style;
    private  int  suitableAge;
    private  int  pic;
    private  boolean isHistory;
    private  boolean isHorror;
    private  boolean isArt;


    public Book() {
    }

    public Book(String name, String style, int suitableAge, int pic, boolean isHistory, boolean isHorror, boolean isArt) {
        this.name = name;
        this.style = style;
        this.suitableAge = suitableAge;
        this.pic = pic;
        this.isHistory = isHistory;
        this.isHorror = isHorror;
        this.isArt = isArt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getSuitableAge() {
        return suitableAge;
    }

    public void setSuitableAge(int suitableAge) {
        this.suitableAge = suitableAge;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
    public boolean isHistory() {
        return isHistory;
    }

    public void setHistory(boolean history) {
        isHistory = history;
    }

    public boolean isHorror() {
        return isHorror;
    }

    public void setHorror(boolean horror) {
        isHorror = horror;
    }

    public boolean isArt() {
        return isArt;
    }

    public void setArt(boolean art) {
        isArt = art;
    }
}
