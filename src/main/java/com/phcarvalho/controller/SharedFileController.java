package com.phcarvalho.controller;

import com.phcarvalho.model.SharedFileModel;
import com.phcarvalho.model.corba.FileData;
import com.phcarvalho.model.corba.FileMetadata;
import com.phcarvalho.view.SharedFileView;

public class SharedFileController {

    private SharedFileView view;
    private SharedFileModel model;

    public SharedFileController() {
    }

    public void initializeList() {
        view.getList().setModel(model.getList());
    }

    public void searchFile(String text) {
        model.searchFile(text);
    }

    public FileData download(FileMetadata fileMetadata){
        return model.download(fileMetadata);
    }

    public void setView(SharedFileView view) {
        this.view = view;
    }

    public void setModel(SharedFileModel model) {
        this.model = model;
    }
}
