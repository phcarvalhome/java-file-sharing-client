package com.phcarvalho.view;

import com.phcarvalho.controller.DownloadedFileController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.view.util.DialogUtil;
import com.phcarvalho.view.vo.FileDataAdapter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DownloadedFileView extends JPanel {

    private static final String TITLE = "Downloaded File";
//    private static final String EMPTY_LABEL = "-";
    private static final int WIDTH = 280;
    private static final int HEIGHT = 120;

    private DownloadedFileController controller;
    private MainView mainView;
    private DialogUtil dialogUtil;
    private JList<FileDataAdapter> list;

    public DownloadedFileView(DownloadedFileController controller) {
        super(new GridBagLayout());
        this.controller = controller;
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
        list = new JList();
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
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public JList getList() {
        return list;
    }
}
