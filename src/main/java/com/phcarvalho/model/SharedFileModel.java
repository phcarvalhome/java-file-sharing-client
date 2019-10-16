package com.phcarvalho.model;

import com.phcarvalho.controller.SharedFileController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.corba.FileData;
import com.phcarvalho.model.corba.FileMetadata;
import com.phcarvalho.model.corba.User;
import com.phcarvalho.model.corba.client.ClientService;
import com.phcarvalho.model.corba.server.ServerService;
import com.phcarvalho.view.vo.FileDataAdapter;
import com.phcarvalho.view.vo.FileMetadataAdapter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

public class SharedFileModel {

    private SharedFileController controller;
    private DefaultListModel<FileMetadataAdapter> list;
    private ConnectionModel connectionModel;
    private DownloadedFileModel downloadedFileModel;

    public SharedFileModel(SharedFileController controller) {
        this.controller = controller;
        list = new DefaultListModel();
        connectionModel = DependencyFactory.getSingleton().get(ConnectionModel.class);
        downloadedFileModel = DependencyFactory.getSingleton().get(DownloadedFileModel.class);
    }

    public void searchFile(String text) {
        ServerService serverService = connectionModel.getServerService();
        FileMetadata[] fileMetadataArray = serverService.search(text);
        User localUser = Configuration.getSingleton().getLocalUser();

        Arrays.stream(fileMetadataArray)
                .filter(fileMetadata -> !fileMetadata.user.id.equals(localUser.id))
                .forEach(fileMetadata -> list.addElement(new FileMetadataAdapter(fileMetadata)));
    }

    public FileData download(FileMetadata fileMetadata){
        String id = fileMetadata.user.id;
        ClientService clientService = connectionModel.getClientService(id);
        FileData fileData = clientService.downloadFileData(fileMetadata);
        byte[] encodedBytes = Base64.getDecoder().decode(fileData.content.getBytes());
        String sharedDirectoryPath = Configuration.getSingleton().getSharedDirectoryPath();

        try (FileOutputStream fileOutputStream = new FileOutputStream(sharedDirectoryPath + "\\" + fileData.metadata.name)) {
            fileOutputStream.write(encodedBytes);
            downloadedFileModel.getList().addElement(new FileDataAdapter(fileData));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileData;
    }

    public DefaultListModel<FileMetadataAdapter> getList() {
        return list;
    }
}
