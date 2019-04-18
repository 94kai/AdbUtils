package com.xk.adbutils.view;

import com.xk.adbutils.ThreadUtils;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JButton;

public class Button extends JButton {

    public Button(String text, Runnable runnable) {
        setMargin(new Insets(-5, 0, -5, 0));
        setText(text);

        addActionListener(e -> ThreadUtils.execute(() -> {
            LogArea.addText(getText(), Color.RED);
            runnable.run();
        }));

    }
}
