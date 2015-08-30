package com.tutorazadi.interestdestroyerandroid;

/**
 * Created by azadi on 8/29/15.
 */
public class Skin {

    private String name;
    private String backgroundColor;
    private String titleColor;
    private String buttonColor;

    public Skin()
    {
        // Set to defaults...
    }

    public Skin(String name, String backgroundColor, String titleColor, String buttonColor)
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


    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getButtonColor() {
        return buttonColor;
    }

    public void setButtonColor(String buttonColor) {
        this.buttonColor = buttonColor;
    }
}
