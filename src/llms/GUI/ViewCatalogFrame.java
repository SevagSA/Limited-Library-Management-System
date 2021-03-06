package llms.GUI;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import llms.Book.Book;
import llms.Book.BookView;
import llms.Controller;

/**
 * A JFrame allowing users to view the catalog of the library.
 *
 * @author Sevag Saro Aredjian
 */
public class ViewCatalogFrame extends javax.swing.JFrame {

    private Book book;
    private boolean wasAdded = false;
    private boolean isFrench = false;

    /**
     * Creates new form ViewCatalogFrame
     */
    public ViewCatalogFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * Creates new form ViewCatalogFrame for and individual book.
     *
     * @param book The book that will be displayed.
     */
    public ViewCatalogFrame(Book book) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.wasAdded = true;
        this.book = book;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        catalogScrollPane = new javax.swing.JScrollPane();
        catalogList = new javax.swing.JList<>();
        goBackLabel = new javax.swing.JLabel();
        viewCatalogLabel = new javax.swing.JLabel();
        languageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        catalogScrollPane.setViewportView(catalogList);

        goBackLabel.setText("Go to home page");
        goBackLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goBackLabelMouseClicked(evt);
            }
        });

        viewCatalogLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        viewCatalogLabel.setText("View Catalog");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(goBackLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(catalogScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(viewCatalogLabel))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(languageLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(languageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewCatalogLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(catalogScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addComponent(goBackLabel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goBackLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goBackLabelMouseClicked
        new HomeFrame().setVisible(true);
        dispose();
    }//GEN-LAST:event_goBackLabelMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (wasAdded) {
            Controller c = new Controller(book, new BookView(book));
            viewCatalogLabel.setText(wasAdded ? c.getTitle() + " was added." : c.getTitle());
            catalogList.setModel(new javax.swing.AbstractListModel<String>() {
                String[] bookArr = {book.toString()};

                @Override
                public int getSize() {
                    return bookArr.length;
                }

                @Override
                public String getElementAt(int i) {
                    return bookArr[i];
                }
            });
        } else {
            catalogList.setModel(new javax.swing.AbstractListModel<String>() {
                Map<String, String> catalog = Controller.viewCatalog();

                @Override
                public int getSize() {
                    return catalog.size();
                }

                @Override
                public String getElementAt(int i) {
                    return catalog.values().stream().collect(
                            Collectors.toList()).get(i);
                }
            });
        }
    }//GEN-LAST:event_formWindowOpened

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
        viewCatalogLabel.setText(res.getString("CATALOG"));
        goBackLabel.setText(res.getString("GO_BACK_HOME"));
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
            java.util.logging.Logger.getLogger(ViewCatalogFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCatalogFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCatalogFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCatalogFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewCatalogFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> catalogList;
    private javax.swing.JScrollPane catalogScrollPane;
    private javax.swing.JLabel goBackLabel;
    private javax.swing.JLabel languageLabel;
    private javax.swing.JLabel viewCatalogLabel;
    // End of variables declaration//GEN-END:variables
}
