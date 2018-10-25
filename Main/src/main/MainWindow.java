package main;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tomekniemczyk
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {

        initComponents();
        setLocationRelativeTo(null);

        getIPAddres();
        loadData();

    }

    public void loadData() {

        try {
            yourIP.setText(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getIPAddres() {
//        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
//        model.addRow(new Object[]{"Column 1", "Column 2", "Column 3"});

        final byte[] ip;
        try {
            ip = InetAddress.getLocalHost().getAddress();

        } catch (Exception e) {
            return;     // exit method, otherwise "ip might not have been initialized"
        }

        for (int i = 1; i <= 254; i++) {
            final int j = i;  // i as non-final variable cannot be referenced from inner class
            new Thread(new Runnable() {   // new thread for parallel execution
                public void run() {
                    try {
                        ip[3] = (byte) j;
                        //String hostName = iAddress.getHostName();
                        InetAddress address = InetAddress.getByAddress(ip);
                        String output = address.toString().substring(1);
                        long start_time = System.nanoTime();
                        if (address.isReachable(5000)) {
                            String hostname = address.getHostName();
                            String ip = address.getHostAddress();
                            byte[] tmac = address.getAddress();

                            System.out.println("Adres IP: " + output);

                            long end_time = System.nanoTime();
                            double difference = (end_time - start_time) / 1e6;
                            String ping = Double.toString(difference);

                            System.out.println("Ping: " + ping.substring(0, 1) + "ms");

                            if (hostname.equals(output)) {
                                //System.out.println("Brak nazwy hosta");
                                hostname = "n/a";
                            } else {
                                //System.out.println("Nazwa hosta:" + hostname);

                            }

//                            System.out.println(tmac);
//
//                            StringBuilder sb = new StringBuilder();
//                            for (int i = 0; i < tmac.length; i++) {
//                                sb.append(String.format("%02X%s", tmac[i], (i < tmac.length - 1) ? "-" : ""));
//                            }
//                            System.out.println(sb.toString());
                            String macAdress = "n/a";
                            if (output.equals(InetAddress.getLocalHost().getHostAddress())) {
                               InetAddress ip1 = InetAddress.getByName(output);

                            NetworkInterface network = NetworkInterface.getByInetAddress(ip1);

                            byte[] mac = network.getHardwareAddress();

                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < mac.length; i++) {
                                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                            }
                            System.out.println(sb.toString());
                            macAdress = sb.toString();
                                
                            }

                            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                            model.addRow(new Object[]{output, hostname, ping.substring(0, 1),macAdress});

//                            InetAddress ip1 = InetAddress.getByName(output);
//
//                            NetworkInterface network = NetworkInterface.getByInetAddress(ip1);
//
//                            byte[] mac = network.getHardwareAddress();
//
//                            //        System.out.print("Current MAC address : ");
//                            StringBuilder sb = new StringBuilder();
//                            for (int i = 0; i < mac.length; i++) {
//                                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
//                            }
//                          //  System.out.println(sb.toString());
//                           
                        } else {
                            //  System.out.println("Not Reachable: "+output);
                        }
                    } catch (Exception e) {
                        // e.printStackTrace();
                    }
                }
            }).start();     // dont forget to start the thread
        }

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
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        yourIP = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Adres IP", "Nazwa hosta", "Ping", "Adres MAC"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        jButton1.setText("Skanuj");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Twój adres IP:");

        yourIP.setForeground(new java.awt.Color(0, 0, 255));
        yourIP.setText("adresIP");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yourIP, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(yourIP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
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
                if ("Window".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel yourIP;
    // End of variables declaration//GEN-END:variables
}
