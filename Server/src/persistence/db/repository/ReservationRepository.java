/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.db.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Reservation;
import persistence.db.DBConnectionFactory;
import persistence.db.DBRepository;

/**
 *
 * @author David
 */
public class ReservationRepository implements DBRepository<Reservation, Long>{
    
    private Connection connection;

    @Override
    public List<Reservation> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Reservation r) throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("INSERT INTO Reservation(id,dateOfReservation,deadline,accountId, memberId) VALUES(?,?,?,?,?)");
        ps.setLong(1, r.getId());
        ps.setDate(2, new java.sql.Date(r.getDateOfReservation().getTime()));
        ps.setDate(3, new java.sql.Date(r.getDeadline().getTime()));
        ps.setLong(4, r.getAccount().getId());
        ps.setLong(5, r.getMember().getId());
        
        int affectedRows = ps.executeUpdate();
    }

    @Override
    public void edit(Reservation t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Reservation t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reservation getById(Long k) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Long getLastIndex() throws SQLException, IOException{
        try {
            this.connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement ps = this.connection.prepareStatement("SELECT MAX(id) as maxid FROM Reservation");
            ResultSet rs = ps.executeQuery();
            Long maxIndex = Long.MIN_VALUE;
            if(rs.next()){
                maxIndex = rs.getLong("maxid");
            }
            return maxIndex;
        } catch (Exception e) {
            System.out.println("Exception kod max indeksa: "+e);
            throw e;
        }

    }
    
    
}
