package com.phcarvalho.view;

import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.view.listener.SelectSharedDirectoryListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;

public class SharedDirectoryPathView extends JPanel {

    private static final String SELECT_SHARED_DIRECTORY = "Select Shared Directory";
    private static final String EMPTY_LABEL = "-";

    private JButton selectSharedDirectoryButton;
    private JFileChooser chooser;
    private JLabel userNameLabel;
    private JLabel userNameValueLabel;

    public SharedDirectoryPathView() {
        selectSharedDirectoryButton = new JButton(SELECT_SHARED_DIRECTORY);
        chooser = new JFileChooser();
        userNameLabel = new JLabel("Selected Directory:");
        userNameValueLabel = new JLabel(EMPTY_LABEL);
        initialize();
    }

    private void initialize() {
        TitledBorder titledBorder = new TitledBorder("Shared Directory");

        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBorder(titledBorder);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

//        selectSharedDirectoryButton.setEnabled(false);
        selectSharedDirectoryButton.addActionListener(new SelectSharedDirectoryListener(() -> selectSharedDirectory()));
        selectSharedDirectoryButton.setPreferredSize(new Dimension(180, 30));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(selectSharedDirectoryButton, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(userNameLabel, gridBagConstraints);

        userNameValueLabel.setPreferredSize(new Dimension(420, 40));
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(userNameValueLabel, gridBagConstraints);
    }

    public void selectSharedDirectory() {
        chooser.setCurrentDirectory(new File(Configuration.getSingleton().getSharedDirectoryPath()));
        chooser.setDialogTitle(SELECT_SHARED_DIRECTORY);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setApproveButtonText("Select");

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            Configuration.getSingleton().setSharedDirectoryPath(chooser.getSelectedFile().getPath());
            userNameValueLabel.setText(chooser.getSelectedFile().getPath());
        }
    }
}
