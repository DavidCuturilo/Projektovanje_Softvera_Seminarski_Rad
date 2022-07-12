/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forms;

import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.ReservationItem;

/**
 *
 * @author David
 */
public class TableModelReservation extends AbstractTableModel {
    private List<ReservationItem> reservations;
    private String atributi[] = new String[] {"Title", "Performance date", "Number of res.", "Reservation date", "Pickup deadline"};
    private Class atributsClass[] = new Class[] {String.class, Date.class, Integer.class, Date.class, Date.class};

    public TableModelReservation(List<ReservationItem> reservations) {
        this.reservations = reservations;
    }
    
    @Override
    public int getRowCount() {
        return reservations.size();
    }

    @Override
    public int getColumnCount() {
        return atributi.length;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex>atributsClass.length){
            return Object.class;
        } else return atributsClass[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        if(column>atributi.length){
            return "n/a";
        } else return atributi[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ReservationItem reservationItem = reservations.get(rowIndex);
        switch(columnIndex){
            case 0:
                return reservationItem.getPerformance().getTheatricalPlay().getTitle();
            case 1:
                return reservationItem.getPerformance().getPerformanceDate();
            case 2:
                return reservationItem.getQuantity();
            case 3:
                return reservationItem.getReservation().getDateOfReservation();
            case 4:
                return reservationItem.getReservation().getDeadline();
            default: 
                return "n/a";
        }
    }
    
    public List<ReservationItem> getAllPerformance(){
        return this.reservations;
    }
    
    public ReservationItem getReservationItemAtIndex(int index){
        return this.reservations.get(index);
    }
    
    public void removeReservationItem(int rowIndex){
        reservations.remove(rowIndex);
        fireTableDataChanged();
    }
}
