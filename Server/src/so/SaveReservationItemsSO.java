/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import java.util.List;
import model.ReservationItem;
import persistence.db.repository.ReservationItemRepository;

/**
 *
 * @author David
 */
public class SaveReservationItemsSO extends AbstractSO {
    private final ReservationItemRepository reservationItemRepository;

    public SaveReservationItemsSO() {
        this.reservationItemRepository = new ReservationItemRepository();
    }
    
    
    @Override
    protected void precondition(Object param) throws Exception {
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        List<ReservationItem> reservationItems = (List<ReservationItem>) param;
        this.reservationItemRepository.addAll(reservationItems);
        return null;
    }
    
}
