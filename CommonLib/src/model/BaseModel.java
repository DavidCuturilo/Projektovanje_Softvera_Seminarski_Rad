/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author David
 */
public abstract class BaseModel {

    public abstract String getAttributeList();

    public abstract String getClassName();

    public abstract String getAttributeValues();

    public abstract String getQueryCondition();

    public abstract BaseModel getNewRecord(ResultSet rs) throws SQLException;
    
    public abstract void setId(ResultSet rs) throws Exception;

}
