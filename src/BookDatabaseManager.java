import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDatabaseManager {
    static private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static private String DB_URL = "jdbc:mysql://localhost:3306/books";

    static private final String USER = "root";
    static private final String PASS = "gordie28";



    private List<Book> bookList = new ArrayList<>();
    private List<Author> authorList = new ArrayList<>();

    Connection conn = null;
    PreparedStatement pstmt = null;

    public List<Book> getBookList() throws SQLException {
        loadBooks();
        return bookList;}
    public List<Author> getAuthorList() throws SQLException {
        loadAuthors();
        return authorList;}

    private void loadAuthors() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            pstmt = conn.prepareStatement("SELECT * FROM authors");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Author author = new Author(rs.getString("lastName"), rs.getString("firstName"), rs.getInt("authorID"));
                pstmt = conn.prepareStatement("SELECT * FROM titles t INNER JOIN authorISBN i " +
                        "ON i.isbn = t.isbn INNER JOIN authors a " +
                        "ON a.authorID = i.authorID WHERE a.authorID = ?");
                pstmt.setInt(1, author.getId());
                ResultSet rs2 = pstmt.executeQuery();
                List<Book> bookListTemp = new ArrayList<>();
                while (rs2.next()) {
                    bookListTemp.add(new Book(rs2.getString("title"), rs2.getString("copyright"), rs2.getInt("editionNumber"), rs2.getString("isbn")));
                }
                author.setBookList(bookListTemp);
                authorList.add(author);
            }
            System.out.println("Authors loaded successfully.");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
    }
    private void loadBooks() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            pstmt = conn.prepareStatement("SELECT * FROM titles");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Book book = new Book(rs.getString("title"), rs.getString("copyright"), rs.getInt("editionNumber"), rs.getString("isbn"));
                pstmt = conn.prepareStatement("SELECT * FROM authors a INNER JOIN authorISBN i " +
                        "ON a.authorID = i.authorID INNER JOIN titles t " +
                        "ON i.isbn = t.isbn WHERE t.title = ?");
                pstmt.setString(1, book.getTitle());
                ResultSet rs2 = pstmt.executeQuery();
                List<Author> authorListTemp = new ArrayList<>();
                while (rs2.next()) {
                    authorListTemp.add(new Author(rs2.getString("lastName"), rs2.getString("firstName"), rs2.getInt("authorID")));
                }
                book.setAuthorList(authorListTemp);
                bookList.add(book);
            }
            System.out.println("Books loaded successfully.");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
    }
    private void loadDatabase() throws SQLException {
        loadBooks();
        loadAuthors();
        System.out.println("Database loaded successfully.");
    }
    public void addNewBook(Book book) throws SQLException {
        try {
            String new_isbn = book.getISBN();
            String new_title = book.getTitle();
            int new_editionNumber = book.getEdition();
            String new_copyright = book.getCopyright();

            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            String query = "INSERT into titles (isbn, title, editionNumber, copyright)" +
                    "VALUES (?, ?, ?, ?)";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, new_isbn);
            pstmt.setString(2, new_title);
            pstmt.setInt(3, new_editionNumber);
            pstmt.setString(4, new_copyright);
            pstmt.execute();

            System.out.println(new_title + " has been added to the database.");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
    }
    public void addNewAuthor(Author author) throws SQLException {
        try {
            String new_lastName = author.getLastName();
            String new_firstName = author.getFirstName();
            int new_id = author.getId();

            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            String query = "INSERT into authors (authorID, firstName, lastName)" +
                    "VALUES (?, ?, ?)";

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, new_id);
            pstmt.setString(2, new_firstName);
            pstmt.setString(3, new_lastName);
            pstmt.execute();

            System.out.println(new_firstName + " " + new_lastName + " has been added to the database.");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
    }

}
