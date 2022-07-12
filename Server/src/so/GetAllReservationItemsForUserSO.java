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
public class GetAllReservationItemsForUserSO extends AbstractSO{
    
    private final ReservationItemRepository reservationItemRepository;

    public GetAllReservationItemsForUserSO() {
        reservationItemRepository = new ReservationItemRepository();
    }
    
    

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Long)) {
            throw new Exception("Parametar mora biti tipa Long");
        }
        Long userId = (Long) param;
        
        try {
            Validator.startValidation()
                    .validateID(userId, "User id mora biti validan (pozitivan broj)!.");
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        Long userId = (Long) param;
        List<ReservationItem> reservationItems = this.reservationItemRepository.getAllItemsForUser(userId);
        if(reservationItems == null)  throw new Exception("Nevalidan id");
        return reservationItems;
    }
    
}
