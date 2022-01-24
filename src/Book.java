import java.util.List;

public class Book {
    private List<Author> authorList;
    private String title;
    private String copyright;
    private int edition;
    private String ISBN;

    public String getTitle() {
        return title;}
    public void setTitle(String title) {
        this.title = title;}

    public List<Author> getAuthorList() {
        return authorList;}
    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;}

    public String getCopyright() {
        return copyright;}
    public void setCopyright(String copyright) {
        this.copyright = copyright;}

    public int getEdition() {
        return edition;}
    public void setEdition(int edition) {
        this.edition = edition;}

    public String getISBN() {
        return ISBN;}
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;}

    public Book(String title, String copyright, int edition, String ISBN) {
        this.title = title;
        this.copyright = copyright;
        this.edition = edition;
        this.ISBN = ISBN;
    }

    public String toString() {
        return "Title: " + getTitle()
                + "\nAuthors: " + getAuthorList()
                + "\nEdition: " + getEdition()
                + "\nCopyright: " + getCopyright()
                + "\nISBN: " + getISBN();
    }
}
