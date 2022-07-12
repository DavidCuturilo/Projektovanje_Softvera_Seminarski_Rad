/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import model.Reservation;
import persistence.db.repository.ReservationRepository;
import validation.Validator;

/**
 *
 * @author David
 */
public class RemoveReservationSO extends AbstractSO {
    private final ReservationRepository reservationRepository;

    public RemoveReservationSO() {
        this.reservationRepository = new ReservationRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Reservation)) {
            throw new Exception("Parametar mora biti instanca ReservationItem");
        }
        Reservation reservation = (Reservation) param;
        
        try {
            Validator.startValidation()
                    .validateNotNull(reservation, "Reservation ne sme biti null!.");
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        Reservation reservation = (Reservation) param;
        this.reservationRepository.delete(reservation);
        return null;
    }
    
}
