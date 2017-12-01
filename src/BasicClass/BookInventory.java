package BasicClass;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BookInventory {
    private SimpleStringProperty id, name;
    private SimpleIntegerProperty num;

    public BookInventory(String id, String name, int num){
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.num = new SimpleIntegerProperty(num);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public int getNum() {
        return num.get();
    }

    public SimpleIntegerProperty numProperty() {
        return num;
    }
}
