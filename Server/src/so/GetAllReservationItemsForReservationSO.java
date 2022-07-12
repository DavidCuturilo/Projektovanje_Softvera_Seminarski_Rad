/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import java.util.List;
import model.ReservationItem;
import persistence.db.repository.ReservationItemRepository;
import validation.Validator;

/**
 *
 * @author David
 */
public class GetAllReservationItemsForReservationSO extends AbstractSO {
    private final ReservationItemRepository reservationItemRepository;

    public GetAllReservationItemsForReservationSO() {
        this.reservationItemRepository = new ReservationItemRepository();
    }
    
    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Long)) {
            throw new Exception("Parametar mora biti tipa Long");
        }
        Long userId = (Long) param;
        
        try {
            Validator.startValidation()
                    .validateID(userId, "Reservation id mora biti validan (pozitivan broj)!.");
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        Long reservationId = (Long) param;
        List<ReservationItem> reservationItems = this.reservationItemRepository.getAllItemsForReservation(reservationId);
        if(reservationItems == null)  throw new Exception("Nevalidan id");
        return reservationItems;
    }
    
}
