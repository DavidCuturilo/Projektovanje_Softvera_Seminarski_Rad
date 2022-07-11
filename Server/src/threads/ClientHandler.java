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
import model.Account;
import model.Performance;
import model.Reservation;
import model.ReservationItem;

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
    private Account userAccount;
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
        switch (request.getOperation()) {
            case LOGIN:
                return this.login(request);
            case GET_ALL_PERFORMANCES:
                return this.getAllPerformances();
            case SAVE_RESERVATION:
                return this.saveReservation(request);
            case SAVE_RESERVATION_ITEMS:
                return this.saveReservationItems(request);
            case GET_MAX_INDEX:
                return this.getMaxIndex();
            default:
                return new Response(ResponseType.ERROR, null, new UnsupportedOperationException());
        }
    }
    
    private Response login(Request request) {
        Response response;
        try {

            Account requestMember = (Account) request.getData();
            Account memberAccount = this.controller.login(requestMember.getUsername(), requestMember.getPassword());
            for (ClientHandler ch : this.serverThread.getClients()) {
                if (ch!=this && ch.getUserAccount()!=null && ch.getUserAccount().getId() == memberAccount.getId()) {
                    throw new Exception("Korisnik je već ulogovan");
                }
            }
            response = new Response(ResponseType.SUCCESS, memberAccount, null);
            this.userAccount = memberAccount;
            this.loginTime = new Date();
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new Response(ResponseType.ERROR, null, ex);
        }
        return response;
    }
    
    private Response getAllPerformances() {
        Response response;
        try {
            List<Performance> performances = this.controller.getAllPerformances();
            response = new Response(ResponseType.SUCCESS, performances, null);
        } catch (Exception e) {
            response = new Response(ResponseType.ERROR, null, e);
        }
        return response;
    }
    
    private Response saveReservation(Request request){
        Response response;
        try {
            Reservation reservation = (Reservation) request.getData();
            controller.saveReservation(reservation);
            response = new Response(ResponseType.SUCCESS, null, null);

        } catch (Exception e) {
            response = new Response(ResponseType.ERROR, null, e);
        }
        return response;
    }
    
    public Response saveReservationItems(Request request){
        Response response;
        try {
            List<ReservationItem> reservationItems = (List<ReservationItem>) request.getData();
            controller.saveReservationItems(reservationItems);
            response = new Response(ResponseType.SUCCESS, null, null);

        } catch (Exception e) {
            response = new Response(ResponseType.ERROR, null, e);
        }
        return response;
    }
    
    public Response getMaxIndex(){
        Response response;
        try {
            Long getMaxIndex = this.controller.getMaxIndex();
            response = new Response(ResponseType.SUCCESS, getMaxIndex, null);
        } catch (Exception e) {
            response = new Response(ResponseType.ERROR, null, e);
        }
        return response;
        
    }
    
    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
    
    void sendDisconnectEvent() {
        try {
            this.out.writeObject(new Request(Operation.DISCONNECT, null));
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
