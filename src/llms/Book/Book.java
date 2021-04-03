package llms.Book;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;
import llms.Controller;
import llms.DatabaseConnection;
import llms.Student.Student;
import llms.Student.StudentView;

/**
 * Class of book.
 *
 * @author Sevag Saro Aredjian
 */
public class Book {

    private static final Connection CON = DatabaseConnection.getInstance();

    private String SN;
    private String title;
    private String author;
    private String publisher;
    private Integer qte;
    private Integer issuedQte;
    private String dateOfPurchase;

    /**
     * Book constructor with all attributes.
     *
     * @param serialNumber The book's serial number.
     * @param title The book's title.
     * @param author The book's author.
     * @param publisher The book's publisher.
     * @param quantity The book's quantity.
     * @param issued The book's issued quantity.
     * @param dateOfPurchase The book's date of purchase.
     */
    public Book(String serialNumber, String title, String author, String publisher,
            Integer quantity, Integer issued, String dateOfPurchase) {
        this.SN = serialNumber;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.qte = quantity;
        this.issuedQte = issued;
        // if an empty string is passed ("") i.e. the user did not input a date
        // from the GUI, the value null should be stored instead of ""
        this.dateOfPurchase = dateOfPurchase.isEmpty() ? null : dateOfPurchase;
    }

    /**
     * To add a book to the Books table in the database.
     *
     * @param book The book that will be added.
     */
    public void addBook(Book book) {
        Controller c = new Controller(book, new BookView(book));
        try (Statement stmt = CON.createStatement()) {
            String insertQuery
                    = "INSERT INTO Books (SN, Title, Author, Publisher, Quantity, "
                    + "Issued, addedDate) "
                    + "VALUES ('" + c.getSN() + "', '" + c.getTitle() + "', '"
                    + c.getAuthor() + "', '" + c.getPublisher() + "', " + c.getQte()
                    + ", " + c.getIssuedQte() + ", '" + c.getDateOfPurchase() + "');";
            stmt.executeUpdate(insertQuery);
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage()
                    + " : method: addBook()");
            System.exit(0);
        }
    }

    /**
     * To issue a book to a student.
     *
     * @param book The book that will be issued.
     * @param student The student that the book will be issued to.
     * @return True if the book issue was successful, false if not.
     */
    public boolean issueBook(Book book, Student student) {
        Controller c = new Controller(book, new BookView(book), student,
                new StudentView(student));
        if (c.getIssuedQte() < c.getQte()) {
            c.setQuantity(c.getQte() - 1);
            c.setIssuedQte(c.getIssuedQte() + 1);
            try (Statement stmt = CON.createStatement()) {
                String query
                        = "INSERT INTO IssuedBooks (SN, StId, StName, StudentContact, IssuedDate) "
                        + "VALUES ('" + c.getSN() + "', '" + c.getStId() + "', '"
                        + c.getName() + "', '" + c.getContactNumber()
                        + "', '" + DateTimeFormatter.ofPattern("dd-MM-yyyy").
                                format(LocalDateTime.now()) + "');";
                stmt.executeUpdate(query);
                query = "UPDATE Books SET Quantity = " + c.getQte() + ", "
                        + " Issued = " + c.getIssuedQte() + " WHERE "
                        + " SN = '" + c.getSN() + "' ";
                stmt.executeUpdate(query);
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage()
                        + " : method: issueBook()");
                System.exit(0);
                return false;
            }
        }
        return true;
    }

    /**
     * To return a book.
     *
     * @param book The book that will be returned.
     * @param student The student that is returning the book.
     * @return True if the book return was successful, false if not.
     */
    public boolean returnBook(Book book, Student student) {
        Controller c = new Controller(book, new BookView(book), student,
                new StudentView(student));
        try (Statement stmt = CON.createStatement()) {
            c.setQuantity(c.getQte() + 1);
            c.setIssuedQte(c.getIssuedQte() - 1);
            String deleteBooksQuery = "DELETE FROM IssuedBooks "
                    + "WHERE SN = '" + c.getSN() + "' AND"
                    + " StId = '" + c.getStId() + "';";
            stmt.executeUpdate(deleteBooksQuery);
            String updateBookQuery = "UPDATE Books SET Quantity = "
                    + c.getQte() + ", Issued = " + c.getIssuedQte() + " "
                    + "WHERE SN = '" + c.getSN() + "' ";
            stmt.executeUpdate(updateBookQuery);
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage()
                    + " : method: returnBook()");
            System.exit(0);
            return false;
        }
        return true;

    }

    /**
     * To view the entire catalog of books available in the library.
     *
     * @return A Map that contains all book sorted by their serial number and
     * where the serial number is the key and the book's information is the
     * value.
     */
    public static Map<String, String> viewCatalog() {
        TreeMap<String, String> catalogBooksMap = new TreeMap<>((String o1, String o2) -> {
            return o1.compareToIgnoreCase(o2);
        });
        try (Statement stmt = CON.createStatement()) {
            String getBooksQuery = "SELECT * FROM Books";
            ResultSet rs = stmt.executeQuery(getBooksQuery);
            while (rs.next()) {
                catalogBooksMap.put(rs.getString("SN"),
                        "SN: " + rs.getString("SN") + ", Title: " + rs.getString("Title")
                        + ", Author " + rs.getString("Author") + ", Publisher: "
                        + rs.getString("Publisher") + ", Quantity: "
                        + rs.getInt("Quantity") + ", Quantity Issued: "
                        + rs.getInt("Issued") + ", Date Added: " + rs.getString("addedDate"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage()
                    + " : method: viewCatalog()");
            System.exit(0);
        }
        return catalogBooksMap;
    }

    /**
     * To view all issued books in the library.
     *
     * @return A Map that contains all issued books sorted by their serial
     * number and where the serial number is the key and the book's information
     * is the value.
     */
    public static Map<String, String> viewIssuedBooks() {
        TreeMap<String, String> issuedBooksMap = new TreeMap<>((String o1, String o2) -> {
            return o1.compareToIgnoreCase(o2);
        });
        try (Statement stmt = CON.createStatement()) {
            String selectQuery = "SELECT * FROM IssuedBooks";
            ResultSet rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                issuedBooksMap.put(rs.getString("SN"),
                        "SN: " + rs.getString("SN") + ", ID: " + rs.getInt("id") + ", StId: " + rs.getString("StId")
                        + ", StName: " + rs.getString("StName") + ", Student Contact: "
                        + rs.getString("StudentContact") + ", Issued Date: "
                        + rs.getString("IssuedDate"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage()
                    + " : method: viewIssuedBooks()");
            System.exit(0);
        }
        return issuedBooksMap;
    }

    /**
     * To view all issued books in the library.
     *
     * @param studentId The id of the student whose issued books will be
     * returned.
     * @return A Map that contains all issued books sorted by their serial
     * number and where the serial number is the key and the book's information
     * is the value.
     */
    public static Map<String, String> viewIssuedBooks(String studentId) {
        TreeMap<String, String> issuedBooksMap = new TreeMap<>((String o1, String o2) -> {
            return o1.compareToIgnoreCase(o2);
        });
        try (Statement stmt = CON.createStatement()) {
            String selectQuery = "SELECT * FROM IssuedBooks WHERE StId = '" + studentId + "'";
            ResultSet rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                issuedBooksMap.put(rs.getString("SN"),
                        "SN: " + rs.getString("SN") + ", ID: " + rs.getInt("id")
                        + ", StId: " + rs.getString("StId") + ", StName: "
                        + rs.getString("StName") + ", Student Contact: "
                        + rs.getString("StudentContact") + ", Issued Date: "
                        + rs.getString("IssuedDate"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage()
                    + " : method: viewIssuedBooks()");
            System.exit(0);
        }
        return issuedBooksMap;
    }

    /**
     * To get a Map of all the available books to borrow in the library. Used to
     * display all books that can be borrowed in the GUI.
     *
     * @return A Map that contains all available books to borrow sorted by their
     * serial number and where the serial number is the key and the book's
     * information is the value.
     */
    public static Map<String, String> getAvailableBooks() {
        TreeMap<String, String> availableBooksMap = new TreeMap<>((String o1, String o2) -> {
            return o1.compareToIgnoreCase(o2);
        });
        try (Statement stmt = CON.createStatement()) {
            String getBooksQuery = "SELECT * FROM Books WHERE Issued<Quantity";
            ResultSet rs = stmt.executeQuery(getBooksQuery);
            while (rs.next()) {
                availableBooksMap.put(rs.getString("SN"),
                        "SN: " + rs.getString("SN") + ", Title: " + rs.getString("Title")
                        + ", Author: " + rs.getString("Author")
                        + ", Publisher: " + rs.getString("Publisher") + ", Quantity: "
                        + rs.getInt("Quantity") + ", Quantity Issued: "
                        + rs.getInt("Issued") + ", Date Added: " + rs.getString("addedDate"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage()
                    + " : method: getAvailableBooks()");
            System.exit(0);
        }
        return availableBooksMap;
    }

    /**
     * To get a book by its serial number.
     *
     * @param sn The serial number of the book that will be returned from the
     * database.
     * @return The book with the requested serial number. If a book with the
     * requested serial number does not exists, a book with null values will be
     * returned.
     */
    public static Book getBookBySN(String sn) {
        Book book = new Book(null, null, null, null, null, null, "");
        try (Statement stmt = CON.createStatement()) {
            String getBooksQuery = "SELECT * FROM Books WHERE SN = '" + sn + "'";
            ResultSet rs = stmt.executeQuery(getBooksQuery);
            while (rs.next()) {
                book.setSN(rs.getString("SN"));
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setPublisher(rs.getString("Publisher"));
                book.setQuantity(rs.getInt("Quantity"));
                book.setIssuedQte(rs.getInt("Issued"));
                book.setDateOfPurchase(rs.getString("addedDate"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage()
                    + " : method: getBookBySN()");
            System.exit(0);
        }
        return book;
    }

    @Override
    public String toString() {
        return "Book{" + "serialNumber=" + SN + ", title=" + title
                + ", author=" + author + ", publisher=" + publisher
                + ", quantity=" + qte + ", issuedQte=" + issuedQte
                + ", dateOfPurchase=" + dateOfPurchase + '}';
    }

    /**
     * Getter.
     *
     * @return The book's serial number.
     */
    public String getSN() {
        return SN;
    }

    /**
     * Setter.
     *
     * @param SN The book's serial number.
     */
    public void setSN(String SN) {
        this.SN = SN;
    }

    /**
     * Getter.
     *
     * @return The book's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter.
     *
     * @param title The book's title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter.
     *
     * @return The book's author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Setter.
     *
     * @param author The book's author.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Getter.
     *
     * @return The book's publisher.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Setter.
     *
     * @param publisher The book's publisher.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Getter.
     *
     * @return The book's quantity.
     */
    public Integer getQte() {
        return qte;
    }

    /**
     * Setter.
     *
     * @param quantity The book's quantity.
     */
    public void setQuantity(int quantity) {
        this.qte = quantity;
    }

    /**
     * Getter.
     *
     * @return The book's issued quantity.
     */
    public Integer getIssuedQte() {
        return issuedQte;
    }

    /**
     * Setter.
     *
     * @param issuedQte The book's issued quantity.
     */
    public void setIssuedQte(int issuedQte) {
        this.issuedQte = issuedQte;
    }

    /**
     * Getter.
     *
     * @return The book's date of purchase.
     */
    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    /**
     * Setter.
     *
     * @param dateOfPurchase The book's date of purchase.
     */
    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
}
