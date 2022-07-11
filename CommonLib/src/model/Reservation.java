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
import java.util.List;

/**
 *
 * @author David
 */
public class Reservation extends BaseModel implements Serializable {
    private Long id;
    private Date dateOfReservation;
    private Date deadline;
    private Account account;
    private Member member;
    List<ReservationItem> reservedItems;

    public Reservation(Long id, Date dateOfReservation, Date deadline, Account account, Member member, List<ReservationItem> reservedItems) {
        this.id = id;
        this.dateOfReservation = dateOfReservation;
        this.deadline = deadline;
        this.account = account;
        this.member = member;
        this.reservedItems = reservedItems;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }


    public Long getId() {
        return id;
    }

    public Date getDateOfReservation() {
        return dateOfReservation;
    }

    public Date getDeadline() {
        return deadline;
    }

    public Account getAccount() {
        return account;
    }

    public List<ReservationItem> getReservedItems() {
        return reservedItems;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateOfReservation(Date dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setReservedItems(List<ReservationItem> reservedItems) {
        this.reservedItems = reservedItems;
    }

    @Override
    public String getAttributeList() {
        return "id, dateOfReservation, deadline, accountId, memberId";
    }

    @Override
    public String getClassName() {
        return "reservation";
    }

    @Override
    public String getAttributeValues() {
        return "'" + this.id + "', '" + this.dateOfReservation + "', '" + this.deadline + "', '" + this.account.getId() + "', " + this.member.getId();
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
