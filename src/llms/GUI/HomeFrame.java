package llms.GUI;

import java.util.Locale;
import java.util.ResourceBundle;
import llms.GUI.Librarian.LibrarianFrame;
import llms.Controller;
import llms.Student.StudentView;

/**
 * The home page JFrame allowing users to choose the option they want to
 * continue browsing the application.
 *
 * @author Sevag Saro Aredjian
 */
public class HomeFrame extends javax.swing.JFrame {

    private Boolean isFrench = false;

    /**
     * Creates new form HomeFrame
     */
    public HomeFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        goToStudentBtn = new javax.swing.JButton();
        goToCatalogBtn = new javax.swing.JButton();
        goToReturnABookBtn = new javax.swing.JButton();
        welcomeLabel = new javax.swing.JLabel();
        goToLibrarianBtn = new javax.swing.JButton();
        languageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        goToStudentBtn.setText("I am a Student");
        goToStudentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToStudentBtnActionPerformed(evt);
            }
        });

        goToCatalogBtn.setText("View Catalog");
        goToCatalogBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToCatalogBtnActionPerformed(evt);
            }
        });

        goToReturnABookBtn.setText("Return Book");
        goToReturnABookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToReturnABookBtnActionPerformed(evt);
            }
        });

        welcomeLabel.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeLabel.setText("Limited Library Management System");

        goToLibrarianBtn.setText("I am a Librarian");
        goToLibrarianBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToLibrarianBtnActionPerformed(evt);
            }
        });

        languageLabel.setText("FR");
        languageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                languageLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(languageLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(welcomeLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(goToStudentBtn)
                                    .addComponent(goToCatalogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(goToLibrarianBtn)
                                    .addComponent(goToReturnABookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(languageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goToStudentBtn)
                    .addComponent(goToLibrarianBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goToCatalogBtn)
                    .addComponent(goToReturnABookBtn))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goToStudentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToStudentBtnActionPerformed
        Controller c = new Controller(null, new StudentView(null));
        c.displayStudentLogin();
        dispose();
    }//GEN-LAST:event_goToStudentBtnActionPerformed

    private void goToCatalogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToCatalogBtnActionPerformed
        new ViewCatalogFrame().setVisible(true);
        dispose();
    }//GEN-LAST:event_goToCatalogBtnActionPerformed

    private void goToReturnABookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToReturnABookBtnActionPerformed
        new ReturnBookFrame().setVisible(true);
        dispose();
    }//GEN-LAST:event_goToReturnABookBtnActionPerformed

    private void goToLibrarianBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToLibrarianBtnActionPerformed
        new LibrarianFrame().setVisible(true);
        dispose();
    }//GEN-LAST:event_goToLibrarianBtnActionPerformed

    private void languageLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_languageLabelMouseClicked
        isFrench = !isFrench;
        String lang = "en";
        String country = "CA";
        Locale locale = new Locale(lang, country);
        ResourceBundle res = ResourceBundle.getBundle("llms/RessourceBundles/LLMS_en_CA", locale);
        if (isFrench) {
            lang = "fr";
            country = "CA";
            locale = new Locale(lang, country);
            res = ResourceBundle.getBundle("llms/RessourceBundles/LLMS_fr_CA", locale);
        }
        languageLabel.setText(res.getString("OTHER_LANG"));
        welcomeLabel.setText(res.getString("WELCOME_HOME"));
        goToStudentBtn.setText(res.getString("STUDENT"));
        goToLibrarianBtn.setText(res.getString("LIBRARIAN"));
        goToCatalogBtn.setText(res.getString("CATALOG"));
        goToReturnABookBtn.setText(res.getString("RETURN"));
    }//GEN-LAST:event_languageLabelMouseClicked

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
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton goToCatalogBtn;
    private javax.swing.JButton goToLibrarianBtn;
    private javax.swing.JButton goToReturnABookBtn;
    private javax.swing.JButton goToStudentBtn;
    private javax.swing.JLabel languageLabel;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
