package llms.GUI.Librarian;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import llms.Book.Book;
import llms.Book.BookView;
import llms.Controller;

/**
 * A JFrame allowing librarians to view all issued books.
 *
 * @author Sevag Saro Aredjian
 */
public class ViewIssuedBookFrame extends javax.swing.JFrame {

    private Book book;
    private boolean isFrench = false;
    private boolean wasIssued = false;

    /**
     * Creates new form ViewIssuedBookFrame
     */
    public ViewIssuedBookFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * Creates new form ViewIssuedBookFrame
     *
     * @param book The book that will be displayed.
     */
    public ViewIssuedBookFrame(Book book) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.book = book;
        this.wasIssued = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        goBackLabel = new javax.swing.JLabel();
        viewIssuedLabel = new javax.swing.JLabel();
        issuedScrollPane = new javax.swing.JScrollPane();
        issuedList = new javax.swing.JList<>();
        languageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(512, 324));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        goBackLabel.setText("Go to library main page");
        goBackLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goBackLabelMouseClicked(evt);
            }
        });

        viewIssuedLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        viewIssuedLabel.setText("View Issued Books");

        issuedList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        issuedScrollPane.setViewportView(issuedList);

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(goBackLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(viewIssuedLabel)
                                    .addComponent(issuedScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(languageLabel)))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(languageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewIssuedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(issuedScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(goBackLabel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goBackLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goBackLabelMouseClicked
        new LibrarianFrame().setVisible(true);
        dispose();
    }//GEN-LAST:event_goBackLabelMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (wasIssued) {
            Controller c = new Controller(book, new BookView(book));
            viewIssuedLabel.setText("Issued " + c.getTitle());

            issuedList.setModel(new javax.swing.AbstractListModel<String>() {
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
            issuedList.setModel(new javax.swing.AbstractListModel<String>() {
                Map<String, String> issuedBooks = Controller.viewIssuedbooks();

                @Override
                public int getSize() {
                    return issuedBooks.size();
                }

                @Override
                public String getElementAt(int i) {
                    return issuedBooks.values().stream().collect(
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
        viewIssuedLabel.setText(res.getString("VIEW_ISSUED"));
        goBackLabel.setText(res.getString("GO_BACK_LIBRARY"));
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
            java.util.logging.Logger.getLogger(ViewIssuedBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewIssuedBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewIssuedBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewIssuedBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewIssuedBookFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel goBackLabel;
    private javax.swing.JList<String> issuedList;
    private javax.swing.JScrollPane issuedScrollPane;
    private javax.swing.JLabel languageLabel;
    private javax.swing.JLabel viewIssuedLabel;
    // End of variables declaration//GEN-END:variables
}
