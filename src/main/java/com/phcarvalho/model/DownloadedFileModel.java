package com.phcarvalho.model;

import com.phcarvalho.controller.DownloadedFileController;
import com.phcarvalho.view.vo.FileDataAdapter;

import javax.swing.*;

public class DownloadedFileModel {

    private DownloadedFileController controller;
    private DefaultListModel<FileDataAdapter> list;

    public DownloadedFileModel(DownloadedFileController controller) {
        this.controller = controller;
        list = new DefaultListModel();
    }

    public DefaultListModel<FileDataAdapter> getList() {
        return list;
    }
}
