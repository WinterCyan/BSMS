package BasicClass;

public class Book {
    public static final int BOOD_ID_LENGTH = 6;
    public static final int SELLER_LENGTH = 10;

    private String bookName;
    private String bookId;
    private float bookPrice;
    private int bookNum;
    private int sale;

    public Book(String bookId, String bookName, float bookPrice, int bookNum, int sale){
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookNum = bookNum;
        this.sale = sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public int getSale() {
        return sale;
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
