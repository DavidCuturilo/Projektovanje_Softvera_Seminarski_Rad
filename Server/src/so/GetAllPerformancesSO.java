/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import persistence.db.repository.PerformanceRepository;

/**
 *
 * @author David
 */
public class GetAllPerformancesSO extends AbstractSO {
    private final PerformanceRepository performanceRepository;

    public GetAllPerformancesSO() {
        this.performanceRepository = new PerformanceRepository();
    }
    
    @Override
    protected void precondition(Object param) throws Exception {

    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        return this.performanceRepository.getAll();
    }
    
}
