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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Performance;
import model.TheatricalPlay;
import model.enums.Genre;
import persistence.db.DBConnectionFactory;
import persistence.db.DBRepository;

/**
 *
 * @author David
 */
public class PerformanceRepository implements DBRepository<Performance, Long>{
    private Connection connection;

    @Override
    public List<Performance> getAll() throws Exception {
        try {
            this.connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM Performance p LEFT JOIN TheatricalPlay t ON p.theatricalPlayId = t.id;");
            
            ResultSet rs = ps.executeQuery();
            List<Performance> performances = new ArrayList<>();
            Performance performance = null;
            while(rs.next()){
                performance = new Performance(rs.getLong("p.id"), rs.getDate("p.performanceDate"), rs.getBoolean("p.premiere"), rs.getString("p.stage"), null);
                if(rs.getString("t.id")!=null){
                    performance.setTheatricalPlay(new TheatricalPlay(rs.getLong("t.id"), Genre.valueOf(rs.getString("t.genre")), rs.getString("t.title"), rs.getDate("t.duration")));
                }
                performances.add(performance);
            }
            return performances;
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (IOException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public void add(Performance p) throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("INSERT INTO Performance(performanceDate,premiere,stage,theatricalPlayId) VALUES(?,?,?,?)");
        ps.setDate(1, new java.sql.Date(p.getPerformanceDate().getTime()));
        ps.setBoolean(2, p.isPremiere());
        ps.setString(3, p.getStage());
        ps.setLong(4, p.getTheatricalPlay().getId());
        int affectedRows = ps.executeUpdate();
    }

    @Override
    public void edit(Performance p) throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("UPDATE Performance p SET performanceDate =?, premiere=?, stage=? WHERE p.id=?");
        ps.setDate(1, new java.sql.Date(p.getPerformanceDate().getTime()));
        ps.setBoolean(2, p.isPremiere());
        ps.setString(3, p.getStage());
        ps.setLong(4, p.getId());
        int affectedRows = ps.executeUpdate();
    }

    @Override
    public void delete(Performance t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Performance getById(Long k) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
