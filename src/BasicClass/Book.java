package BasicClass;

public class Book {
    private String bookName;
    private String bookId;
    private float bookPrice;
    private int bookNum;

    public Book(String bookId, String bookName, float bookPrice, int bookNum){
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookNum = bookNum;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookId() {
        return bookId;
    }

    public float getBookPrice() {
        return bookPrice;
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }
}
