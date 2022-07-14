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

/**
 *
 * @author David
 */
public class Performance extends BaseModel implements Serializable {
    private Long id;
    private Date performanceDate;
    private boolean premiere;
    private String stage;
    private TheatricalPlay theatricalPlay;

    public Performance(TheatricalPlay theatricalPlay) {
        this.theatricalPlay = theatricalPlay;
    }

    public Performance(Long id, Date performanceDate, boolean premiere, String stage, TheatricalPlay theatricalPlay) {
        this.id = id;
        this.performanceDate = performanceDate;
        this.premiere = premiere;
        this.stage = stage;
        this.theatricalPlay = theatricalPlay;
    }

    public Long getId() {
        return id;
    }

    public Date getPerformanceDate() {
        return performanceDate;
    }

    public boolean isPremiere() {
        return premiere;
    }

    public String getStage() {
        return stage;
    }

    public TheatricalPlay getTheatricalPlay() {
        return theatricalPlay;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPerformanceDate(Date performanceDate) {
        this.performanceDate = performanceDate;
    }

    public void setPremiere(boolean premiere) {
        this.premiere = premiere;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public void setTheatricalPlay(TheatricalPlay theatricalPlay) {
        this.theatricalPlay = theatricalPlay;
    }

    @Override
    public String getAttributeList() {
        return "id, performanceDate, premiere, stage, theatricalPlayId";
    }

    @Override
    public String getClassName() {
        return "performance";
    }

    @Override
    public String getAttributeValues() {
        return "'" + this.id + "', '" + this.performanceDate + "', '" + this.premiere + "', '" + this.stage + "', " + this.theatricalPlay.getId();
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
