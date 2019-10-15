package com.phcarvalho.model.service;

import com.phcarvalho.model.corba.SharedFile;
import com.phcarvalho.model.corba.client.ClientServicePOA;

public class ClientService extends ClientServicePOA {

    @Override
    public SharedFile downloadSharedFile(String name) {
        return null;
    }
}
