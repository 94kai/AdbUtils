package com.xk.adbutils.view;

import com.xk.adbutils.bean.Config;

import javax.swing.*;

public abstract class MJpanel extends JPanel {
    protected Config config;

    public void setConfig() {
        this.config = Config.instance;
        onConfigLoaded();
        doLayout();
    }


    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        init();
    }

    public int getLeft() {
        return getX();
    }

    public int getRight() {
        return getX() + getWidth();
    }

    public int getTop() {
        return getY();
    }

    public int getBottom() {
        return getY() + getHeight();
    }

    protected abstract void init();


    protected abstract void onConfigLoaded();
}
