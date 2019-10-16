package com.phcarvalho.model.service;

import com.phcarvalho.model.corba.FileData;
import com.phcarvalho.model.corba.FileMetadata;
import com.phcarvalho.model.corba.client.ClientServicePOA;
import com.phcarvalho.model.util.FileUtil;

public class ClientService extends ClientServicePOA {

    @Override
    public FileData downloadFileData(FileMetadata fileMetadata) {
        return FileUtil.readLocalFile(fileMetadata);
    }
}
