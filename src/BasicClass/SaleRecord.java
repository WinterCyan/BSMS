package BasicClass;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SaleRecord {
    private Book book;
    private Sale sale;
    private SimpleStringProperty name, seller;
    private SimpleIntegerProperty ord, num;
    private SimpleFloatProperty price, total;

    public SaleRecord(Book book, Sale sale, int ordNum){
        this.sale = sale;
        this.book = book;

        this.ord = new SimpleIntegerProperty(ordNum);
//        this.id = new SimpleStringProperty(sale.getSaleId());
        this.name = new SimpleStringProperty(book.getBookName());
        this.seller = new SimpleStringProperty(sale.getSeller());
        this.num = new SimpleIntegerProperty(sale.getSaleNum());
        this.price = new SimpleFloatProperty(book.getBookPrice());
        this.total = new SimpleFloatProperty((sale.getSaleNum() * book.getBookPrice()));
    }

    public Book getBook() {
        return book;
    }

    public Sale getSale() {
        return sale;
    }

//    public String getId() {
//        return id.get();
//    }
//
//    public SimpleStringProperty idProperty() {
//        return id;
//    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getSeller() {
        return seller.get();
    }

    public SimpleStringProperty sellerProperty() {
        return seller;
    }

    public int getOrd() {
        return ord.get();
    }

    public SimpleIntegerProperty ordProperty() {
        return ord;
    }

    public int getNum() {
        return num.get();
    }

    public SimpleIntegerProperty numProperty() {
        return num;
    }

    public float getPrice() {
        return price.get();
    }

    public SimpleFloatProperty priceProperty() {
        return price;
    }

    public float getTotal() {
        return total.get();
    }

    public SimpleFloatProperty totalProperty() {
        return total;
    }
}
