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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Member;
import persistence.db.DBConnectionFactory;
import persistence.db.DBRepository;

/**
 *
 * @author David
 */
public class AccountRepository implements DBRepository<Account, Long>{
    
    private Connection connection;

    @Override
    public List<Account> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Account account) throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("INSERT INTO ACCOUNT(username,password,isAdmin,memberId) VALUES(?,?,?,?)");
        ps.setString(1, account.getUsername());
        ps.setString(2, account.getPassword());
        ps.setBoolean(3, account.isIsAdmin());
        ps.setLong(4, account.getMember().getId());

        int rowsAffected = ps.executeUpdate();
    }
    
    public Account getForLogin(String username, String password) throws SQLException, IOException {
        try {
            this.connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM Account a LEFT JOIN Member m ON a.memberId= m.id WHERE a.username=? AND a.password=?;");
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            Account account = null;
            if(rs.next()){
                account = new Account(rs.getLong("a.id"), rs.getString("a.username"), rs.getString("a.password"), null, rs.getBoolean("a.isAdmin"));
                if(rs.getString("m.id")!=null){
                    account.setMember(new Member(rs.getLong("m.id"), rs.getString("m.ImePrezime"), rs.getString("m.email"), account));
                }
            }
            return account;
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (IOException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    

    @Override
    public void edit(Account t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Account t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account getById(Long k) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
