package llms.GUI.Librarian;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import static llms.HelperFunctions.getFirstValueJList;
import llms.Book.Book;
import llms.Book.BookView;
import llms.Controller;
import llms.Student.Student;
import llms.Student.StudentView;

/**
 * A JFrame allowing the librarian to issue a book to a student.
 *
 * @author Sevag Saro Aredjian
 */
public class IssueBookFrame extends javax.swing.JFrame {

    private boolean isFrench = false;

    /**
     * Creates new form IssueBookFrame
     */
    public IssueBookFrame() {
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

        issueBookLabel = new javax.swing.JLabel();
        bookScrollPane = new javax.swing.JScrollPane();
        bookList = new javax.swing.JList<>();
        studentScrollPane = new javax.swing.JScrollPane();
        studentList = new javax.swing.JList<>();
        issueButton = new javax.swing.JButton();
        goBackLabel = new javax.swing.JLabel();
        languageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        issueBookLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        issueBookLabel.setText("Issue a Book");

        bookList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        bookScrollPane.setViewportView(bookList);

        studentList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        studentScrollPane.setViewportView(studentList);

        issueButton.setText("Issue Book");
        issueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueButtonActionPerformed(evt);
            }
        });

        goBackLabel.setText("Go to library main page");
        goBackLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goBackLabelMouseClicked(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(goBackLabel)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(issueBookLabel)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(issueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(141, 141, 141))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(studentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bookScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13))))))
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
                .addComponent(issueBookLabel)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bookScrollPane)
                    .addComponent(studentScrollPane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(issueButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(goBackLabel)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goBackLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goBackLabelMouseClicked
        new LibrarianFrame().setVisible(true);
        dispose();
    }//GEN-LAST:event_goBackLabelMouseClicked

    private void issueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueButtonActionPerformed
        String bookSN = getFirstValueJList(bookList);
        String studentId = getFirstValueJList(studentList);

        Book book = Controller.getBookBySN(bookSN);
        Student stu = Controller.getStudentById(studentId);

        Controller c = new Controller(book, new BookView(book), stu, new StudentView(stu));
        c.issueBook();
        c.displayIssuedBook();
        dispose();
    }//GEN-LAST:event_issueButtonActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        bookList.setModel(new javax.swing.AbstractListModel<String>() {
            Map<String, String> availableBooks = Controller.getAvailableBooks();

            @Override
            public int getSize() {
                return availableBooks.size();
            }

            @Override
            public String getElementAt(int i) {
                return availableBooks.values().stream().collect(
                        Collectors.toList()).get(i);
            }
        });
        studentList.setModel(new javax.swing.AbstractListModel<String>() {
            Map<String, String> allStudents = Controller.getAllStudents();

            @Override
            public int getSize() {
                return allStudents.size();
            }

            @Override
            public String getElementAt(int i) {
                return allStudents.values().stream().collect(
                        Collectors.toList()).get(i);
            }
        });
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
        issueBookLabel.setText(res.getString("ISSUE_BOOK"));
        issueButton.setText(res.getString("ISSUE_BOOK"));
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
            java.util.logging.Logger.getLogger(IssueBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBookFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> bookList;
    private javax.swing.JScrollPane bookScrollPane;
    private javax.swing.JLabel goBackLabel;
    private javax.swing.JLabel issueBookLabel;
    private javax.swing.JButton issueButton;
    private javax.swing.JLabel languageLabel;
    private javax.swing.JList<String> studentList;
    private javax.swing.JScrollPane studentScrollPane;
    // End of variables declaration//GEN-END:variables
}
