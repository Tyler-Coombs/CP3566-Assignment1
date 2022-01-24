import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BookApplication {
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        BookDatabaseManager db = new BookDatabaseManager();

        System.out.println("BOOK LIBRARY");
        int userInput = 0;

        while (true) {
            try {
                System.out.println("\nMAIN MENU \n1. Show Books \n2. Show Authors " +
                        "\n3. Add a Book \n4. Add an Author \n5. Quit \n");
                System.out.print("Please select one of the options above by entering the corresponding number: ");
                userInput = input.nextInt();
                System.out.println("");
                if (userInput == 1) {
                    List<Book> bookList = db.getBookList();
                    Iterator iter = bookList.iterator();
                    while (iter.hasNext()) {
                        System.out.println(iter.next());
                        System.out.println("");
                    }
                    continue;
                } else if (userInput == 2) {
                    List<Author> authorList = db.getAuthorList();
                    Iterator iter = authorList.iterator();
                    while (iter.hasNext()) {
                        System.out.println(iter.next());
                        System.out.println("");
                    }
                    continue;
                } else if (userInput == 3) {
                    System.out.print("Book Title: ");
                    String title = input.next();
                    System.out.print("ISBN: ");
                    String isbn = input.next();
                    System.out.print("Edition: ");
                    int edition = input.nextInt();
                    System.out.print("Copyright: ");
                    String copyright = input.next();

                    Book book = new Book(title, copyright, edition, isbn);
                    db.addNewBook(book);
                    continue;
                } else if (userInput == 4) {
                    System.out.print("Last Name: ");
                    String lastName = input.next();
                    System.out.println("First Name: ");
                    String firstName = input.next();
                    int id = db.getAuthorList().size() + 1;

                    Author author = new Author(lastName, firstName, id);
                    db.addNewAuthor(author);
                    continue;
                } else if (userInput == 5) {
                    System.out.println("Thank you. Goodbye.");
                    break;
                } else {
                    System.out.println("Invalid selection. Please try again.");
                    continue;
                }

            } catch (InputMismatchException inputMismatchException) {
                System.out.println(inputMismatchException.toString());
            } catch (SQLException sqlException) {
                System.out.println(sqlException.toString());
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        
    }
}
