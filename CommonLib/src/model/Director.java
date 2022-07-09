/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author David
 */
public class Director extends BaseModel implements Serializable {
    private Long id;
    private String imePrezime;
    private Performance performance;

    public Director(Long id, String imePrezime, Performance performance) {
        this.id = id;
        this.imePrezime = imePrezime;
        this.performance = performance;
    }

    public Long getId() {
        return id;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    @Override
    public String getAttributeList() {
        return "id, imePrezime, performance";
    }

    @Override
    public String getClassName() {
        return "director";
    }

    @Override
    public String getAttributeValues() {
        return "'" + this.id + "', '" + this.imePrezime + "', '" + this.performance.getId();
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
