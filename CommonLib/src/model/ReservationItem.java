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
public class ReservationItem extends BaseModel implements Serializable {
    private Long id;
    private Reservation reservation;
    private int quantity;
    private Performance performance;

    public ReservationItem(Reservation reservation, Long id, int quantity, Performance performance) {
        this.reservation = reservation;
        this.id = id;
        this.quantity = quantity;
        this.performance = performance;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    @Override
    public String getAttributeList() {
        return "id, reservationId, quantity, performanceId";
    }

    @Override
    public String getClassName() {
        return "reservationItem";
    }

    @Override
    public String getAttributeValues() {
        return "'" + this.id + "', '" + this.reservation.getId() + "', '" + this.quantity + "', '" + this.performance.getId();
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
