/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.Account;
import model.Performance;
import model.Reservation;
import model.ReservationItem;
import so.AbstractSO;
import so.GetAllPerformancesSO;
import so.GetMaxIndexSO;
import so.LoginSO;
import so.SaveReservationItemsSO;
import so.SaveReservationSO;

/**
 *
 * @author David
 */
public class Controller {

    public Controller() {
    }
    
    public Account login(String username, String password) throws Exception {
        Account account = new Account(username, password);
        AbstractSO loginSo = new LoginSO();
        Account loggedInAccount = (Account) loginSo.execute(account);
        return loggedInAccount;
    }
    
    public List<Performance> getAllPerformances() throws Exception {
        AbstractSO getAllPerformancesSO = new GetAllPerformancesSO();
        List<Performance> performances = (List<Performance>) getAllPerformancesSO.execute(null);
        return performances;
    }
    
    public void saveReservation(Reservation reservation) throws Exception {
        AbstractSO saveReservationSO = new SaveReservationSO();
        saveReservationSO.execute(reservation);
    }
    
    public void saveReservationItems(List<ReservationItem> reservationItems) throws Exception{
        AbstractSO saveReservationItemsSO = new SaveReservationItemsSO();
        saveReservationItemsSO.execute(reservationItems);
    }
    
    public Long getMaxIndex() throws Exception{
        AbstractSO getMaxIndexSO = new GetMaxIndexSO();
        Long maxIndex = (Long)getMaxIndexSO.execute(null);
        return maxIndex;
    }
}
