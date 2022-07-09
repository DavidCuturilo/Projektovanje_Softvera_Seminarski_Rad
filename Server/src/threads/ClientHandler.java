/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import communication.Operation;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class ClientHandler extends Thread {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ServerThread serverThread;
    private Controller controller;
//    private Account userAccount;
    private Date loginTime;
    ClientHandler(Socket socket, ServerThread serverThread, Controller controller) {

        try {
            this.socket = socket;
            this.serverThread = serverThread;
            this.controller = controller;
            this.out = new ObjectOutputStream(this.socket.getOutputStream());
            this.in = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (!this.socket.isClosed()) {
            try {
                Request request = (Request) this.in.readObject();
                Response response = handleRequest(request);
                if (response == null) {
                    continue;
                }

                this.out.writeObject(response);
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    this.socket.close();
                    this.serverThread.getClients().remove(this);
                } catch (IOException ex1) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.serverThread.getClients().remove(this);
    }

    private Response handleRequest(Request request) {
        return null;
    }
    
    void sendDisconnectEvent() {
        try {
            this.out.writeObject(new Request(Operation.DISCONNECT, null));
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
