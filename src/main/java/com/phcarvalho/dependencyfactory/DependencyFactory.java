package com.phcarvalho.dependencyfactory;

import com.phcarvalho.controller.ConnectionController;
import com.phcarvalho.controller.DownloadedFileController;
import com.phcarvalho.controller.MainController;
import com.phcarvalho.controller.SharedFileController;
import com.phcarvalho.model.ConnectionModel;
import com.phcarvalho.model.DownloadedFileModel;
import com.phcarvalho.model.MainModel;
import com.phcarvalho.model.SharedFileModel;
import com.phcarvalho.view.ConnectionView;
import com.phcarvalho.view.DownloadedFileView;
import com.phcarvalho.view.MainView;
import com.phcarvalho.view.SharedFileView;
import com.phcarvalho.view.util.DialogUtil;

import java.util.HashMap;
import java.util.Map;

public class DependencyFactory {

    private static DependencyFactory singleton;

    public static DependencyFactory getSingleton(){

        if(singleton == null)
            singleton = new DependencyFactory();

        return singleton;
    }

//    private ICommunicationStrategy communicationStrategyFactory;
    private Map<Class<?>, Object> dependencyMap;

    private DependencyFactory() {
        dependencyMap = new HashMap<>();
    }

    public void build(){
        dependencyMap.put(DialogUtil.class, new DialogUtil());
//        dependencyMap.put(CommandInvoker.class, new CommandInvoker());
//        dependencyMap.put(SocketConnectionStrategy.class, new SocketConnectionStrategy());
//        dependencyMap.put(IConnectionStrategy.class, communicationStrategyFactory.buildConnectionStrategy());
//        dependencyMap.put(ICommandTemplateFactory.class, communicationStrategyFactory.buildCommandTemplateFactory());
//        dependencyMap.put(StartingPositionConfigurationRegistry.class, new StartingPositionConfigurationRegistry());

//        buildBoardMVC();
        buildConnectionMVC();
        buildDownloadedFileMVC();
        buildSharedFileMVC();
//        buildGameMVC();
//        buildChatMVC();
        buildMainMVC();

//        get(CommandInvoker.class).buildCommandObserverMap();
////        get(IConnectionStrategy.class).setCommandInvoker(get(CommandInvoker.class));
//
//        dependencyMap.put(BoardPositionTransferHandler.class, new BoardPositionTransferHandler());
    }

//    private void buildBoardMVC() {
//        BoardController boardController = new BoardController();
//        BoardView boardView = new BoardView(boardController);
//        BoardModel boardModel = new BoardModel(boardController);
//
//        boardController.setView(boardView);
//        boardController.setModel(boardModel);
//        dependencyMap.put(BoardView.class, boardView);
//        dependencyMap.put(BoardModel.class, boardModel);

//    }

    private void buildConnectionMVC() {
        ConnectionController connectionController = new ConnectionController();
        ConnectionView connectionView = new ConnectionView(connectionController);
        ConnectionModel connectionModel = new ConnectionModel(connectionController);

        connectionController.setView(connectionView);
        connectionController.setModel(connectionModel);
        dependencyMap.put(ConnectionView.class, connectionView);
        dependencyMap.put(ConnectionModel.class, connectionModel);
    //        get(IConnectionHandlerStrategy.class).setConnectionModel(connectionModel);
    }

    private void buildDownloadedFileMVC() {
        DownloadedFileController downloadedFileController = new DownloadedFileController();
        DownloadedFileView downloadedFileView = new DownloadedFileView(downloadedFileController);
        DownloadedFileModel downloadedFileModel = new DownloadedFileModel(downloadedFileController);

        downloadedFileController.setView(downloadedFileView);
        downloadedFileController.setModel(downloadedFileModel);
        downloadedFileController.initializeList();
        dependencyMap.put(DownloadedFileView.class, downloadedFileView);
        dependencyMap.put(DownloadedFileModel.class, downloadedFileModel);
//        get(IConnectionHandlerStrategy.class).setDownloadedFileModel(downloadedFileModel);
    }

    private void buildSharedFileMVC() {
        SharedFileController sharedFileController = new SharedFileController();
        SharedFileView sharedFileView = new SharedFileView(sharedFileController);
        SharedFileModel sharedFileModel = new SharedFileModel(sharedFileController);

        sharedFileController.setView(sharedFileView);
        sharedFileController.setModel(sharedFileModel);
        sharedFileController.initializeList();
        dependencyMap.put(SharedFileView.class, sharedFileView);
        dependencyMap.put(SharedFileModel.class, sharedFileModel);
//        get(IConnectionHandlerStrategy.class).setSharedFileModel(sharedFileModel);
    }
//    private void buildGameMVC() {
//        GameController gameController = new GameController();
//        GameView gameView = new GameView(gameController);
//        GameModel gameModel = new GameModel(gameController);
//
//        gameController.setView(gameView);
//        gameController.setModel(gameModel);
//        gameController.initializeList();
//        dependencyMap.put(GameView.class, gameView);
//        dependencyMap.put(GameModel.class, gameModel);
////        get(IConnectionHandlerStrategy.class).setGameModel(gameModel);

//    }

//    private void buildChatMVC() {
//        ChatController chatController = new ChatController();
//        ChatView chatView = new ChatView(chatController);
//        ChatModel chatModel = new ChatModel(chatController);
//
//        chatController.setView(chatView);
//        chatController.setModel(chatModel);
//        dependencyMap.put(ChatView.class, chatView);
//        dependencyMap.put(ChatModel.class, chatModel);
//    }

    private void buildMainMVC() {
        MainController mainController = new MainController();
        MainView mainView = new MainView(mainController);
        MainModel mainModel = new MainModel(mainController);

        mainController.setView(mainView);
        mainController.setModel(mainModel);
        dependencyMap.put(MainView.class, mainView);
        dependencyMap.put(MainModel.class, mainModel);
        get(DialogUtil.class).setMainView(mainView);
//        get(BoardView.class).setMainView(mainView);
        get(SharedFileView.class).setMainView(mainView);
        get(DownloadedFileView.class).setMainView(mainView);
//        get(GameView.class).setMainView(mainView);
        get(ConnectionView.class).setMainView(mainView);
//        get(ChatView.class).setMainView(mainView);
////        get(IConnectionStrategy.class).setMainModel(mainModel);
//        //TODO talvez fazer o set de cada model que foi colocado lá...
    }

    public <T> T get(Class<T> classType){
        Object dependency = dependencyMap.get(classType);

        if(dependency == null)
            throw new RuntimeException("The dependency is null! Type: " + classType);

        return (T) dependency;
    }

//    public void setCommunicationStrategyFactory(ICommunicationStrategy communicationStrategyFactory) {
//        this.communicationStrategyFactory = communicationStrategyFactory;
//    }
}
