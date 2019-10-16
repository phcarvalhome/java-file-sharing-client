package com.phcarvalho.view;

import com.phcarvalho.controller.DownloadedFileController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.corba.FileData;
import com.phcarvalho.view.util.DialogUtil;
import com.phcarvalho.view.vo.FileDataAdapter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DownloadedFileView extends JPanel {

    private static final String TITLE = "Downloaded File";
//    private static final String EMPTY_LABEL = "-";
    private static final int WIDTH = 360;
    private static final int HEIGHT = 270;
    private static final String OPEN = "Open";

    private DownloadedFileController controller;
    private MainView mainView;
    private DialogUtil dialogUtil;
    private JList<FileDataAdapter> list;
    private JButton downloadSharedFileButton;

    public DownloadedFileView(DownloadedFileController controller) {
        super(new GridBagLayout());
        this.controller = controller;
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
        list = new JList();
        downloadSharedFileButton = new JButton(OPEN);
        initialize();
    }

    private void initialize() {
        TitledBorder titledBorder = new TitledBorder(TITLE);

        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBorder(titledBorder);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

//        list.setEnabled(false);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(list);

        scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(scrollPane, gridBagConstraints);

        downloadSharedFileButton.setEnabled(false);
        downloadSharedFileButton.addActionListener(actionEvent -> open());
        downloadSharedFileButton.setPreferredSize(new Dimension(120, 30));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(downloadSharedFileButton, gridBagConstraints);
    }

    public void open(){

        if (Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            String sharedDirectoryPath = Configuration.getSingleton().getSharedDirectoryPath();
            FileData fileData = list.getSelectedValue().getFileData();

            try {
                desktop.open(new File(sharedDirectoryPath + "\\" + fileData.metadata.name));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public JButton getDownloadSharedFileButton() {
        return downloadSharedFileButton;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public JList getList() {
        return list;
    }
}
