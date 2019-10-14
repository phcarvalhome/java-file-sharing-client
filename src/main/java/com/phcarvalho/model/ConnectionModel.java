package com.phcarvalho.model;

import com.phcarvalho.controller.ConnectionController;
import com.phcarvalho.filesharing.User;

import java.rmi.RemoteException;

public class ConnectionModel {

    private ConnectionController controller;
//    private IConnectionStrategy connectionStrategy;
//    private ICommandTemplateFactory commandTemplateFactory;
//    private ConnectedPlayerModel connectedPlayerModel;
//    private BoardModel boardModel;

    public ConnectionModel(ConnectionController controller) {
        this.controller = controller;
//        connectionStrategy = DependencyFactory.getSingleton().get(IConnectionStrategy.class);
//        commandTemplateFactory = DependencyFactory.getSingleton().get(ICommandTemplateFactory.class);
//        connectedPlayerModel = DependencyFactory.getSingleton().get(ConnectedPlayerModel.class);
//        boardModel = DependencyFactory.getSingleton().get(BoardModel.class);
    }

    public void connectToServer(User localUser, User remoteUser) throws RemoteException {
//        Configuration.getSingleton().setLocalUser(localUser);
//        Configuration.getSingleton().setRemoteUser(remoteUser);
//        connectionStrategy.connectToServer(remoteUser);
//        commandTemplateFactory.getConnection().connect(new ConnectCommand(remoteUser));
    }

//    public void connectToServerByCallback(ConnectCommand connectCommand) {
//        Configuration.getSingleton().setServerConnected(true);
//        controller.connectToServerByCallback();
//    }

    public void disconnect() throws RemoteException {
//        Game gameSelected = Configuration.getSingleton().getGameSelected();
//
//        if(gameSelected != null){
//            Player player = Configuration.getSingleton().getPlayer();
//
//            commandTemplateFactory.getMain().addPlayer(new AddPlayerCommand(player, gameSelected, true));
//        }
////        else
////        commandTemplateFactory.getConnection().disconnect(new DisconnectCommand()); //TODO precisa disso mesmo aqui (disconnect por fechar frame do jogo)...
    }

//    public void disconnectByCallback(DisconnectCommand disconnectCommand) {
//        controller.disconnectByCallback(disconnectCommand);
//    }
//
//    public void clear() {
//        Configuration.getSingleton().clear();
//        controller.clear();
//    }
}
