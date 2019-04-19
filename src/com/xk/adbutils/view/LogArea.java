package com.xk.adbutils.view;

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
        addTextWithDate(text, foreground, new Date());
    }

    public static void addTextWithDate(String text, Color foreground, Date date) {
        if (text.contains("%]") && text.contains("[")) {//针对安装app日志优化。
            if (text.contains("2%]")||text.contains("4%]")||text.contains("6%]")||text.contains("7%]")) {
                    return;
            }
        }
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
            d.insertString(instance.logPan.getStyledDocument().getLength(), string, attr);
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
