package com.xk.adbutils.view;

import com.xk.adbutils.ShellUtils;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * 安装apk的工作区
 */
public class InstallAppArea extends MJpanel {

    private JList sourceList;
    private ArrayList<File> listFiles;

    @Override
    protected void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel jLabel = new JLabel("安装apk：");
        add(jLabel);
        sourceList = new JList();
        sourceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane sourceListScroller = new JScrollPane(sourceList);
        add(sourceListScroller);


        JPanel btnContainer = new JPanel();
        btnContainer.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;


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
        btnContainer.add(refresh, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        btnContainer.add(install, gridBagConstraints);
        add(btnContainer);
    }

    @Override
    protected void onConfigLoaded() {

    }

    private void installApk() {
        int selectedIndex = sourceList.getSelectedIndex();
        if (selectedIndex == -1) {
            LogArea.addText("ERROR!!! 请选择要安装的apk！！！", Color.RED);
            return;
        }
        File file = listFiles.get(selectedIndex);
        String defaultUninstallPackageName = config.getVariate("uninstallPackageName");
        if (defaultUninstallPackageName == null || defaultUninstallPackageName.equals("")) {
            defaultUninstallPackageName="com.jingdong.xx";
        }
        ShellUtils.executeShellWithLog("uninstall " + defaultUninstallPackageName);
        ShellUtils.executeShellWithLog("install -r " + file.getAbsolutePath());
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