import java.util.UUID;

@XMLable
public class Order {
    @XMLfield(type = "String", name = "ID")
    private String id;
    
    @XMLfield(type = "double")
    public double price;

    @XMLfield(type = "String")
    public String timestamp;

    @XMLfield(type = "boolean")
    public boolean shipped;

    public Order(double price, String timestamp, boolean shipped) {
        this.id = UUID.randomUUID().toString();
        this.price = price;
        this.timestamp = timestamp;
        this.shipped = shipped;
    }

    public Order() {}
}
