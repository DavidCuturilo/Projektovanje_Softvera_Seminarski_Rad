/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forms.member;

import controller.Controller;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Performance;
import model.Reservation;
import model.ReservationItem;
import view.forms.TableModelPerformance;

/**
 *
 * @author David
 */
public class MemberReservationPanel extends javax.swing.JFrame {
    
    private Socket socket;
    private Controller controller;
    
    private List<Performance> selectedPerformances;

    /**
     * Creates new form MemberReservationPanel
     */
    public MemberReservationPanel() throws Exception {
        initComponents();
        controller = Controller.getInstance();
        selectedPerformances = new ArrayList<>();
        prepareView();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jLabel1 = new javax.swing.JLabel();
        btnSelect = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPerformance = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRezervacije = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        jLabel1.setText("Repertoar");

        btnSelect.setText("Izaberi");
        btnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectActionPerformed(evt);
            }
        });

        btnSave.setText("Sacuvaj rezervacije");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnRemove.setText("Ponisti izabrano");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        tblPerformance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPerformance.setColumnSelectionAllowed(false);
        jScrollPane1.setViewportView(tblPerformance);

        tblRezervacije.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblRezervacije);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        jLabel2.setText("Rezervacije");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnRemove, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                                .addComponent(btnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(52, 52, 52))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSelect)
                        .addGap(130, 130, 130))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnRemove)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectActionPerformed
        int getSelectedRow = tblPerformance.getSelectedRow();
        
        if(getSelectedRow > -1) {
            TableModelPerformance tm = (TableModelPerformance) tblPerformance.getModel();
            Performance performance = tm.getPerformanceAtIndex(getSelectedRow);

            TableModelPerformance tmR = (TableModelPerformance) tblRezervacije.getModel();
            tmR.addPerformance(performance);

            selectedPerformances.add(performance);
        } else {
            JOptionPane.showMessageDialog(this, "No row selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnSelectActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int getSelectedRow = tblRezervacije.getSelectedRow();
        
        if(getSelectedRow > -1) {
            TableModelPerformance tmR = (TableModelPerformance) tblRezervacije.getModel();
            Performance removedPerformance = tmR.getPerformanceAtIndex(getSelectedRow);
            tmR.removePerformance(getSelectedRow); 
            selectedPerformances.remove(removedPerformance);
        } else {
            JOptionPane.showMessageDialog(this, "No row selected", "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        int deadlineYear = new Date().getYear();
        int deadlineMonth = new Date().getMonth();
        int deadlineDay = new Date().getDate()+7;
        
        Long index = Long.MIN_VALUE;
        try {
            index = controller.getMaxIndex()+1;
        } catch (Exception ex) {
            Logger.getLogger(MemberReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        Reservation reservation = new Reservation(index, new Date(), new Date(deadlineYear, deadlineMonth, deadlineDay), controller.getUserAccount(), controller.getUserAccount().getMember(), null);
        boolean continueProces = false;
        
        List<ReservationItem> reservationItems = new ArrayList<>();
        for (Performance performance : selectedPerformances) {
            int quantity = 0;
            Performance currentPerformance = performance;
            for (ReservationItem reservationItem : reservationItems) {
                if(reservationItem.getPerformance().getTheatricalPlay().getTitle().equals(currentPerformance.getTheatricalPlay().getTitle())) continueProces = true;
            }
            if(continueProces) continue;
            for(Performance performance1 : selectedPerformances){
                if(currentPerformance.getTheatricalPlay().getTitle().equals(performance1.getTheatricalPlay().getTitle())){
                    quantity++;
                }
            }
            ReservationItem reservationItem = new ReservationItem(reservation, Long.MIN_VALUE, quantity, currentPerformance);
            reservationItems.add(reservationItem);
        }
        
        reservation.setReservedItems(reservationItems);
        try {
            controller.saveReservation(reservation);
            controller.saveAllReservationItems(reservationItems);
            JOptionPane.showMessageDialog(this, "Successfully  saved reservation", "Success", JOptionPane.INFORMATION_MESSAGE);        
        } catch (Exception ex) {
            Logger.getLogger(MemberReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_btnSaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MemberReservationPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MemberReservationPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MemberReservationPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MemberReservationPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MemberReservationPanel().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(MemberReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSelect;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblPerformance;
    private javax.swing.JTable tblRezervacije;
    // End of variables declaration//GEN-END:variables

    private void prepareView() throws Exception {
        // komunikacija sa bazom, getAllPerformance
        List<Performance> performances = controller.getAllPerformances();
        TableModelPerformance tm = new TableModelPerformance(performances);
        tblPerformance.setModel(tm);
        
        List<Performance> reservations = new ArrayList<>();
        TableModelPerformance tmR = new TableModelPerformance(reservations);
        tblRezervacije.setModel(tmR);
    }
}
