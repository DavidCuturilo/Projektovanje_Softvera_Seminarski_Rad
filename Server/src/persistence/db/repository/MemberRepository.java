/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Member;
import persistence.db.DBConnectionFactory;
import persistence.db.DBRepository;

/**
 *
 * @author David
 */
public class MemberRepository implements DBRepository<Member, Long> {
    
    private Connection connection;

    @Override
    public List<Member> getAll() throws Exception {
        this.connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM MEMBER m LEFT JOIN account a ON m.id=a.memberId;");
        ResultSet rs = ps.executeQuery();
        
        List<Member> employeeList = new ArrayList<>();
        while(rs.next()){
            Member m = new Member(rs.getLong("e.id"), rs.getString("e.ImePrezime"), rs.getString("e.email"), null);
            long accId = rs.getLong("a.id");
            if(!rs.wasNull()){
                boolean isAdmin = rs.getBoolean("isAdmin");
                if(isAdmin)continue;
                m.setAccount(new Account(accId, rs.getString("a.username"), rs.getString("a.password"), m, rs.getBoolean("isAdmin")));
            }
            employeeList.add(m);
        }
        rs.close();
        ps.close();
        
        return employeeList;
    }

    @Override
    public void add(Member t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Member t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Member t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Member getById(Long k) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
