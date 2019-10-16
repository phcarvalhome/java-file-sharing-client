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
    private static final int WIDTH = 280;
    private static final int HEIGHT = 120;
    private static final String DOWNLOAD = "Download";

    private SharedFileController controller;
    private MainView mainView;
    private DialogUtil dialogUtil;
    private JList<FileMetadataAdapter> list;
    private JTextField messageTextField;
    private JButton selectGameButton;

    public SharedFileView(SharedFileController controller) {
        super(new GridBagLayout());
        this.controller = controller;
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
        list = new JList();
        messageTextField = new JTextField();
        selectGameButton = new JButton(DOWNLOAD);
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

        messageTextField.setEnabled(false);
        messageTextField.addKeyListener(new SearchSharedFileKeyListener(() -> searchFile()));
        messageTextField.setPreferredSize(new Dimension(WIDTH, 30));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(messageTextField, gridBagConstraints);

        selectGameButton.setEnabled(false);
        selectGameButton.addActionListener(actionEvent -> download());
        selectGameButton.setPreferredSize(new Dimension(120, 30));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(selectGameButton, gridBagConstraints);
    }

    private void searchFile() {
        String text = messageTextField.getText();

        controller.searchFile(text);
    }

    public void download(){
        FileMetadata fileMetadata = list.getSelectedValue().getFileMetadata();
        FileData fileData = controller.download(fileMetadata);
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public JList getList() {
        return list;
    }

    public JTextField getMessageTextField() {
        return messageTextField;
    }

    public JButton getSelectGameButton() {
        return selectGameButton;
    }
}
