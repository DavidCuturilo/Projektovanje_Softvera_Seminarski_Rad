/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import model.ReservationItem;
import persistence.db.repository.ReservationItemRepository;
import validation.Validator;

/**
 *
 * @author David
 */
public class RemoveReservationItemSO extends AbstractSO {
    private final ReservationItemRepository reservationItemRepository;

    public RemoveReservationItemSO() {
        reservationItemRepository = new ReservationItemRepository();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof ReservationItem)) {
            throw new Exception("Parametar mora biti instanca ReservationItem");
        }
        ReservationItem reservationItem = (ReservationItem) param;
        
        try {
            Validator.startValidation()
                    .validateNotNull(reservationItem, "ReservationItem ne sme biti null!.");
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        ReservationItem reservationItem = (ReservationItem) param;
        this.reservationItemRepository.delete(reservationItem);
        return null;
    }
    
}
