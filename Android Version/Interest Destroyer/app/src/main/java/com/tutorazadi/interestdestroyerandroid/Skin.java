package com.tutorazadi.interestdestroyerandroid;

/**
 * Created by azadi on 8/29/15.
 */
public class Skin {

    private String name;
    private int backgroundColor;
    private String titleColor;
    private int buttonColor;

    public Skin()
    {
        // Set to defaults...
    }

    public Skin(String name, int backgroundColor, String titleColor, int buttonColor)
    {
        this.name = name;
        this.backgroundColor = backgroundColor;
        this.titleColor = titleColor;
        this.buttonColor = buttonColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public int getButtonColor() {
        return buttonColor;
    }

    public void setButtonColor(int buttonColor) {
        this.buttonColor = buttonColor;
    }
}
