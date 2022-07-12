/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forms;

import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Performance;

/**
 *
 * @author David
 */
public class TableModelPerformance extends AbstractTableModel {
    private List<Performance> performances;
    private String atributi[] = new String[] {"Title", "Genre", "Duration(min)", "Performance Date", "Stage", "Premiere"};
    private Class atributsClass[] = new Class[] {String.class, String.class, Integer.class, Date.class, String.class, Boolean.class};

    public TableModelPerformance(List<Performance> performances) {
        this.performances = performances;
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
