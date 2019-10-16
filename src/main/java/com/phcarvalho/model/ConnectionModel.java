package com.phcarvalho.model;

import com.phcarvalho.controller.ConnectionController;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.corba.FileMetadata;
import com.phcarvalho.model.corba.User;
import com.phcarvalho.model.corba.client.ClientServiceHelper;
import com.phcarvalho.model.corba.server.ServerService;
import com.phcarvalho.model.corba.server.ServerServiceHelper;
import com.phcarvalho.model.service.ClientService;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ConnectionModel {

    private ConnectionController controller;
    private ORB orb;
    private POA rootPOA;
    private NamingContext namingContext;

    public ConnectionModel(ConnectionController controller) {
        this.controller = controller;
    }

    public void connectToServer(User localUser, User remoteUser) {
        Configuration.getSingleton().setLocalUser(localUser);
        Configuration.getSingleton().setRemoteUser(remoteUser);

        orb = ORB.init(/*args*/ new String[]{"[-ORBInitialHost" + " Host" + "]"}, null);

        try {
            org.omg.CORBA.Object rootPOAObject = orb.resolve_initial_references("RootPOA");
            rootPOA = POAHelper.narrow(rootPOAObject);
            org.omg.CORBA.Object namingContextObject = orb.resolve_initial_references("NameService") ;
            namingContext = NamingContextHelper.narrow(namingContextObject);
            serve(); //TODO isso fica aqui mesmo?
            connectToServer();
        } catch (org.omg.CORBA.ORBPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
        }
    }

    public void serve(){
        ClientService clientService = new ClientService();
        String serviceName = Configuration.getSingleton().getLocalUser().id;

        try {
            org.omg.CORBA.Object serviceObject = rootPOA.servant_to_reference(clientService);
            NameComponent nameComponent = new NameComponent(serviceName, "Instance");
            NameComponent[] nameComponentArray = {nameComponent};

            namingContext.rebind(nameComponentArray, serviceObject);
            rootPOA.the_POAManager().activate();
            Executors.newSingleThreadExecutor().execute(() -> orb.run());
        } catch (WrongPolicy wrongPolicy) {
            wrongPolicy.printStackTrace();
        } catch (AdapterInactive adapterInactive) {
            adapterInactive.printStackTrace();
        } catch (ServantNotActive servantNotActive) {
            servantNotActive.printStackTrace();
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        }
    }

    public void connectToServer(){

        if(".".equals(Configuration.getSingleton().getSharedDirectoryPath()))
            controller.selectSharedDirectory();

        ServerService serverService = getServerService();
        User localUser = Configuration.getSingleton().getLocalUser();

        try {
            Path sharedDirectoryPath = Paths.get(Configuration.getSingleton().getSharedDirectoryPath());
            FileMetadata[] fileMetadataArray = Files.list(sharedDirectoryPath)
                    .filter(Files::isRegularFile)
                    .map(path -> new FileMetadata(localUser, path.getFileName().toString()))
                    .collect(Collectors.toList()).toArray(new FileMetadata[]{});

            serverService.connectToServer(fileMetadataArray);
            Configuration.getSingleton().setServerConnected(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerService getServerService() {
        String id = Configuration.getSingleton().getRemoteUser().id;
        NameComponent[] nameComponentArray = {new NameComponent(id, "Instance")};

        try {
            org.omg.CORBA.Object serverServiceObject = namingContext.resolve(nameComponentArray);

            return ServerServiceHelper.narrow(serverServiceObject);
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }

        throw new RuntimeException("getServerService");
    }

    public com.phcarvalho.model.corba.client.ClientService getClientService(String id) {
        com.phcarvalho.model.corba.client.ClientService clientService = Configuration.getSingleton()
                .getClientService(id);

        if(clientService != null)
            return clientService;

        NameComponent[] nameComponentArray = {new NameComponent(id, "Instance")};

        try {
            org.omg.CORBA.Object serverServiceObject = namingContext.resolve(nameComponentArray);

            clientService = ClientServiceHelper.narrow(serverServiceObject);
            Configuration.getSingleton().putClientService("", clientService);

            return clientService;
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }

        throw new RuntimeException("...");
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
}
