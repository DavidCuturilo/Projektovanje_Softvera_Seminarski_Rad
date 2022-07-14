/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import java.util.List;
import model.Member;
import model.Reservation;
import persistence.db.repository.MemberRepository;
import persistence.db.repository.ReservationRepository;

/**
 *
 * @author David
 */
public class GetAllReservationsForMembersSO extends AbstractSO {
    private final ReservationRepository reservationRepository;

    public GetAllReservationsForMembersSO() {
        this.reservationRepository = new ReservationRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        List<Reservation> reservations = this.reservationRepository.getAllReservationWithExtraDetails();
        return reservations;
    }
    
    
}
