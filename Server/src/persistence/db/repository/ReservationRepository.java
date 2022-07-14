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
import java.util.ArrayList;
import java.util.List;
import model.Member;
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

    public List<Reservation> getAllReservationWithExtraDetails() throws SQLException, IOException{
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM Member m RIGHT JOIN Reservation r ON m.id = r.memberId RIGHT JOIN ReservationItem ri ON r.id = ri.reservationId LEFT JOIN Performance p ON ri.performanceId = p.id LEFT JOIN TheatricalPlay tp ON p.theatricalPlayId = tp.id;");
        ResultSet rs = ps.executeQuery();
        
        List<Reservation> reservations = new ArrayList<>();
        while(rs.next()){
            Member m = new Member(rs.getLong("m.id"), rs.getString("m.ImePrezime"), rs.getString("m.email"), null);
            Reservation reservation = null;
            if(rs.getString("r.id")!=null){
                reservation = new Reservation(rs.getLong("r.id"), rs.getDate("r.dateOfReservation"), rs.getDate("r.deadline"), null, m, null);
            }
            ReservationItem resItem=null;
            if(rs.getString("ri.id")!=null){
                resItem = new ReservationItem(reservation, rs.getLong("ri.id"), rs.getInt("ri.quantity"), null);
            }
            Performance performance = null;
            if(rs.getString("p.id")!=null){
                performance = new Performance(rs.getLong("p.id"), rs.getDate("p.performanceDate"), rs.getBoolean("p.premiere"), rs.getString("p.stage"), null);
            }
            TheatricalPlay theatricalPlay = null;
            if(rs.getString("tp.id")!=null){
                theatricalPlay = new TheatricalPlay(rs.getLong("tp.id"), Genre.valueOf(rs.getString("tp.genre")), rs.getString("tp.title"), rs.getInt("tp.duration"));
            }
            performance.setTheatricalPlay(theatricalPlay);
            resItem.setPerformance(performance);
            List<ReservationItem> resItems = new ArrayList<>();
            resItems.add(resItem);
            reservation.setReservedItems(resItems);
            reservations.add(reservation);
        }
        rs.close();
        ps.close();
        
        return reservations;
    }
    
        public List<Reservation> getAllReservationWithExtraDetailsForMember(String imePrezime) throws SQLException, IOException{
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM Member m RIGHT JOIN Reservation r ON m.id = r.memberId RIGHT JOIN ReservationItem ri ON r.id = ri.reservationId LEFT JOIN Performance p ON ri.performanceId = p.id LEFT JOIN TheatricalPlay tp ON p.theatricalPlayId = tp.id WHERE m.imePrezime=?;");
        ps.setString(1, imePrezime);
        ResultSet rs = ps.executeQuery();
        
        List<Reservation> reservations = new ArrayList<>();
        while(rs.next()){
            Member m = new Member(rs.getLong("m.id"), rs.getString("m.ImePrezime"), rs.getString("m.email"), null);
            Reservation reservation = null;
            if(rs.getString("r.id")!=null){
                reservation = new Reservation(rs.getLong("r.id"), rs.getDate("r.dateOfReservation"), rs.getDate("r.deadline"), null, m, null);
            }
            ReservationItem resItem=null;
            if(rs.getString("ri.id")!=null){
                resItem = new ReservationItem(reservation, rs.getLong("ri.id"), rs.getInt("ri.quantity"), null);
            }
            Performance performance = null;
            if(rs.getString("p.id")!=null){
                performance = new Performance(rs.getLong("p.id"), rs.getDate("p.performanceDate"), rs.getBoolean("p.premiere"), rs.getString("p.stage"), null);
            }
            TheatricalPlay theatricalPlay = null;
            if(rs.getString("tp.id")!=null){
                theatricalPlay = new TheatricalPlay(rs.getLong("tp.id"), Genre.valueOf(rs.getString("tp.genre")), rs.getString("tp.title"), rs.getInt("tp.duration"));
            }
            performance.setTheatricalPlay(theatricalPlay);
            resItem.setPerformance(performance);
            List<ReservationItem> resItems = new ArrayList<>();
            resItems.add(resItem);
            reservation.setReservedItems(resItems);
            reservations.add(reservation);
        }
        rs.close();
        ps.close();
        
        return reservations;
    }
    
    @Override
    public void edit(Reservation t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Reservation r) throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("DELETE FROM Reservation WHERE id =?;");
        ps.setLong(1, r.getId());
        int affectedRows = ps.executeUpdate();
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
