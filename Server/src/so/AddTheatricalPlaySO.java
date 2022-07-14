/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import model.TheatricalPlay;
import persistence.db.repository.TheatricalPlayRepository;

/**
 *
 * @author David
 */
public class AddTheatricalPlaySO extends AbstractSO {
    private final TheatricalPlayRepository theatricalPlayRepository;

    public AddTheatricalPlaySO() {
        System.out.println("Inicijalizujem repo");
        this.theatricalPlayRepository = new TheatricalPlayRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        TheatricalPlay theatricalPlay = (TheatricalPlay)param;
        this.theatricalPlayRepository.add(theatricalPlay);
        return null;
    }
    
}
