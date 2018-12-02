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

    public LogArea() throws HeadlessException {
        super(new JTextPane());
        instance = this;
        logPan = (JTextPane) getViewport().getView();
    }


    public static void addText(String text, Color foreground) {
        addTextWithDate(text,foreground,new Date());
    }

    public static void addTextWithDate(String text, Color foreground, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
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


    }

    public static void clear() {
        instance.logPan.setText("");
    }
}
