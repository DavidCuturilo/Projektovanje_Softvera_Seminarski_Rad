/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forms.member;

import controller.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Account;
import model.Reservation;
import model.ReservationItem;
import view.forms.TableModelReservation;

/**
 *
 * @author David
 */
public class MemberReservationPreviewPanel extends javax.swing.JFrame {

    private Controller controller;
    private List<ReservationItem> reservationItems;
    /**
     * Creates new form MemberReservationPreviewPanel
     */
    public MemberReservationPreviewPanel() throws Exception {
        initComponents();
        reservationItems = new ArrayList<>();
        controller = Controller.getInstance();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblReservationItems = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("My reservations");

        tblReservationItems.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblReservationItems);

        jButton1.setText("Otkazi rezervaciju");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Izvrsene rezervacije");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int getSelectedRow = tblReservationItems.getSelectedRow();
        
        if(getSelectedRow > -1) {
            
            int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this reservation?","Confirm", JOptionPane.YES_NO_OPTION);
            if(answer == 0){
                TableModelReservation tmR = (TableModelReservation) tblReservationItems.getModel();
                ReservationItem removedReservationItem = tmR.getReservationItemAtIndex(getSelectedRow);
                Reservation reservation = removedReservationItem.getReservation();
                
                try {
                    //Brisanje item-a
                    this.controller.removeReservationItem(removedReservationItem);
                    JOptionPane.showMessageDialog(this, "Successfully  removed reservation", "Success", JOptionPane.INFORMATION_MESSAGE);
                    //provera da li rezervacija ima ijednu stavku, ako ne brise se i ona
                    List<ReservationItem> remainingReservationItems = this.controller.getAllReservationItemsForReservation(reservation.getId());
                    reservation.setReservedItems(remainingReservationItems);
                    
                    if(reservation.getReservedItems().size() < 1){
                        this.controller.removeReservation(reservation);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MemberReservationPreviewPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                

                tmR.removeReservationItem(getSelectedRow);  
                System.out.println("Brisemo stavku");
            }
  
        } else {
            JOptionPane.showMessageDialog(this, "No row selected", "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(MemberReservationPreviewPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MemberReservationPreviewPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MemberReservationPreviewPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MemberReservationPreviewPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MemberReservationPreviewPanel().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(MemberReservationPreviewPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReservationItems;
    // End of variables declaration//GEN-END:variables

    private void prepareView() throws Exception {
        Account a = controller.getUserAccount();
        List<ReservationItem> reservations = controller.getAllReservationItemsForUser(a.getId());
        TableModelReservation tm = new TableModelReservation(reservations);
        tblReservationItems.setModel(tm);
    }
}