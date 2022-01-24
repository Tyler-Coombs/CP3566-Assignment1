import java.util.List;

public class Author {
    private List<Book> bookList;
    private String lastName;
    private String firstName;
    private int id;

    public String getLastName() {
        return lastName;}
    public void setLastName(String lastName) {
        this.lastName = lastName;}

    public String getFirstName() {
        return firstName;}
    public void setFirstName(String firstName) {
        this.firstName = firstName;}

    public List<Book> getBookList() {
        return bookList;}
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;}

    public int getId() {
        return id;}
    public void setId(int id) {
        this.id = id;}

    public Author(String lastName, String firstName, int id) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
    }

    public String toString() {
        return "Last Name: " + getLastName()
                + "\nFirst Name: " + getFirstName()
                + "\nAuthor ID: " + getId()
                + "\nBooks: " + getBookList();
    }
}
