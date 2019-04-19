package com.xk.adbutils.view;

import com.xk.adbutils.ThreadUtils;

import java.awt.Color;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * 日志区
 */
public class LogArea extends JScrollPane {

    public static LogArea instance;
    private final JTextPane logPan;

    /**
     * 存储上一条日志，用来去重
     */
    private static String logTemp;
    public LogArea() throws HeadlessException {
        super(new JTextPane());
        instance = this;
        logPan = (JTextPane) getViewport().getView();
    }


    public static void addText(String text, Color foreground) {
        ThreadUtils.executeDelay(() -> {
            addTextWithDate(text,foreground,new Date());
        },0);
    }

    public static void addTextWithDate(String text, Color foreground, Date date) {
        if (text.equals(logTemp)) {
            //去重
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss ");
        String string = simpleDateFormat.format(date) + text + "\n\n";
        StyledDocument d = instance.logPan.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr, foreground);
//        StyleConstants.setBackground(attr, background);
        try {
            d.insertString(instance.logPan.getText().length(), string, attr);
            instance.getVerticalScrollBar().setValue(instance.getVerticalScrollBar().getMaximum());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        logTemp = text;
    }

    public static void clear() {
        instance.logPan.setText("");
    }
}
