/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import model.BaseModel;
import persistence.db.DBConnectionFactory;
import persistence.db.DBRepository;

/**
 *
 * @author David
 */
public class Repository implements DBRepository<BaseModel, BaseModel>{

    private Connection connection;

    
    @Override
    public void add(BaseModel el) throws Exception {
        try {
            String upit = "INSERT INTO " + el.getClassName()+ "(" + el.getAttributeList()+ ")" + " VALUES (" + el.getAttributeValues()+ ")";
            connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            el.setId(rs);
            rs.close();
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void edit(BaseModel el) throws Exception {
        try {
            String upit = "UPDATE " + el.getClassName()+ " SET " + el.getAttributeList()+ " WHERE " + el.getQueryCondition();
            connection = DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(upit);
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void delete(BaseModel el) throws Exception {
        try {
            String upit = "DELETE FROM " + el.getClassName()+ " WHERE " + el.getQueryCondition();
            connection = DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(upit);
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public BaseModel getById(BaseModel el) throws Exception {
        try {
            String upit = "SELECT * FROM " + el.getClassName()+ " WHERE " + el.getQueryCondition();
            connection = DBConnectionFactory.getInstance().getConnection();
            Statement st = connection.prepareStatement(upit);
            ResultSet rs = st.executeQuery(upit);
            if (rs.next()) {
                el = el.getNewRecord(rs);
            } else {
                el = null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return el;
    }

    @Override
    public List<BaseModel> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
