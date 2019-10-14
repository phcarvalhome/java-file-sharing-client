package com.phcarvalho.controller;

import com.phcarvalho.model.DownloadedFileModel;
import com.phcarvalho.view.DownloadedFileView;

public class DownloadedFileController {

    private DownloadedFileView view;
    private DownloadedFileModel model;

    public DownloadedFileController() {
    }

    public void initializeList() {
        view.getList().setModel(model.getList());
    }

//    public void add(Player player) {
//        model.add(player);
//    }
//
//    public void removeByCallback(Player player) {
//        view.removeByCallback(player);
//    }
//
//    public int getPlayerIndex(Player player) {
//        return model.getPlayerIndex(player);
//    }
//
//    public void clear() {
//        model.clear();
//    }

    public void setView(DownloadedFileView view) {
        this.view = view;
    }

    public void setModel(DownloadedFileModel model) {
        this.model = model;
    }
}
