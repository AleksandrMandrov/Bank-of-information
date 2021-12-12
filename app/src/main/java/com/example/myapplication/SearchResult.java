package com.example.myapplication;

public class SearchResult {
    private String mainText; // название
    private String additionalText;  // столица

    public SearchResult(String mainText, String additionalText){

        this.mainText = mainText;
        this.additionalText = additionalText;
    }

    public String getMainText() {
        return this.mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getAdditionalText() {
        return this.additionalText;
    }

    public void setAdditionalText(String additionalText) {
        this.additionalText = additionalText;
    }
}