package com.phcarvalho.model.configuration;

import com.phcarvalho.model.corba.User;
import com.phcarvalho.model.corba.client.ClientService;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private static Configuration singleton;

    public static Configuration getSingleton(){

        if(singleton == null)
            singleton = new Configuration();

        return singleton;
    }

    private User localUser;
    private User remoteUser;
    private boolean serverConnected;
    private Map<String, ClientService> clientServiceMap;

    private Configuration() {
        serverConnected = false;
        clientServiceMap = new HashMap<>();
    }

    public String getId(){
        return localUser.name + "::" + localUser.host + "::" + localUser.port;
    }

    public String getServerId(){
        return remoteUser.name + "::" + remoteUser.host + "::" + remoteUser.port;
    }

    public ClientService getClientService(String id){
        return clientServiceMap.get(id);
    }

    public void putClientService(String id, ClientService clientService){
        clientServiceMap.put(id, clientService);
    }

    public User getLocalUser() {

//        if(localUser == null)
//            throw new RuntimeException("The localUser field is null!");

        return localUser;
    }

    public void setLocalUser(User localUser) {
        this.localUser = localUser;
    }

    public User getRemoteUser() {

        if(remoteUser == null)
            throw new RuntimeException("The remoteUser field is null!");

        return remoteUser;
    }

    public void setRemoteUser(User remoteUser) {
        this.remoteUser = remoteUser;
    }

    public boolean isServerConnected() {
        return serverConnected;
    }

    public void setServerConnected(boolean serverConnected) {
        this.serverConnected = serverConnected;
    }
}
