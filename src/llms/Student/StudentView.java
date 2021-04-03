package llms.Student;

import llms.GUI.Student.BookSearchFrame;
import llms.GUI.Student.BorrowBookFrame;
import llms.GUI.Student.StudentFrame;
import llms.GUI.Student.StudentLoginFrame;
import llms.GUI.Student.StudentRegisterFrame;

/**
 * Class for student view implementing the factory design pattern.
 *
 * @author Sevag Saro Aredjian
 */
public class StudentView {

    private final Student student;

    /**
     * Constructor for student view.
     *
     * @param student The student who's values will be displayed.
     */
    public StudentView(Student student) {
        this.student = student;
    }

    public void displayStudentView(String displayType, String notification) {
        switch (displayType) {
            case "StudentDetails":
                new StudentFrame(student).setVisible(true);
                break;
            case "SearchFrame":
                new BookSearchFrame(student).setVisible(true);
                break;
            case "BorrowFrame":
                new BorrowBookFrame(student).setVisible(true);
                break;
            case "Notification":
                new StudentFrame(student, notification).setVisible(true);
                break;
            case "Login":
                new StudentLoginFrame().setVisible(true);
                break;
            case "Register":
                new StudentRegisterFrame().setVisible(true);
                break;
            default:
                System.err.println("Unkown display type");
        }
    }
}
