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
import java.sql.Statement;
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
    
    public TheatricalPlay getByTitle(String title) throws SQLException, IOException{
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM TheatricalPlay t WHERE t.title=?;");
        ps.setString(1, title);
        ResultSet rs = ps.executeQuery();
        
        TheatricalPlay tp = new TheatricalPlay();
        if(rs.next()){
            tp.setId(rs.getLong("t.id"));
            tp.setTitle(rs.getString("t.title"));
            tp.setGenre(Genre.valueOf(rs.getString("t.genre")));
            tp.setDuration(rs.getInt("t.duration"));
        }
        rs.close();
        ps.close();
        
        return tp;
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
    
    public Long addAndReturnId(TheatricalPlay t) throws SQLException, IOException, Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("INSERT INTO TheatricalPlay(genre,title,duration) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, t.getGenre().toString());
        ps.setString(2, t.getTitle());
        ps.setInt(3, t.getDuration());
        int affectedRows = ps.executeUpdate();  
        
        ResultSet generatedKeys = ps.getGeneratedKeys();
        
        Long id = null;
        if(generatedKeys.next()){
            id = generatedKeys.getLong(1);
        }else{
            throw new Exception("Neuspešno cuvanje predstave");
        }
        
        return id;
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
