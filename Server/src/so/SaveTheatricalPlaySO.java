/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import model.TheatricalPlay;
import persistence.db.repository.TheatricalPlayRepository;
import validation.Validator;

/**
 *
 * @author David
 */
public class SaveTheatricalPlaySO extends AbstractSO {
    private final TheatricalPlayRepository theatricalPlayRepository;

    public SaveTheatricalPlaySO() {
        this.theatricalPlayRepository = new TheatricalPlayRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof TheatricalPlay)) {
            throw new Exception("Parametar mora biti instanca klase TheatricalPlay");
        }
        TheatricalPlay theatricalPlay = (TheatricalPlay) param;
        
        try {
            Validator.startValidation()
                    .validateNotNullOrEmpty(theatricalPlay.getTitle(), "Naslov predstave je obavezno polje, ne sme biti prazno!.");
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        System.out.println("Stigao do pre zahteva");
        TheatricalPlay theatricalPlay = (TheatricalPlay) param;
        Long id = this.theatricalPlayRepository.addAndReturnId(theatricalPlay);
        return id;
    }
    
}
