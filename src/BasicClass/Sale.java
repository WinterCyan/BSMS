package BasicClass;

public class Sale {
    private String saleId;
    private String bookId;
    private String seller;
    private int saleNum;

    public Sale(String bookId, String seller, int saleNum){
//        this.saleId = saleId;
        this.bookId = bookId;
        this.seller = seller;
        this.saleNum = saleNum;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }
}
