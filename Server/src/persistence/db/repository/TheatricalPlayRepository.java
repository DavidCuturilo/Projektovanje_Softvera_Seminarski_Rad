/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.db.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.TheatricalPlay;
import model.enums.Genre;
import persistence.db.DBConnectionFactory;
import persistence.db.DBRepository;

/**
 *
 * @author David
 */
public class TheatricalPlayRepository implements DBRepository<TheatricalPlay, Long>{
    
    private Connection connection;
    
    @Override
    public List<TheatricalPlay> getAll() throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM TheatricalPlay t");
        ResultSet rs = ps.executeQuery();
        
        List<TheatricalPlay> theatricalPlays = new ArrayList<>();
        while(rs.next()){
            TheatricalPlay tp = new TheatricalPlay(rs.getLong("t.id"), Genre.valueOf(rs.getString("t.genre")), rs.getString("t.title"), rs.getInt("t.duration"));
            theatricalPlays.add(tp);
        }
        rs.close();
        ps.close();
        
        return theatricalPlays;
    }
    
    @Override
    public void add(TheatricalPlay t) throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("INSERT INTO TheatricalPlay(genre,title,duration) VALUES(?,?,?)");
        ps.setString(1, t.getGenre().toString());
        ps.setString(2, t.getTitle());
        ps.setInt(3, t.getDuration());
        int affectedRows = ps.executeUpdate();

    }

    @Override
    public void edit(TheatricalPlay t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(TheatricalPlay t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TheatricalPlay getById(Long k) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
