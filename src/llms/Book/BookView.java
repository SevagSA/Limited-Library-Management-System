package llms.Book;

import llms.GUI.Librarian.AddBookFrame;
import llms.GUI.Librarian.ViewIssuedBookFrame;
import llms.GUI.Librarian.IssueBookFrame;
import llms.GUI.ViewCatalogFrame;

/**
 * Class for book view implementing the factory design pattern.
 *
 * @author Sevag Saro Aredjian
 */
public class BookView {

    private final Book book;

    /**
     * Constructor for Book view.
     *
     * @param book The book who's values will be displayed.
     */
    public BookView(Book book) {
        this.book = book;
    }

    public void displayBookView(String displayType) {
        switch (displayType) {
            case "BookDetails":
                new ViewCatalogFrame(book).setVisible(true);
                break;
            case "ViewIssuedBook":
                new ViewIssuedBookFrame(book).setVisible(true);
                break;
            case "AddBook":
                new AddBookFrame().setVisible(true);
                break;
            case "IssueForm":
                new IssueBookFrame().setVisible(true);
                break;
            case "AllIssuedBooks":
                new ViewIssuedBookFrame().setVisible(true);
                break;
            default:
                System.err.println("Unkown display type");
        }
    }
}
