package llms;

import llms.Student.Student;
import llms.Student.StudentView;
import llms.Book.BookView;
import llms.Book.Book;
import java.util.List;
import java.util.Map;

/**
 * Controller class for the LLMS project.
 *
 * @author Sevag Saro Aredjian
 */
public class Controller {

    private Book book;
    private BookView bookView;
    private Student student;
    private StudentView studentView;

    /**
     * Controller constructor with only book related parameters. This
     * constructor is used when only book related methods are to be used.
     *
     * @param book The book that will be used to conduct the methods.
     * @param bookView The object that will display the changes done by the
     * invoked methods on the book.
     */
    public Controller(Book book, BookView bookView) {
        this.book = book;
        this.bookView = bookView;
    }

    /**
     * Controller constructor with only student related parameters. This
     * constructor is used when only student related methods are to be used.
     *
     * @param student The student that will be used to conduct the methods.
     * @param studentView The object that will display the changes done by the
     * invoked methods on the student.
     */
    public Controller(Student student, StudentView studentView) {
        this.student = student;
        this.studentView = studentView;
    }

    /**
     * Controller constructor with book and student related parameters. This
     * constructor is used when book and student related methods are to be used.
     *
     * @param book The book that will be used to conduct the methods.
     * @param bookView The object that will display all book related changes
     * done by the invoked methods on the book.
     * @param student The student that will be used to conduct the methods.
     * @param studentView The object that will display all student related
     * changes done by the invoked methods on the student.
     */
    public Controller(Book book, BookView bookView, Student student, StudentView studentView) {
        this.book = book;
        this.bookView = bookView;
        this.student = student;
        this.studentView = studentView;
    }

    // Student methods
    /**
     * To add a student to the Students table in the database.
     */
    public void addStudent() {
        student.addStudent(student);
    }

    /**
     * To search a book by its title.
     *
     * @param title The title that will be searched.
     * @return A list of books ordered by their serial number that have a title
     * that matches the input title.
     */
    public List<Book> searchBookByTitle(String title) {
        return student.searchBookByTitle(title);
    }

    /**
     * To search a book by its name.
     *
     * @param name The name that will be searched.
     * @return A list of books ordered by their serial number that have a name
     * that matches the input name.
     */
    public List<Book> searchBookByName(String name) {
        return student.searchBookByName(name);
    }

    /**
     * To search a book by its year.
     *
     * @param year The year that will be searched.
     * @return A list of books ordered by their serial number that have a year
     * that matches the input year.
     */
    public List<Book> searchBookByYear(String year) {
        return student.searchBookByYear(year);
    }

    /**
     * To borrow a book.
     *
     * @return True if the borrow was successful, false if otherwise.
     */
    public boolean borrowBook() {
        return student.borrowBook(book);
    }

    /**
     * To return a book from the books that the student has borrowed.
     *
     * @return True if the return was successful, false if otherwise.
     */
    public boolean returnBookAsStudent() {
        return student.returnBook(book);
    }

    /**
     * To get a Map of all students.
     *
     * @return A Map of all students where the student is the key, and the value
     * is a string containing all the student's information.
     */
    public static Map<String, String> getAllStudents() {
        return Student.getAllStudents();
    }

    /**
     * To get a Map of all students that have issued a book.
     *
     * @return A Map of all students that have issued a book where the student
     * is the key, and the value is a string containing all the student's
     * information.
     */
    public static Map<String, String> getAllIssuedStudents() {
        return Student.getAllIssuedStudents();
    }

    /**
     * To get a student by their id.
     *
     * @param id The id of the student that we want to get.
     * @return The student that has the input id. A student with null values
     * will return if a student with the input id does not exists.
     */
    public static Student getStudentById(String id) {
        return Student.getStudentById(id);
    }

    /**
     * To display the student frame in the GUI allowing the students to see
     * their account details.
     */
    public void displayStudentDetails() {
        studentView.displayStudentView("StudentDetails", null);
    }

    /**
     * To display the search frame in the GUI allowing the student to search for
     * a book.
     */
    public void displaySearchFrame() {
        studentView.displayStudentView("SearchFrame", null);
    }

    /**
     * To display the borrow frame in the GUI allowing a student to borrow a
     * book.
     */
    public void displayBorrowFrame() {
        studentView.displayStudentView("BorrowFrame", null);
    }

    /**
     * To display a notification message of the most recent action of the
     * student. The notification message is displayed in the student main frame.
     *
     * @param notification The notification message that will be displayed to
     * the student.
     */
    public void displayStudentNotification(String notification) {
        studentView.displayStudentView("Notification", notification);
    }

    /**
     * To display the login GUI form to the student.
     */
    public void displayStudentLogin() {
        studentView.displayStudentView("Login", null);
    }

    /**
     * To display the register GUI form to the student.
     */
    public void displayStudentRegister() {
        studentView.displayStudentView("Register", null);
    }

    /**
     * Getter.
     *
     * @return The student's id.
     */
    public String getStId() {
        return student.getStId();
    }

    /**
     * Setter.
     *
     * @param stId The student's id.
     */
    public void setStId(String stId) {
        student.setStId(stId);
    }

    /**
     * Getter.
     *
     * @return The student's name.
     */
    public String getName() {
        return student.getName();
    }

    /**
     * Setter.
     *
     * @param name The student's name.
     */
    public void setName(String name) {
        student.setName(name);
    }

    /**
     * Getter.
     *
     * @return The student's contact number.
     */
    public String getContactNumber() {
        return student.getContactNumber();
    }

    /**
     * Setter.
     *
     * @param contactNumber The student's contact number.
     */
    public void setContactNumber(String contactNumber) {
        student.setContactNumber(contactNumber);
    }

    // Book methods
    /**
     * To add a book to the Books table in the database.
     */
    public void addBook() {
        book.addBook(book);
    }

    /**
     * To issue a book to a student.
     *
     * @return True if the book issue was successful, false if not.
     */
    public boolean issueBook() {
        return book.issueBook(book, student);
    }

    /**
     * To return a book.
     *
     * @return True if the book return was successful, false if not.
     */
    public boolean returnBook() {
        return book.returnBook(book, student);
    }

    /**
     * To view the entire catalog of books available in the library.
     *
     * @return A Map that contains all book sorted by their serial number and
     * where the serial number is the key and the book's information is the
     * value.
     */
    public static Map<String, String> viewCatalog() {
        return Book.viewCatalog();
    }

    /**
     * To view all issued books in the library.
     *
     * @return A Map that contains all issued books sorted by their serial
     * number and where the serial number is the key and the book's information
     * is the value.
     */
    public static Map<String, String> viewIssuedbooks() {
        return Book.viewIssuedBooks();
    }

    /**
     * To view all issued books in the library.
     *
     * @param studentId The id of the student whose issued books will be
     * returned.
     * @return A Map that contains all issued books of the student with the
     * input studentId sorted by their serial number and where the serial number
     * is the key and the book's information is the value.
     */
    public static Map<String, String> viewIssuedbooks(String studentId) {
        return Book.viewIssuedBooks(studentId);
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
        return Book.getAvailableBooks();
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
        return Book.getBookBySN(sn);
    }

    /**
     * Getter.
     *
     * @return The book's serial number.
     */
    public String getSN() {
        return book.getSN();
    }

    /**
     * Setter.
     *
     * @param serialNumber The book's serial number.
     */
    public void setSerialNumber(String serialNumber) {
        book.setSN(serialNumber);
    }

    /**
     * Getter.
     *
     * @return The book's title.
     */
    public String getTitle() {
        return book.getTitle();
    }

    /**
     * Setter.
     *
     * @param title The book's title.
     */
    public void setTitle(String title) {
        book.setTitle(title);
    }

    /**
     * Getter.
     *
     * @return The book's author.
     */
    public String getAuthor() {
        return book.getAuthor();
    }

    /**
     * Setter.
     *
     * @param author The book's author.
     */
    public void setAuthor(String author) {
        book.setAuthor(author);
    }

    /**
     * Getter.
     *
     * @return The book's publisher.
     */
    public String getPublisher() {
        return book.getPublisher();
    }

    /**
     * Setter.
     *
     * @param publisher The book's publisher.
     */
    public void setPublisher(String publisher) {
        book.setPublisher(publisher);
    }

    /**
     * Getter.
     *
     * @return The book's quantity.
     */
    public Integer getQte() {
        return book.getQte();
    }

    /**
     * Setter.
     *
     * @param quantity The book's quantity.
     */
    public void setQuantity(int quantity) {
        book.setQuantity(quantity);
    }

    /**
     * Getter.
     *
     * @return The book's issued quantity.
     */
    public Integer getIssuedQte() {
        return book.getIssuedQte();
    }

    /**
     * Setter.
     *
     * @param issuedQte The book's issued quantity.
     */
    public void setIssuedQte(int issuedQte) {
        book.setIssuedQte(issuedQte);
    }

    /**
     * Getter.
     *
     * @return The book's date of purchase.
     */
    public String getDateOfPurchase() {
        return book.getDateOfPurchase();
    }

    /**
     * Setter.
     *
     * @param dateOfPurchase The book's date of purchase.
     */
    public void setDateOfPurchase(String dateOfPurchase) {
        book.setDateOfPurchase(dateOfPurchase);
    }

    /**
     * To display the catalog frame in the GUI allowing the librarian to see the
     * book's details.
     */
    public void displayBookDetails() {
        bookView.displayBookView("BookDetails");
    }

    /**
     * To display the issued book frame in the GUI allowing the librarian to see
     * the details of the most recent book that was issued.
     */
    public void displayIssuedBook() {
        bookView.displayBookView("ViewIssuedBook");
    }

    /**
     * To display the add book GUI form allowing librarians to add a new book to
     * the database.
     */
    public void displayAddBook() {
        bookView.displayBookView("AddBook");
    }

    /**
     * To display the issue book GUI form allowing librarians to issue a book to
     * a student.
     */
    public void displayIssueBookForm() {
        bookView.displayBookView("IssueForm");
    }

    /**
     * To display all issued books in the GUI.
     */
    public void displayIssueBook() {
        bookView.displayBookView("AllIssuedBooks");
    }

    /**
     * Getter.
     *
     * @return The controller's book.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Setter.
     *
     * @param book The controller's book.
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Getter.
     *
     * @return The controller's book view.
     */
    public BookView getBookView() {
        return bookView;
    }

    /**
     * Setter.
     *
     * @param bookView The controller's book view.
     */
    public void setBookView(BookView bookView) {
        this.bookView = bookView;
    }

    /**
     * Getter.
     *
     * @return The controller's student.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Setter.
     *
     * @param student The controller's student.
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Getter.
     *
     * @return The controller's student view.
     */
    public StudentView getStudentView() {
        return studentView;
    }

    /**
     * Setter.
     *
     * @param studentView The controller's student view.
     */
    public void setStudentView(StudentView studentView) {
        this.studentView = studentView;
    }
}
