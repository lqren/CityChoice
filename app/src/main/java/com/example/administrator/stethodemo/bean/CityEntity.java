package com.example.administrator.stethodemo.bean;


public class CityEntity {

    private String name;
    private String firstWord;
    private boolean isIndex;


    public CityEntity(String name, String firstWord) {
        this.name = name;
        this.firstWord = firstWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstWord() {
        return firstWord;
    }

    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }

    public boolean isIndex() {
        return isIndex;
    }

    public void setIndex(boolean index) {
        isIndex = index;
    }
}
