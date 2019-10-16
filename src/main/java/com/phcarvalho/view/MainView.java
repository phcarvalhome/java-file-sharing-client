package com.phcarvalho.view;

import com.phcarvalho.controller.MainController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.view.listener.MainWindowAdapter;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private static final String TITLE = "File Sharing - Client";

    private MainController controller;
    private JPanel mainPanel;
    private JPanel centerPanel;
    private SharedDirectoryPathView sharedDirectoryPathView;
    private SharedFileView sharedFileView;
    private DownloadedFileView downloadedFileView;
    private ConnectionView connectionView;

    public MainView(MainController controller) {
        this.controller = controller;
        mainPanel = new JPanel(new GridBagLayout());
        centerPanel = new JPanel(new GridBagLayout());
        sharedDirectoryPathView = new SharedDirectoryPathView();
        sharedFileView = DependencyFactory.getSingleton().get(SharedFileView.class);
        downloadedFileView = DependencyFactory.getSingleton().get(DownloadedFileView.class);
        connectionView = DependencyFactory.getSingleton().get(ConnectionView.class);
        initialize();
    }

    private void initialize() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        mainPanel.add(sharedDirectoryPathView, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        mainPanel.add(centerPanel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        centerPanel.add(sharedFileView, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        centerPanel.add(downloadedFileView, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        mainPanel.add(connectionView, gridBagConstraints);

        addWindowListener(new MainWindowAdapter(() -> disconnect()));
        setTitle(TITLE);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private void disconnect() {
        connectionView.disconnect();
    }

    public SharedDirectoryPathView getSharedDirectoryPathView() {
        return sharedDirectoryPathView;
    }

    public SharedFileView getSharedFileView() {
        return sharedFileView;
    }

    public DownloadedFileView getDownloadedFileView() {
        return downloadedFileView;
    }

    public ConnectionView getConnectionView() {
        return connectionView;
    }
}
