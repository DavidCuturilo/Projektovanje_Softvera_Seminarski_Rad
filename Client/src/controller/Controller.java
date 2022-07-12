/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.Communication;
import communication.Operation;
import communication.Request;
import communication.Response;
import communication.ResponseType;
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
public class Controller {
    
    private static Controller instance;
    private Account userAccount;
    private List<Performance> performances;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void login(Account account) throws Exception {
        Request request = new Request(Operation.LOGIN, account);
        try {
            Response response = Communication.getInstance().send(request);
            
            if(response.getResponseType() == ResponseType.SUCCESS){
                this.userAccount = (Account) response.getData();
            }else{
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, response.getError());
                throw response.getError();
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public List<Performance> getAllPerformances() throws Exception {
        Request request = new Request(Operation.GET_ALL_PERFORMANCES, null);
        try {
            Response response = Communication.getInstance().send(request);
            
            if(response.getResponseType() == ResponseType.SUCCESS){
                this.performances =  (List<Performance>) response.getData();
            }else{
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, response.getError());
                throw response.getError();
            }
            return performances;
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public void saveReservation(Reservation reservation) throws Exception{
        Request request = new Request(Operation.SAVE_RESERVATION, reservation);
        try {
            Response response = Communication.getInstance().send(request);
            
            if(response.getResponseType() == ResponseType.SUCCESS){
                System.out.println("Uspesno sacuvana rezervacija");
            }else{
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, response.getError());
                throw response.getError();
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public void saveAllReservationItems(List<ReservationItem> reservationItems) throws Exception{
        Request request = new Request(Operation.SAVE_RESERVATION_ITEMS, reservationItems);
        try {
            Response response = Communication.getInstance().send(request);
            
            if(response.getResponseType() == ResponseType.SUCCESS){
                System.out.println("Uspesno sacuvane stavke rezervacije");
            }else{
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, response.getError());
                throw response.getError();
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public Long getMaxIndex() throws Exception{
        Request request = new Request(Operation.GET_MAX_INDEX, null);
        Long index;
        try {
            Response response = Communication.getInstance().send(request);
            
            if(response.getResponseType() == ResponseType.SUCCESS){
                index = (Long) response.getData();
            }else{
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, response.getError());
                throw response.getError();
            }
            return index;
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public List<ReservationItem> getAllReservationItemsForUser(Long id) throws Exception{
        Request request = new Request(Operation.GET_ALL_RESERVATION_ITEMS, id);
        List<ReservationItem> items;
        try {
            Response response = Communication.getInstance().send(request);
            
            if(response.getResponseType() == ResponseType.SUCCESS){
                items =  (List<ReservationItem>) response.getData();
            }else{
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, response.getError());
                throw response.getError();
            }
            return items;
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public void removeReservationItem(ReservationItem reservationItem) throws Exception{
        Request request = new Request(Operation.REMOVE_RESERVATION_ITEM, reservationItem);
        try {
            Response response = Communication.getInstance().send(request);
            
            if(response.getResponseType() == ResponseType.SUCCESS){
                System.out.println("Uspesno obrisana stavke rezervacije");
            }else{
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, response.getError());
                throw response.getError();
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public List<ReservationItem> getAllReservationItemsForReservation(Long reservationId) throws Exception{
        Request request = new Request(Operation.GET_ALL_RESERVATION_ITEMS_FOR_RESERVATION, reservationId);
        List<ReservationItem> items;
        try {
            Response response = Communication.getInstance().send(request);
            
            if(response.getResponseType() == ResponseType.SUCCESS){
                items =  (List<ReservationItem>) response.getData();
            }else{
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, response.getError());
                throw response.getError();
            }
            return items;
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    } 
    
    public void removeReservation(Reservation reservation) throws Exception{
        Request request = new Request(Operation.REMOVE_RESERVATION, reservation);
        try {
            Response response = Communication.getInstance().send(request);
            
            if(response.getResponseType() == ResponseType.SUCCESS){
                System.out.println("Uspesno obrisana rezervacija");
            }else{
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, response.getError());
                throw response.getError();
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

}
