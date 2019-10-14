package com.phcarvalho.view;

import com.phcarvalho.controller.SharedFileController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.filesharing.SharedFile;
import com.phcarvalho.view.listener.SearchSharedFileKeyListener;
import com.phcarvalho.view.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SharedFileView extends JPanel {

    private static final String TITLE = "Shared File";
    private static final String EMPTY_LABEL = "-";
    private static final int WIDTH = 280;
    private static final int HEIGHT = 120;

    private SharedFileController controller;
    private MainView mainView;
    private DialogUtil dialogUtil;
    private JList<SharedFile> list;
//    private JPanel bottomPanel;
//    private JLabel turnPlayerLabel;
//    private JLabel turnPlayerValueLabel;
    private JTextField messageTextField;

    public SharedFileView(SharedFileController controller) {
        super(new GridBagLayout());
        this.controller = controller;
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
        list = new JList();
//        bottomPanel = new JPanel(new GridBagLayout());
//        turnPlayerLabel = new JLabel("Turn player:");
//        turnPlayerValueLabel = new JLabel(EMPTY_LABEL);
        messageTextField = new JTextField();
        initialize();
    }

    private void initialize() {
        TitledBorder titledBorder = new TitledBorder(TITLE);

        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBorder(titledBorder);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        list.setEnabled(false);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);

        JScrollPane scrollPane = new JScrollPane(list);

        scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(scrollPane, gridBagConstraints);

//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 1;
//        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
//        add(bottomPanel, gridBagConstraints);

//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
//        bottomPanel.add(turnPlayerLabel, gridBagConstraints);

//        turnPlayerValueLabel.setPreferredSize(new Dimension(140, 30));
//        gridBagConstraints.gridx = 1;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
//        bottomPanel.add(turnPlayerValueLabel, gridBagConstraints);

        messageTextField.setEnabled(false);
        messageTextField.addKeyListener(new SearchSharedFileKeyListener(() -> searchFile()));
        messageTextField.setPreferredSize(new Dimension(WIDTH, 30));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(messageTextField, gridBagConstraints);
    }

    private void searchFile() {
    }

//    public void add(Player player){
//        controller.add(player);
//    }
//
//    public void setTurnPlayer(Player turnPlayer) {
//        int turnPlayerIndex = controller.getPlayerIndex(turnPlayer);
//
//        list.setSelectedIndex(turnPlayerIndex);
//        list.setSelectionBackground(turnPlayer.getColor().getColor());
//        turnPlayerValueLabel.setText(turnPlayer.toString());
//    }
//
//    public void setReadyPlayer(Player readyPlayer) {
////        int readyPlayerIndex = controller.getPlayerIndex(readyPlayer);
////        Player player = list.getModel().getElementAt(readyPlayerIndex);
////
////        player.getUser().setName(READY_SYMBOL + player.getUser().getName());
////        list.repaint();
//    }
//
//    public void removeByCallback(Player player) {
//        String message = String.join("",
//                "The player ", player.getUser().getName(), " is DISCONNECTED!");
//
////        list.repaint();
//        mainView.getChatView().displaySystemMessage(message);
//    }
//
//    public void reset() {
//        controller.clear();
//        turnPlayerValueLabel.setText(EMPTY_LABEL);
//    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public JList getList() {
        return list;
    }
}
