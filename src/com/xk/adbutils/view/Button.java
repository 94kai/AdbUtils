package com.xk.adbutils.view;

import com.xk.adbutils.ThreadUtils;

import java.awt.Color;

import javax.swing.JButton;

public class Button extends JButton {

    public Button(String text, Runnable runnable) {
        setText(text);

        addActionListener(e -> ThreadUtils.execute(() -> {
            LogArea.addText(getText(), Color.RED);
            runnable.run();
        }));

    }
}
