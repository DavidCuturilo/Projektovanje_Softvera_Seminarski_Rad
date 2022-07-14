/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import model.Performance;
import persistence.db.repository.PerformanceRepository;
import validation.Validator;

/**
 *
 * @author David
 */
public class SavePerformanceSO extends AbstractSO {
    private final PerformanceRepository performanceRepository;

    public SavePerformanceSO() {
        this.performanceRepository = new PerformanceRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Performance)) {
            throw new Exception("Parametar mora biti instanca klase Performance");
        }
        Performance performance = (Performance) param;
        
        try {
            Validator.startValidation()
                    .validateNotNull(performance.getPerformanceDate(), "Datum izvodjenja predstave je obavezno polje, ne sme biti prazno!.");
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        Performance performance = (Performance) param;
        this.performanceRepository.add(performance);
        return null;
    }
    
    
}
