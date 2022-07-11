/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import persistence.db.DBConnectionFactory;

/**
 *
 * @author David
 */
public abstract class AbstractSO {
    public Object execute(Object param) throws Exception {
        try {
            precondition(param);
            startTransaction();
            Object returnParam = executeTransaction(param);
            commitTransaction();
            System.out.println("Uspesno izvrsena operacija");
            return returnParam;
        } catch (Exception ex) {
            rollbackTransaction();
            System.out.println("Neuspesno izvrsena operacija");
            throw ex;
        }
    }

    protected abstract void precondition(Object param) throws Exception;

    protected void startTransaction() throws Exception {
        DBConnectionFactory.getInstance().getConnection().setAutoCommit(false);
    }

    protected void commitTransaction() throws Exception {
        DBConnectionFactory.getInstance().getConnection().commit();
        DBConnectionFactory.getInstance().getConnection().setAutoCommit(true);
    }

    protected void rollbackTransaction() throws Exception {
        DBConnectionFactory.getInstance().getConnection().rollback();
        DBConnectionFactory.getInstance().getConnection().setAutoCommit(true);
    }

    protected abstract Object executeTransaction(Object param) throws Exception;
}
