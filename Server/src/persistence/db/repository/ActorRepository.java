/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import model.Actor;
import persistence.db.DBConnectionFactory;
import persistence.db.DBRepository;

/**
 *
 * @author David
 */
public class ActorRepository implements DBRepository<Actor, Long>{
    
    private Connection connection;
    
    @Override
    public List<Actor> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void add(Actor a) throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("INSERT INTO Actor(imePrezime,role,performanceId) VALUES(?,?,?)");
        ps.setString(1, a.getImePrezime());
        ps.setString(2, a.getRole());
        ps.setLong(3, a.getPerformance().getId());
        int affectedRows = ps.executeUpdate();

    }

    @Override
    public void edit(Actor t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Actor t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Actor getById(Long k) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
