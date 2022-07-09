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
public class Member extends BaseModel implements Serializable {
    private Long id;
    private String imePrezime;
    private String email;
    private Account account;

    public Member() {
    }

    public Member(Long id, String imePrezime, String email, Account account) {
        this.id = id;
        this.imePrezime = imePrezime;
        this.email = email;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public String getEmail() {
        return email;
    }

    public Account getAccount() {
        return account;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String getAttributeList() {
        return "id, imePrezime, email, account";
    }

    @Override
    public String getClassName() {
        return "member";
    }

    @Override
    public String getAttributeValues() {
        return "'" + this.id + "', '" + this.imePrezime + "', '" + this.email + "', '" + this.account.getId();
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
