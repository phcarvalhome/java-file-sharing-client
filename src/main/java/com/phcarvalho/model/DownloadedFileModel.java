package com.phcarvalho.model;

import com.phcarvalho.controller.DownloadedFileController;
import com.phcarvalho.filesharing.SharedFile;

import javax.swing.*;

public class DownloadedFileModel {

    private DownloadedFileController controller;
    private DefaultListModel<SharedFile> list;

    public DownloadedFileModel(DownloadedFileController controller) {
        this.controller = controller;
        list = new DefaultListModel();
    }

//    public void add(Player player) {
//        list.addElement(player);
//    }
//
//    public void removeByCallback(AddPlayerCommand addPlayerCommand) {
//        Integer gameId = addPlayerCommand.getGame().getId();
//        User sourceUser = addPlayerCommand.getSourceUser();
//
//        Configuration.getSingleton().getGame(gameId).removeUser(sourceUser);
//
//        Game gameSelected = Configuration.getSingleton().getGameSelected();
//        Player player = addPlayerCommand.getPlayer();
//
//        if((gameSelected != null) && (gameSelected.getId().equals(gameId))){
//            list.removeElement(player);
//            controller.removeByCallback(player);
//        }
//    }
//
//    public void clear() {
//        list.clear();
//    }
//
//    public int getPlayerIndex(Player player) {
//        return list.indexOf(player);
//    }

    public DefaultListModel<SharedFile> getList() {
        return list;
    }
}
