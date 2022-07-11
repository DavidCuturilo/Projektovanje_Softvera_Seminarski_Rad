/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import exception.ValidationException;
import model.Account;
import persistence.db.repository.AccountRepository;
import validation.Validator;

/**
 *
 * @author David
 */
public class CreateAccountSO extends AbstractSO{

    private final AccountRepository accountRepository;

    public CreateAccountSO() {
        this.accountRepository = new AccountRepository();
    }
    
    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Account)) {
            throw new Exception("Parametar mora biti instanca klase Account");
        }
        Account account = (Account) param;
        
        try {
            Validator.startValidation()
                    .validateNotNullOrEmpty(account.getUsername(), "Korisničko ime ne sme biti prazno")
                    .validateNotNullOrEmpty(account.getPassword(), "Šifra ne sme biti prazna.")
                    .validateNotNull(account.getMember(), "Objekat zaposleni ne sme biti null").throwIfInvalide();
        } catch (ValidationException e) {
            throw e;
        }
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        this.accountRepository.add((Account)param);
        return null;
    }
}
