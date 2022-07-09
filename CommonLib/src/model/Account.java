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
public class Account extends BaseModel implements Serializable {
    
    private Long id;
    private String username;
    private String password;
    private Member member;
    private boolean isAdmin;

    public Account(Long id, String username, String password, Member member, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.member = member;
        this.isAdmin = isAdmin;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Member getMember() {
        return member;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String getAttributeList() {
        return "id, username, password, memberId, isAdmin";
    }

    @Override
    public String getClassName() {
        return "account";
    }

    @Override
    public String getAttributeValues() {
        return "'" + this.id + "', '" + this.username + "', '" + this.password + "', '" + this.isAdmin + "', " + this.member.getId();
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
