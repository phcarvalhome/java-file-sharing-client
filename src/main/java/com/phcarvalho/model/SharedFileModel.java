package com.phcarvalho.model;

import com.phcarvalho.controller.SharedFileController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.corba.SharedFile;
import com.phcarvalho.model.corba.User;
import com.phcarvalho.model.corba.client.ClientService;

import javax.swing.*;

public class SharedFileModel {

    private SharedFileController controller;
    private DefaultListModel<SharedFile> list;
    private ConnectionModel connectionModel;

    public SharedFileModel(SharedFileController controller) {
        this.controller = controller;
        list = new DefaultListModel();
        connectionModel = DependencyFactory.getSingleton().get(ConnectionModel.class);
    }

    public SharedFile download(String name, User sourceUser){
        String id = sourceUser.name + "::" + sourceUser.host + "::" + sourceUser.port;
        ClientService clientService = connectionModel.getClientService(id);
        SharedFile sharedFile = clientService.downloadSharedFile(name);

        return sharedFile;
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
