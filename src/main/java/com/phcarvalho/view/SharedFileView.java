package com.phcarvalho.view;

import com.phcarvalho.controller.SharedFileController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.corba.FileData;
import com.phcarvalho.model.corba.FileMetadata;
import com.phcarvalho.view.listener.SearchSharedFileKeyListener;
import com.phcarvalho.view.util.DialogUtil;
import com.phcarvalho.view.vo.FileMetadataAdapter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SharedFileView extends JPanel {

    private static final String TITLE = "Shared File";
    private static final String EMPTY_LABEL = "-";
    private static final int WIDTH = 360;
    private static final int HEIGHT = 240;
    private static final String DOWNLOAD = "Download";

    private SharedFileController controller;
    private MainView mainView;
    private DialogUtil dialogUtil;
    private JList<FileMetadataAdapter> list;
    private JTextField searchSharedFileField;
    private JButton selectSharedFileButton;

    public SharedFileView(SharedFileController controller) {
        super(new GridBagLayout());
        this.controller = controller;
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
        list = new JList();
        searchSharedFileField = new JTextField();
        selectSharedFileButton = new JButton(DOWNLOAD);
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

        searchSharedFileField.setEnabled(false);
        searchSharedFileField.addKeyListener(new SearchSharedFileKeyListener(() -> searchSharedFile()));
        searchSharedFileField.setPreferredSize(new Dimension(WIDTH, 30));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(searchSharedFileField, gridBagConstraints);

        selectSharedFileButton.setEnabled(false);
        selectSharedFileButton.addActionListener(actionEvent -> download());
        selectSharedFileButton.setPreferredSize(new Dimension(120, 30));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(selectSharedFileButton, gridBagConstraints);
    }

    private void searchSharedFile() {
        String text = searchSharedFileField.getText();

        controller.searchSharedFile(text);
    }

    public void download(){
        FileMetadata fileMetadata = list.getSelectedValue().getFileMetadata();
        FileData fileData = controller.downloadFileData(fileMetadata);
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public JList getList() {
        return list;
    }

    public JTextField getSearchSharedFileField() {
        return searchSharedFileField;
    }

    public JButton getSelectSharedFileButton() {
        return selectSharedFileButton;
    }
}
