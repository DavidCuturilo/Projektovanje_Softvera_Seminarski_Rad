/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import model.ReservationItem;
import persistence.db.DBConnectionFactory;
import persistence.db.DBRepository;

/**
 *
 * @author David
 */
public class ReservationItemRepository implements DBRepository<ReservationItem, Long>{
    
    private Connection connection;

    @Override
    public List<ReservationItem> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addAll(List<ReservationItem> reservationItems) throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        try {
            for (ReservationItem reservationItem : reservationItems) {
                add(reservationItem);
            }
        } catch (Exception e) {
        }
    }
    
    @Override
    public void add(ReservationItem rI) throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("INSERT INTO ReservationItem(reservationId,quantity,performanceId) VALUES(?,?,?)");
        ps.setLong(1, rI.getReservation().getId());
        ps.setInt(2, rI.getQuantity());
        ps.setLong(3, rI.getPerformance().getId());
        int affectedRows = ps.executeUpdate();
    }

    @Override
    public void edit(ReservationItem t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ReservationItem t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReservationItem getById(Long k) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
