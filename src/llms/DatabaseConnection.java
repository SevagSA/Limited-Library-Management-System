package llms;

import java.sql.*;

/**
 * Class that creates the database connection. This class implements the
 * Singleton pattern, and thus can only be instantiated once.
 *
 * @author Sevag Saro Aredjian
 */
public class DatabaseConnection {

    private static Connection con;

    private DatabaseConnection() {
    }

    /**
     * To connect to the database by setting the con Connection object attribute
     * to connect with the LLMS database.
     *
     * @return The con attribute with a connection to the database. All calls
     * following the first one will return the same Connection object as the
     * first call's.
     */
    public static Connection getInstance() {
        if (con == null) {
            con = connectToDatabase();
        }
        return con;
    }

    /**
     * Returns a Connection object of the LLMS database.
     *
     * @return The Connection object of the LLMS database.
     */
    private static Connection connectToDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(
                    "jdbc:sqlite:/Applications/sqlite/db/LLMS.db");
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage()
                    + " : method: connectToDatabase()");
            System.exit(0);
        }
        return con;
    }
}
