package com.xk.mutils.view;

import com.xk.mutils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

/**
 * 安装apk的工作区
 */
public class InstallAppArea extends JPanel {

    private JList sourceList;
    private ArrayList<File> listFiles;

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel jLabel = new JLabel("安装apk：");
        add(jLabel);
        sourceList = new JList();
        sourceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane sourceListScroller = new JScrollPane(sourceList);
        add(sourceListScroller);


        JPanel btnContainer = new JPanel();

        JButton refresh = new JButton("刷新");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshApks();
            }
        });

        JButton install = new JButton("安装");
        install.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                installApk();
            }


        });
        refreshApks();

        btnContainer.add(refresh);
        btnContainer.add(install);
        add(btnContainer);
    }

    private void installApk() {
        int selectedIndex = sourceList.getSelectedIndex();
        File file = listFiles.get(selectedIndex);
        Utils.executeShell("adb", "-s vvv", "install " + file.getAbsolutePath());
    }


    public void refreshApks() {

        new Thread(new Runnable() {


            @Override
            public void run() {
//                File customApkPathParent = new File(customApkPath.getText());
                File customApkPathParent = new File("./");
                String absolutePath = customApkPathParent.getAbsolutePath();
                absolutePath = absolutePath.substring(0, absolutePath.length() - 1);
                System.out.println(absolutePath);
                File file = new File(absolutePath);
                File[] files = file.listFiles();
                listFiles = new ArrayList<>();
                DefaultListModel listModel = new DefaultListModel();
                for (File file1 : files) {
                    if (file1.getName().endsWith(".apk")) {
                        listModel.addElement(file1.getName());
                        listFiles.add(file1);
                    }
                }
                sourceList.setModel(listModel);
            }
        }).start();


    }
}
