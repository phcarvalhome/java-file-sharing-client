package com.phcarvalho.controller;

import com.phcarvalho.model.SharedFileModel;
import com.phcarvalho.model.corba.SharedFile;
import com.phcarvalho.model.corba.User;
import com.phcarvalho.view.SharedFileView;

public class SharedFileController {

    private SharedFileView view;
    private SharedFileModel model;

    public SharedFileController() {
    }

    public void initializeList() {
        view.getList().setModel(model.getList());
    }

    public SharedFile download(String name, User sourceUser){
        return model.download(name, sourceUser);
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

    public void setView(SharedFileView view) {
        this.view = view;
    }

    public void setModel(SharedFileModel model) {
        this.model = model;
    }
}
