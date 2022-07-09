/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import controller.Controller;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.db.DBConnectionFactory;
import main.ServerEnvVariables;
import view.FormMain;

/**
 *
 * @author David
 */
public class ServerThread extends Thread {

    private ServerSocket serverSocket;
    private List<ClientHandler> clients;
    private Controller controller;
    private FormMain mainForm;
    public ServerThread(Controller controller, FormMain mainForm) throws IOException, SQLException {
        this.clients = new ArrayList<>();
        this.controller = controller;
        this.mainForm = mainForm;
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(ServerEnvVariables.CONFIG_FILE_PATH));
            String port = properties.getProperty(ServerEnvVariables.PORT);
            this.serverSocket = new ServerSocket(Integer.parseInt(port));
            System.out.println("Server started on port " + port);
            
            DBConnectionFactory.getInstance().getConnection();
            DBConnectionFactory.getInstance().getConnection().close();
            System.out.println("Database connection successful");
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    @Override
    public void run() {
        while (!this.serverSocket.isClosed()) {
            try {
                Socket socket = this.serverSocket.accept();
                ClientHandler ch = new ClientHandler(socket, this, controller);
                System.out.println("Novi klijent se povezao");
                ch.start();
                clients.add(ch);
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<ClientHandler> getClients() {
        return clients;
    }

    public void disconnectClients() {
        for(ClientHandler ch : this.clients){
            ch.sendDisconnectEvent();
        }
    }
}
