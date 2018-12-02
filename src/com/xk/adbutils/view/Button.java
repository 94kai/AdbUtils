package com.xk.adbutils.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Button extends JButton {
    public Button(String text, Runnable runnable) {
        setText(text);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LogArea.addText(getText(), Color.RED);
                        runnable.run();
                    }
                }).start();
            }
        });

    }
}
