package com.phcarvalho.view;

import com.phcarvalho.controller.ConnectionController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.corba.User;
import com.phcarvalho.view.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
    private JLabel userNameLabel;
    private JLabel userNameValueLabel;
    private JLabel localHostLabel;
    private JLabel localHostValueLabel;

    public ConnectionView(ConnectionController controller) {
        super(new GridBagLayout());
        this.controller = controller;
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
        connectToServerButton = new JButton(CONNECT_TO_SERVER);
        userNameLabel = new JLabel("User name:");
        userNameValueLabel = new JLabel(EMPTY_LABEL);
        localHostLabel = new JLabel("Host:");
        localHostValueLabel = new JLabel(EMPTY_LABEL);
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
        add(userNameLabel, gridBagConstraints);

        userNameValueLabel.setPreferredSize(new Dimension(200, 40));
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(userNameValueLabel, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(localHostLabel, gridBagConstraints);

        localHostValueLabel.setPreferredSize(new Dimension(200, 40));
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(localHostValueLabel, gridBagConstraints);
    }

    private void connectToServer(){
        String userName = getUserName();

        if(userName != null) {
            User localUser = buildLocalUser(userName);
            User remoteUser = new User("SERVER");

            controller.connectToServer(localUser, remoteUser);
            connectToServerByCallback();
        }
    }

    private String getUserName() {
        String userName = dialogUtil.showInput("What is the user name?", CONNECT_TO_SERVER);

        if(userName == null)
            return null;

        if(userName.isEmpty())
            return DEFAULT_USER_NAME + new Random().nextInt();

        return userName;
    }

    private User buildLocalUser(String userId) {
        return new User(userId);
    }

    public void connectToServerByCallback() {
        User localUser = Configuration.getSingleton().getLocalUser();

        userNameValueLabel.setText(localUser.id);
        connectToServerButton.setEnabled(false);
        mainView.getSharedFileView().getSelectGameButton().setEnabled(true);
        mainView.getSharedFileView().getMessageTextField().setEnabled(true);
        mainView.getSharedFileView().getSelectGameButton().setEnabled(true);
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
        userNameValueLabel.setText(EMPTY_LABEL);
        localHostValueLabel.setText(EMPTY_LABEL);
    }

    public void selectSharedDirectory() {
        mainView.getSharedDirectoryPathView().selectSharedDirectory();
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
}
