/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.db;

import persistence.Repository;

/**
 *
 * @author David
 */
public interface DBRepository<T, K> extends Repository<T, K> {

    default public void connect() throws Exception {
        DBConnectionFactory.getInstance().getConnection();
    }

    default public void disconnect() throws Exception {
        DBConnectionFactory.getInstance().getConnection().close();
    }
    
    default public void startTransaction() throws Exception {
        DBConnectionFactory.getInstance().getConnection().commit();
    }

    default public void commit() throws Exception {
        DBConnectionFactory.getInstance().getConnection().commit();
    }

    default public void rollback() throws Exception {
        DBConnectionFactory.getInstance().getConnection().rollback();
    }
}
