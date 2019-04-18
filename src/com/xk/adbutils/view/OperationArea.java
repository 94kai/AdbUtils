package com.xk.adbutils.view;

import java.awt.Color;

/**
 * 操作区
 */
public class OperationArea extends MJpanel {


    private SelectDeviceArea selectDeviceArea;
    public CustomAdbArea customAdbArea;
    private VariateArea variateArea;
    private InstallAppArea installAppArea;


    @Override
    protected void init() {
        setBackground(Color.black);
        setLayout(null);
        selectDeviceArea = new SelectDeviceArea();
        selectDeviceArea.setBounds(0, 0, 100, getHeight());
        add(selectDeviceArea);

        installAppArea = new InstallAppArea();
        installAppArea.setBounds(selectDeviceArea.getRight(), 0, 200, getHeight());
        variateArea = new VariateArea();
        variateArea.setBounds(installAppArea.getRight(), 0, 200, getHeight());
        customAdbArea = new CustomAdbArea();
        customAdbArea.setBounds(variateArea.getRight(), 0, getWidth() - variateArea.getRight(), getHeight());
//
        add(installAppArea);
        add(variateArea);
        add(customAdbArea);
    }



    @Override
    public void onConfigLoaded() {
        selectDeviceArea.setConfig();
        customAdbArea.setConfig();
        variateArea.setConfig();
        installAppArea.setConfig();
    }


}
