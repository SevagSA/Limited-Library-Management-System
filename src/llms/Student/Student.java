package llms.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import llms.Book.Book;
import llms.Controller;
import llms.DatabaseConnection;

/**
 * Class of student.
 *
 * @author Sevag Saro Aredjian
 */
public class Student {

    private static final Connection CON = DatabaseConnection.getInstance();

    private String stId;
    private String name;
    private String contactNumber;

    /**
     * Student constructor with all attributes.
     *
     * @param stId The student's id.
     * @param name The student's name.
     * @param contactNumber The student's contact number.
     */
    public Student(String stId, String name, String contactNumber) {
        this.stId = stId;
        // if an empty string is passed ("") i.e. the user did not input a value
        // from the GUI, the value null should be stored instead of ""
        this.name = name.isEmpty() ? null : name;
        this.contactNumber = contactNumber.isEmpty() ? null : contactNumber;
    }

    /**
     * To add a student to the Students table in the database.
     *
     * @param student The student that will be added.
     */
    public void addStudent(Student student) {
        Controller c = new Controller(student, new StudentView(student));
        try (Statement stmt = CON.createStatement()) {
            String insertQuery
                    = "INSERT INTO Students (StudentId, Name, Contact) "
                    + "VALUES ('" + c.getStId() + "', '" + c.getName()
                    + "', '" + c.getContactNumber() + "')";
            stmt.executeUpdate(insertQuery);
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage()
                    + " : method: addStudent()");
            System.exit(0);
        }
    }

    /**
     * To search a book by its title.
     *
     * @param title The title that will be searched.
     * @return A list of books ordered by their serial number that have a title
     * that matches the input title.
     */
    public List<Book> searchBookByTitle(String title) {
        List<Book> searchResult = new ArrayList<>();
        try (Statement stmt = CON.createStatement()) {
            String insertQuery
                    = "SELECT * FROM Books WHERE Title LIKE '%" + title + "%'";
            ResultSet rs = stmt.executeQuery(insertQuery);
            while (rs.next()) {
                searchResult.add(new Book(rs.getString("SN"), rs.getString("Title"),
                        rs.getString("Author"), rs.getString("Publisher"), rs.getInt("Quantity"),
                        rs.getInt("Issued"), rs.getString("addedDate")));
            }
            Collections.sort(searchResult, (Book b1, Book b2) -> {
                return b1.getSN().compareToIgnoreCase(b2.getSN());
            });
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage()
                    + " : method: searchBookByTitle()");
            System.exit(0);
        }
        return searchResult;
    }

    /**
     * To search a book by its name.
     *
     * @param name The name that will be searched.
     * @return A list of books ordered by their serial number that have a name
     * that matches the input name.
     */
    public List<Book> searchBookByName(String name) {
        List<Book> searchResult = new ArrayList<>();
        try (Statement stmt = CON.createStatement()) {
            String insertQuery
                    = "SELECT * FROM Books WHERE Author LIKE '%" + name + "%'";
            ResultSet rs = stmt.executeQuery(insertQuery);
            while (rs.next()) {
                searchResult.add(new Book(rs.getString("SN"), rs.getString("Title"),
                        rs.getString("Author"), rs.getString("Publisher"), rs.getInt("Quantity"),
                        rs.getInt("Issued"), rs.getString("addedDate")));
            }
            Collections.sort(searchResult, (Book b1, Book b2) -> {
                return b1.getSN().compareToIgnoreCase(b2.getSN());
            });
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage()
                    + " : method: searchBookByName()");
            System.exit(0);
        }
        return searchResult;
    }

    /**
     * To search a book by its year.
     *
     * @param year The year that will be searched.
     * @return A list of books ordered by their serial number that have a year
     * that matches the input year.
     */
    public List<Book> searchBookByYear(String year) {
        List<Book> searchResult = new ArrayList<>();
        try (Statement stmt = CON.createStatement()) {
            String insertQuery
                    = "SELECT * FROM Books WHERE addedDate LIKE '%" + year + "%'";
            ResultSet rs = stmt.executeQuery(insertQuery);
            while (rs.next()) {
                searchResult.add(new Book(rs.getString("SN"), rs.getString("Title"),
                        rs.getString("Author"), rs.getString("Publisher"), rs.getInt("Quantity"),
                        rs.getInt("Issued"), rs.getString("addedDate")));
            }
            Collections.sort(searchResult, (Book b1, Book b2) -> {
                return b1.getSN().compareToIgnoreCase(b2.getSN());
            });
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage()
                    + " : method: searchBookByYear()");
            System.exit(0);
        }
        return searchResult;
    }

    /**
     * To view the entire catalog of books available in the library.
     *
     * @return A Map that contains all book sorted by their serial number and
     * where the serial number is the key and the book's information is the
     * value.
     */
    public Map<String, String> viewCatalog() {
        return Book.viewCatalog();
    }

    /**
     * To borrow a book.
     *
     * @param book The book that will be borrowed.
     * @return True if the borrow was successful, false if otherwise.
     */
    public boolean borrowBook(Book book) {
        return book.issueBook(book, this);
    }

    /**
     * To return a book from the books that the student has borrowed.
     *
     * @param book The book that will be returned
     * @return True if the return was successful, false if otherwise.
     */
    public boolean returnBook(Book book) {
        return book.returnBook(book, this);
    }

    /**
     * To get a Map of all students.
     *
     * @return A Map of all students where the student is the key, and the value
     * is a string containing all the student's information.
     */
    public static Map<String, String> getAllStudents() {
        HashMap<String, String> allStudentsMap = new HashMap<>();
        try (Statement stmt = CON.createStatement()) {
            String getStudentsQuery = "SELECT * FROM Students";
            ResultSet rs = stmt.executeQuery(getStudentsQuery);
            while (rs.next()) {
                allStudentsMap.put(rs.getString("StudentId"),
                        "StId: " + rs.getString("StudentId")
                        + ", Name: " + rs.getString("Name")
                        + ", Contact: " + rs.getString("Contact"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage()
                    + " : method: getAllStudents()");
            System.exit(0);
        }
        return allStudentsMap;
    }

    /**
     * To get a Map of all students that have issued a book.
     *
     * @return A Map of all students that have issued a book where the student
     * is the key, and the value is a string containing all the student's
     * information.
     */
    public static Map<String, String> getAllIssuedStudents() {
        HashMap<String, String> allStudentsMap = new HashMap<>();
        try (Statement stmt = CON.createStatement()) {
            String getStudentsQuery = "SELECT * FROM Students "
                    + "INNER JOIN IssuedBooks on IssuedBooks.StId = Students.StudentId;";
            ResultSet rs = stmt.executeQuery(getStudentsQuery);
            while (rs.next()) {
                allStudentsMap.put(rs.getString("StudentId"),
                        "StId: " + rs.getString("StudentId")
                        + ", Name: " + rs.getString("Name")
                        + ", Contact: " + rs.getString("Contact"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage()
                    + " : method: getAllIssuedStudents()");
            System.exit(0);
        }
        return allStudentsMap;
    }

    /**
     * To get a student by their id.
     *
     * @param id The id of the student that we want to get.
     * @return The student that has the input id. A student with null values
     * will return if a student with the input id does not exists.
     */
    public static Student getStudentById(String id) {
        Student student = new Student("", "", "");
        try (Statement stmt = CON.createStatement()) {
            String getStudentsQuery = "SELECT * FROM Students "
                    + "WHERE StudentId = '" + id + "'";
            ResultSet rs = stmt.executeQuery(getStudentsQuery);
            while (rs.next()) {
                student.setStId(rs.getString("StudentId"));
                student.setName(rs.getString("Name"));
                student.setContactNumber(rs.getString("Contact"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage()
                    + " : method: getStudentById()");
            System.exit(0);
        }
        return student;
    }

    @Override
    public String toString() {
        return "Student{" + "stId=" + stId + ", name=" + name + ", contactNumber="
                + contactNumber + '}';
    }

    /**
     * Getter.
     *
     * @return The student's id.
     */
    public String getStId() {
        return stId;
    }

    /**
     * Setter.
     *
     * @param stId The student's id.
     */
    public void setStId(String stId) {
        this.stId = stId;
    }

    /**
     * Getter.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter.
     *
     * @param name The student's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter.
     *
     * @return The student's contact number.
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Setter.
     *
     * @param contactNumber The student's contact number.
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
