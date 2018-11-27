package com.xk.mutils.view;

import com.xk.mutils.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * 日志区
 */
public class LogArea extends JScrollPane {

    public LogArea(int x, int y, int width, int height) throws HeadlessException {
        super(new JTextPane());
        setBounds(x, y, width, height);
        JTextPane logPan = (JTextPane) getViewport().getView();
        Utils.setLogPan(logPan);
    }


}
