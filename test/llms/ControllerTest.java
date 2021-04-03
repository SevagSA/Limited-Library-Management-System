package llms;

import java.util.Arrays;
import java.util.List;
import llms.Book.Book;
import llms.Book.BookView;
import llms.Student.Student;
import llms.Student.StudentView;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Test class for the Controller of the project.
 *
 * @author Sevag Saro Aredjian
 */
public class ControllerTest {

    public ControllerTest() {
    }

    /**
     * To remove the Books record in the database from the previous test cases.
     */
    public static void removeBooksFromDatabase() {
        Connection con = DatabaseConnection.getInstance();
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate("DELETE FROM Students");
            stmt.executeUpdate("DELETE FROM IssuedBooks");
            stmt.executeUpdate("DELETE FROM Books");
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
    }

    /**
     * To add populate the database with books to use during the tests.
     */
    public static void addBooksToDatabase() {
        System.out.println("addBooksToDatabase");

        Book book1 = new Book("1", "Win without pitching manifesto", "Blair Enns",
                "Koan", 3500, 0, "January 2, 2015");
        Controller c1 = new Controller(book1, new BookView(book1));
        c1.addBook();

        Book book2 = new Book("2", "The best Java book", "Sevag Aredjian",
                "A publisher", 256, 0, "March 23, 2020");
        Controller c2 = new Controller(book2, new BookView(book2));
        c2.addBook();

        Book book3 = new Book("3", "The best C++ book", "George Orwell",
                "The best publisher", 34500, 0, "December 1, 1999");
        Controller c3 = new Controller(book3, new BookView(book3));
        c3.addBook();

        Book book4 = new Book("4", "How to make horrible burgers", "Ronald McDonald",
                "McDonald publishing company", 4554, 0, "June 26, 2006");
        Controller c4 = new Controller(book4, new BookView(book4));
        c4.addBook();

        Book book5 = new Book("5", "How to build cars", "Nissan Company",
                "Nissan book foundation", 1009, 0, "May 2, 2013");
        Controller c5 = new Controller(book5, new BookView(book5));
        c5.addBook();
    }

    /**
     * Test of addStudent method, of class Controller.
     */
    @Test
    public void testAddStudent() {
        System.out.println("testAddStudent\n");

        Student student = new Student("1", "Sevag", "123A$");
        Controller c = new Controller(student, new StudentView(student));
        c.addStudent();
        Student expStudent = Controller.getStudentById(c.getStId());
        assertEquals(expStudent.toString(), c.getStudent().toString());

    }

    /**
     * Test of searchBookByTitle method, of class Controller.
     */
    @Test
    public void testSearchBookByTitle() {
        System.out.println("testSearchBookByTitle");
        removeBooksFromDatabase();
        addBooksToDatabase();

        // Creating the controller
        Student student = new Student("2", "Sevag", "884bfu3");
        Controller c = new Controller(student, new StudentView(student));

        // Searching for the book w/beginning of title
        List<Book> results = c.searchBookByTitle("The best");
        Book book1 = Controller.getBookBySN("2");
        Book book2 = Controller.getBookBySN("3");
        List<Book> expResults = new ArrayList<>(Arrays.asList(book1, book2));
        assertEquals(expResults.toString(), results.toString());

        // Displaying case insesitivity
        results = c.searchBookByTitle("tHe BesT");
        assertEquals(expResults.toString(), results.toString());

        // Searching for the book w/middle strings of title
        results = c.searchBookByTitle("book");
        assertEquals(expResults.toString(), results.toString());
    }

    /**
     * Test of searchBookByName method, of class Controller.
     */
    @Test
    public void testSearchBookByName() {
        System.out.println("testSearchBookByName");
        removeBooksFromDatabase();
        addBooksToDatabase();

        // Creating the controller
        Student student = new Student("2", "Sevag", "884bfu3");
        Controller c = new Controller(student, new StudentView(student));

        // Searching for the book w/a common letter found in both authors
        List<Book> results = c.searchBookByName("g");
        Book book1 = Controller.getBookBySN("2");
        Book book2 = Controller.getBookBySN("3");
        List<Book> expResults = new ArrayList<>(Arrays.asList(book1, book2));
        assertEquals(expResults.toString(), results.toString());

        // Displaying case insesitivity
        results = c.searchBookByName("G");
        assertEquals(expResults.toString(), results.toString());

        // Searching for the book w/middle strings of title
        results = c.searchBookByName("evag");
        expResults.remove(book2);
        assertEquals(expResults.toString(), results.toString());
    }

    /**
     * Test of searchBookByYear method, of class Controller.
     */
    @Test
    public void testSearchBookByYear() {
        System.out.println("testSearchBookByYear");
        removeBooksFromDatabase();
        addBooksToDatabase();

        // Creating the controller
        Student student = new Student("2", "Sevag", "884bfu3");
        Controller c = new Controller(student, new StudentView(student));

        // Searching for the book w/a common substring of the year of both books
        List<Book> results = c.searchBookByYear("20");
        Book book1 = Controller.getBookBySN("1");
        Book book2 = Controller.getBookBySN("2");
        Book book3 = Controller.getBookBySN("4");
        Book book4 = Controller.getBookBySN("5");
        List<Book> expResults = new ArrayList<>(Arrays.asList(book1, book2, book3, book4));
        assertEquals(expResults.toString(), results.toString());

        // Searching for the book w/whole year
        results = c.searchBookByYear("2020");
        expResults.remove(book1);
        expResults.remove(book3);
        expResults.remove(book4);
        assertEquals(expResults.toString(), results.toString());

        // Searching for the book w/middle strings of year that does not exists
        results = c.searchBookByYear("011");
        expResults = new ArrayList<>();
        assertEquals(expResults.toString(), results.toString());
    }

    /**
     * Test of borrowBook method, of class Controller.
     */
    @Test
    public void testBorrowBook() {
        System.out.println("testBorrowBook");
        removeBooksFromDatabase();
        addBooksToDatabase();
        // Creating the controller
        Student student = new Student("cu9b4", "Tro", "$fn49");
        // getting a book added in the addBooksToDatabase();
        Book book = Controller.getBookBySN("1");
        Controller c = new Controller(book, new BookView(book), student, new StudentView(student));
        // Storing isssued qte of book
        Integer issuedQte = c.getIssuedQte();
        // Storing quantity of book
        Integer quantity = c.getQte();

        // Borrowing book1
        boolean result = c.borrowBook();
        assertEquals(true, result);

        // Checking increase in issued qte
        Integer issuedQteIncrease = issuedQte + 1;
        assertEquals(c.getIssuedQte(), issuedQteIncrease);
        // Checking decrease in book qte
        Integer qteDecreased = quantity - 1;
        assertEquals(c.getQte(), qteDecreased);

    }

    /**
     * Test of returnBookAsStudent method, of class Controller.
     */
    @Test
    public void testReturnBookAsStudent() {
        System.out.println("testReturnBookAsStudent");
        removeBooksFromDatabase();
        addBooksToDatabase();
        // Creating the controller
        Student student = new Student("cu9b4", "Tro", "$fn49");
        // getting a book added in the addBooksToDatabase();
        Book book = Controller.getBookBySN("1");
        Controller c = new Controller(book, new BookView(book), student, new StudentView(student));
        // Storing isssued qte of book
        Integer issuedQte = c.getIssuedQte();
        // Storing quantity of book
        Integer quantity = c.getQte();

        // Borrowing book1
        boolean result = c.returnBookAsStudent();
        assertEquals(true, result);

        // Checking increase in issued qte
        Integer issuedQteIncrease = issuedQte - 1;
        assertEquals(c.getIssuedQte(), issuedQteIncrease);
        // Checking decrease in book qte
        Integer qteDecreased = quantity + 1;
        assertEquals(c.getQte(), qteDecreased);
    }

    /**
     * Test of addBook method, of class Controller.
     */
    @Test
    public void testAddBook() {
        System.out.println("testAddBook\n");

        Book book = new Book("982bkjb", "The book", "The author", "THe publishier",
                34053, 35, "January 14, 2019");
        Controller c = new Controller(book, new BookView(book));
        c.addBook();
        Book expBook = Controller.getBookBySN(c.getSN());
        assertEquals(expBook.toString(), c.getBook().toString());
    }

    /**
     * Test of issueBook method, of class Controller.
     */
    @Test
    public void testIssueBook() {
        System.out.println("testIssueBook");
        removeBooksFromDatabase();
        addBooksToDatabase();
        // Creating the controller
        Student student = new Student("cu9b4", "Tro", "$fn49");
        // getting a book added in the addBooksToDatabase();
        Book book = Controller.getBookBySN("1");
        Controller c = new Controller(book, new BookView(book), student, new StudentView(student));
        // Storing isssued qte of book
        Integer issuedQte = c.getIssuedQte();
        // Storing quantity of book
        Integer quantity = c.getQte();

        // Borrowing book1
        boolean result = c.issueBook();
        assertEquals(true, result);

        // Checking increase in issued qte
        Integer issuedQteIncrease = issuedQte + 1;
        assertEquals(c.getIssuedQte(), issuedQteIncrease);
        // Checking decrease in book qte
        Integer qteDecreased = quantity - 1;
        assertEquals(c.getQte(), qteDecreased);
    }

    /**
     * Test of returnBook method, of class Controller.
     */
    @Test
    public void testReturnBook() {
        System.out.println("testReturnBook");
        removeBooksFromDatabase();
        addBooksToDatabase();
        // Creating the controller
        Student student = new Student("cu9b4", "Tro", "$fn49");
        // getting a book added in the addBooksToDatabase();
        Book book = Controller.getBookBySN("1");
        Controller c = new Controller(book, new BookView(book), student, new StudentView(student));
        // Storing isssued qte of book
        Integer issuedQte = c.getIssuedQte();
        // Storing quantity of book
        Integer quantity = c.getQte();

        // Borrowing book1
        boolean result = c.returnBook();
        assertEquals(true, result);

        // Checking increase in issued qte
        Integer issuedQteIncrease = issuedQte - 1;
        assertEquals(c.getIssuedQte(), issuedQteIncrease);
        // Checking decrease in book qte
        Integer qteDecreased = quantity + 1;
        assertEquals(c.getQte(), qteDecreased);
    }

    /**
     * Test of viewCatalog method, of class Controller.
     */
    @Test
    public void testViewCatalog() {
        System.out.println("testViewCatalog");
        Map<String, String> result = Controller.viewCatalog();
        Map<String, String> expResult = new TreeMap<>((String o1, String o2) -> {
            return o1.compareToIgnoreCase(o2);
        });
        Book book1 = Controller.getBookBySN("1");
        Book book2 = Controller.getBookBySN("2");
        Book book3 = Controller.getBookBySN("3");
        Book book4 = Controller.getBookBySN("4");
        Book book5 = Controller.getBookBySN("5");
        List<Book> expBooks = new ArrayList<>(Arrays.asList(book1, book2, book3,
                book4, book5));
        expBooks.forEach((Book b) -> {
            expResult.put(b.getSN(), "SN: " + b.getSN() + ", Title: " + b.getTitle()
                    + ", Author " + b.getAuthor()
                    + ", Publisher: " + b.getPublisher() + ", Quantity: "
                    + b.getQte() + ", Quantity Issued: "
                    + b.getIssuedQte() + ", Date Added: " + b.getDateOfPurchase());
        });
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of viewIssuedbooks method, of class Controller.
     */
    @Test
    public void testViewIssuedbooks() {
        System.out.println("testViewIssuedbooks");
        removeBooksFromDatabase();
        addBooksToDatabase();
        Book book = Controller.getBookBySN("2");
        Student student = new Student("gsg", "Tony", "oinobe4");

        Controller c = new Controller(book, new BookView(book), student, new StudentView(student));
        c.issueBook();

        Map<String, String> result = Controller.viewIssuedbooks();

        Map<String, String> expResult = new HashMap<>();
        expResult.put(c.getSN(), "SN: " + c.getSN() + ", ID: " + 1 + ", StId: " + student.getStId()
                + ", StName: " + student.getName() + ", Student Contact: "
                + student.getContactNumber() + ", Issued Date: "
                + DateTimeFormatter.ofPattern("dd-MM-yyyy").
                        format(LocalDateTime.now()));

        assertEquals(expResult.toString(), result.toString());
    }
}
