package com.phcarvalho.view;

import com.phcarvalho.controller.ConnectionController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.corba.User;
import com.phcarvalho.view.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.Random;

public class ConnectionView extends JPanel {

    private static final String DEFAULT_USER_NAME = "Client ";
    private static final String CONNECT_TO_SERVER = "Connect To Server";
    private static final String EMPTY_LABEL = "-";

    private ConnectionController controller;
    private MainView mainView;
    private DialogUtil dialogUtil;
    private JButton connectToServerButton;
    private JLabel clientIdLabel;
    private JLabel clientIdValueLabel;

    public ConnectionView(ConnectionController controller) {
        super(new GridBagLayout());
        this.controller = controller;
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
        connectToServerButton = new JButton(CONNECT_TO_SERVER);
        clientIdLabel = new JLabel("Client ID:");
        clientIdValueLabel = new JLabel(EMPTY_LABEL);
        initialize();
    }

    private void initialize() {
        TitledBorder titledBorder = new TitledBorder("Connection");

        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBorder(titledBorder);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        connectToServerButton.addActionListener(actionEvent -> connectToServer());
        connectToServerButton.setPreferredSize(new Dimension(160, 30));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(connectToServerButton, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(clientIdLabel, gridBagConstraints);

        clientIdValueLabel.setPreferredSize(new Dimension(480, 40));
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(clientIdValueLabel, gridBagConstraints);
    }

    private void connectToServer(){
        String clientId = getClientId();

        if(clientId != null) {
            User localUser = buildLocalUser(clientId);
            User remoteUser = new User("SERVER");

            controller.connectToServer(localUser, remoteUser);
            connectToServerByCallback();
        }
    }

    private String getClientId() {
        String clientId = dialogUtil.showInput("What is the client ID?", CONNECT_TO_SERVER);

        if(clientId == null)
            return null;

        if(clientId.isEmpty())
            return DEFAULT_USER_NAME + new Random().nextInt();

        return clientId;
    }

    private User buildLocalUser(String clientId) {
        return new User(clientId);
    }

    public void connectToServerByCallback() {
        User localUser = Configuration.getSingleton().getLocalUser();

        clientIdValueLabel.setText(localUser.id);
        connectToServerButton.setEnabled(false);
        mainView.getSharedFileView().getSelectSharedFileButton().setEnabled(true);
        mainView.getSharedFileView().getSearchSharedFileField().setEnabled(true);
        mainView.getSharedFileView().getSelectSharedFileButton().setEnabled(true);
        mainView.getDownloadedFileView().getDownloadSharedFileButton().setEnabled(true);
//        dialogUtil.showInformation("The server is connected!", SERVER_CONNECTION);
    }

    public void disconnect() {

        try {
            controller.disconnect();
        } catch (RemoteException e) {
            //TODO handle it
            e.printStackTrace();
        }
    }

    public void clear() {
        clientIdValueLabel.setText(EMPTY_LABEL);
    }

    public void selectSharedDirectory() {
        mainView.getSharedDirectoryPathView().selectSharedDirectory();
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
}
