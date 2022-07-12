/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import model.enums.Genre;

/**
 *
 * @author David
 */
public class TheatricalPlay extends BaseModel implements Serializable {
    private Long id;
    private Genre genre;
    private String title;
    private int duration;

    public TheatricalPlay(Long id, Genre genre, String title, int duration) {
        this.id = id;
        this.genre = genre;
        this.title = title;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String getAttributeList() {
        return "id, genre, title, duration";
    }

    @Override
    public String getClassName() {
        return "theatricalPlay";
    }

    @Override
    public String getAttributeValues() {
        return "'" + this.id + "', '" + this.genre + "', '" + this.title + "', '" + this.duration;
    }

    @Override
    public String getQueryCondition() {
        return "id=" + id;
    }

    @Override
    public BaseModel getNewRecord(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
