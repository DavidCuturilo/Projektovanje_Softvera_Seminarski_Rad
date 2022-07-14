/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forms;

import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Member;
import model.Reservation;

/**
 *
 * @author David
 */
public class TableModelUserReservation extends AbstractTableModel {
 private List<Reservation> reservations;
    private String atributi[] = new String[] {"Ime i prezime","Naziv predstave", "Datum izvodjenja", "Broj karata za izvodjenje"};
    private Class atributsClass[] = new Class[] {String.class, String.class, Date.class, Integer.class};

    public TableModelUserReservation(List<Reservation> reservations) {
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
    
    public List<Reservation> getAllReservations(){
        return this.reservations;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Reservation reservation = reservations.get(rowIndex);
        switch(columnIndex){
            case 0: 
                return reservation.getMember().getImePrezime();
            case 1: 
                return reservation.getReservedItems().get(0).getPerformance().getTheatricalPlay().getTitle();
            case 2: 
                return reservation.getReservedItems().get(0).getPerformance().getPerformanceDate();
            case 3:
                return reservation.getReservedItems().get(0).getQuantity();
            default: 
                return "n/a";
        }
    }
}
