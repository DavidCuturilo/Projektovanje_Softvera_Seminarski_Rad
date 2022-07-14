/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import model.Performance;
import model.TheatricalPlay;
import model.enums.Genre;

/**
 *
 * @author David
 */
public class TableModelPerformance extends AbstractTableModel {
    private List<Performance> performances;
    private String atributi[] = new String[] {"Title", "Genre", "Duration(min)", "Performance Date", "Stage", "Premiere"};
    private Class atributsClass[] = new Class[] {String.class, String.class, Integer.class, String.class, String.class, Boolean.class};
    private int rowSize;
            
    public TableModelPerformance(List<Performance> performances) {
        this.performances = performances;
        this.rowSize = this.performances.size()-1;
    }
    
    @Override
    public int getRowCount() {
        return performances.size();
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
        Performance performance = performances.get(rowIndex);
        switch(columnIndex){
            case 0: 
                return performance.getTheatricalPlay().getTitle();
            case 1: 
                return performance.getTheatricalPlay().getGenre();
            case 2: 
                return performance.getTheatricalPlay().getDuration();
            case 3:
                return performance.getPerformanceDate();
            case 4:
                return performance.getStage();
            case 5: 
                return performance.isPremiere();
            default: 
                return "n/a";
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Performance performance = performances.get(rowIndex);
        
        switch(columnIndex){
            case 0: 
                performance.getTheatricalPlay().setTitle(aValue.toString());
                break;
            case 1:
                performance.getTheatricalPlay().setGenre(Genre.valueOf(aValue.toString()));
                break;
            case 2:
                performance.getTheatricalPlay().setDuration(Integer.parseInt(aValue.toString()));
                break;
            case 3:
                SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    performance.setPerformanceDate(sd.parse(aValue.toString()));
                } catch (ParseException ex) {
                    Logger.getLogger(TableModelPerformance.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 4:
                System.out.println("Vrednost za stage: "+aValue.toString());
                performance.setStage(aValue.toString());
                break;
            case 5:
                performance.setPremiere(Boolean.parseBoolean(aValue.toString()));
                break;
            default:
                break;
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(rowIndex > this.rowSize) {
            return true;
        }
        return false;
    }
    
    public List<Performance> getAllPerformance(){
        return this.performances;
    }
    
    public Performance getPerformanceAtIndex(int index){
        return performances.get(index);
    }
    
    public void addPerformance(Performance performance){
        this.performances.add(performance);
        fireTableRowsInserted(performances.size()-1, performances.size()-1);
    }
    
    public void removePerformance(int rowIndex){
        performances.remove(rowIndex);
        fireTableDataChanged();
    }
    
}
