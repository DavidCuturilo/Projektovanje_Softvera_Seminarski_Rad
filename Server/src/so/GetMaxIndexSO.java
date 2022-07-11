/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import persistence.db.repository.ReservationRepository;

/**
 *
 * @author David
 */
public class GetMaxIndexSO extends AbstractSO {
    
    private final ReservationRepository reservationRepository;

    public GetMaxIndexSO() {
        this.reservationRepository = new ReservationRepository();
    }
    
    @Override
    protected void precondition(Object param) throws Exception {
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        return this.reservationRepository.getLastIndex();
    }
    
}
