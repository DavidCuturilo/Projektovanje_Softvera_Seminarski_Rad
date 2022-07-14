/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.Account;
import model.Member;
import model.Performance;
import model.Reservation;
import model.ReservationItem;
import model.TheatricalPlay;
import so.AbstractSO;
import so.AddTheatricalPlaySO;
import so.GetAllPerformancesSO;
import so.GetAllReservationItemsForReservationSO;
import so.GetAllReservationItemsForUserSO;
import so.GetAllReservationsForMembersSO;
import so.GetAllReservationsForSelectedMemberSO;
import so.GetMaxIndexSO;
import so.GetTheatricalPlayForTitleSO;
import so.LoginSO;
import so.RemoveReservationItemSO;
import so.RemoveReservationSO;
import so.SavePerformanceSO;
import so.SaveReservationItemsSO;
import so.SaveReservationSO;
import so.SaveTheatricalPlaySO;

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
    
    public List<ReservationItem> getAllReservationItemsForUser(Long userId) throws Exception {
        AbstractSO getAllReservationItemsForUserSO = new GetAllReservationItemsForUserSO();
        List<ReservationItem> reservationItems = (List<ReservationItem>) getAllReservationItemsForUserSO.execute(userId);
        return reservationItems;
    }
    
    public void removeReservationItem(ReservationItem reservationItem) throws Exception{
        AbstractSO removeReservationItemSO = new RemoveReservationItemSO();
        removeReservationItemSO.execute(reservationItem);
    }
    
    public List<ReservationItem> getAllReservationItemsForReservation(Long reservationId) throws Exception {
        AbstractSO getAllReservationItemsForReservationSO = new GetAllReservationItemsForReservationSO();
        List<ReservationItem> reservationItems = (List<ReservationItem>) getAllReservationItemsForReservationSO.execute(reservationId);
        return reservationItems;
    }
    
    public void removeReservation(Reservation reservation) throws Exception{
        AbstractSO removeReservationSO = new RemoveReservationSO();
        removeReservationSO.execute(reservation);
    }
    
    public Long saveTheatricalPlay(TheatricalPlay theatricalPlay) throws Exception {
        AbstractSO saveTheatricalPlaySO = new SaveTheatricalPlaySO();
        Long id = (Long)saveTheatricalPlaySO.execute(theatricalPlay);
        return id;
    }
    
    public void savePerformance(Performance performance) throws Exception{
        AbstractSO savePerformanceSO = new SavePerformanceSO();
        savePerformanceSO.execute(performance);
    }
    
    public TheatricalPlay getTheatricalPlayForTitle(String title) throws Exception{
        AbstractSO getTheatricalPlayForTitleSO = new GetTheatricalPlayForTitleSO();
        TheatricalPlay theatricalPlay = (TheatricalPlay) getTheatricalPlayForTitleSO.execute(title);
        return theatricalPlay;
    }
    
    public List<Reservation> getAllReservationsForMembers() throws Exception {
        AbstractSO getAllReservationsForMembersSO = new GetAllReservationsForMembersSO();
        List<Reservation> reservations = (List<Reservation>) getAllReservationsForMembersSO.execute(null);
        return reservations;
    }
    
    public List<Reservation> getAllReservationsForSelectedMember(String imePrezime) throws Exception {
        AbstractSO getAllReservationsForSelectedMemberSO = new GetAllReservationsForSelectedMemberSO();
        List<Reservation> reservations = (List<Reservation>) getAllReservationsForSelectedMemberSO.execute(imePrezime);
        return reservations;
    }
    
    public void getTheatricalPLay(TheatricalPlay theatricalPlay) throws Exception{
        AbstractSO addTheatricalPlaySO = new AddTheatricalPlaySO();
        addTheatricalPlaySO.execute(theatricalPlay);
    }
}
