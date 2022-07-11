/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import exception.ValidationException;
import model.Reservation;
import persistence.db.repository.ReservationRepository;
import validation.Validator;

/**
 *
 * @author David
 */
public class SaveReservationSO extends AbstractSO{
    
    private final ReservationRepository reservationRepository;

    public SaveReservationSO() {
        this.reservationRepository = new ReservationRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Reservation)) {
            throw new Exception("Parametar mora biti instanca klase Reservation");
        }
        Reservation reservation = (Reservation) param;
        
        try {
            Validator.startValidation()
                    .validateDate(reservation.getDateOfReservation(), "Datum rezervacije je obavezan.");
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        Reservation reservation = (Reservation) param;
        this.reservationRepository.add(reservation);
        return null;
    }
    
}
