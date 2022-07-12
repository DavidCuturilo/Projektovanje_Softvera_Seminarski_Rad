/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.db.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Performance;
import model.Reservation;
import model.ReservationItem;
import model.TheatricalPlay;
import model.enums.Genre;
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
    
    public List<ReservationItem> getAllItemsForUser(Long userId) throws SQLException, IOException {
        try {
            this.connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM Reservation r RIGHT JOIN ReservationItem ri ON ri.reservationId= r.id LEFT JOIN Performance p on ri.performanceId = p.id LEFT JOIN TheatricalPlay tp on p.theatricalPlayId=tp.id WHERE r.accountId=?;");
            ps.setLong(1, userId);
            
            ResultSet rs = ps.executeQuery();
            List<ReservationItem> reservationItems = new ArrayList<>();
            
            while(rs.next()){
                ReservationItem reservationItem = new ReservationItem(null, rs.getLong("ri.id"), rs.getInt("ri.quantity"), null);
                if(rs.getString("r.id")!=null){
                    reservationItem.setReservation(new Reservation(rs.getLong("r.id"), rs.getDate("r.dateOfReservation"), rs.getDate("r.deadline"), null, null, null));
                }
                if(rs.getString("p.id") != null){
                    reservationItem.setPerformance(new Performance(rs.getLong("p.id"), rs.getDate("p.performanceDate"), rs.getBoolean("p.premiere"), rs.getString("p.stage"), new TheatricalPlay(null, Genre.valueOf(rs.getString("tp.genre")), rs.getString("tp.title"), rs.getInt("tp.duration"))));
                }
                reservationItems.add(reservationItem);
            }
            return reservationItems;
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (IOException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public List<ReservationItem> getAllItemsForReservation(Long reservationId) throws SQLException, IOException {
        try {
            this.connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM ReservationItem ri WHERE ri.reservationId=?;");
            ps.setLong(1, reservationId);
            
            ResultSet rs = ps.executeQuery();
            List<ReservationItem> reservationItems = new ArrayList<>();
            
            while(rs.next()){
                ReservationItem reservationItem = new ReservationItem(null, rs.getLong("ri.id"), rs.getInt("ri.quantity"), null);
                reservationItems.add(reservationItem);
            }
            return reservationItems;
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (IOException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
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
    public void delete(ReservationItem ri) throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("DELETE FROM ReservationItem WHERE id =?;");
        ps.setLong(1, ri.getId());
        int affectedRows = ps.executeUpdate();
    }

    @Override
    public ReservationItem getById(Long k) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
