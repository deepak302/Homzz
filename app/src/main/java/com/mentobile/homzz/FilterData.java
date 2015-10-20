package com.mentobile.homzz;

import android.widget.TextView;

/**
 * Created by user on 10/19/2015.
 */
public class FilterData {

    public static final int GRAY = 0X1D394C;
    public static final int GREEN = 0xFF00FF00;

    private String type;
    private int textViewID;
    private boolean isPressed;

    public FilterData(String type, int textView, boolean isPressed) {
        this.type = type;
        this.textViewID = textView;
        this.isPressed = isPressed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTextView() {
        return textViewID;
    }

    public void setTextView(int textView) {
        this.textViewID = textView;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }
}
