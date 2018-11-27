package com.xk.mutils.view;

import com.xk.mutils.Config;
import com.xk.mutils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton {
    public Button(String text,Runnable runnable) {
        setText(text);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Utils.addText(getText(),Color.RED);
                        runnable.run();
                    }
                }).start();
            }
        });

    }
}
