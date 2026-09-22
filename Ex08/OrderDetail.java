package Ex08;

public class OrderDetail {

    private int id;
    private int productId;
    private int orderId;
    private int quantity;
    private double priceSell;

    public OrderDetail() {
    }

    public OrderDetail(
            int id,
            int productId,
            int orderId,
            int quantity,
            double priceSell) {

        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.priceSell = priceSell;
    }

    public OrderDetail(
            int productId,
            int quantity,
            double priceSell) {

        this.productId = productId;
        this.quantity = quantity;
        this.priceSell = priceSell;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(double priceSell) {
        this.priceSell = priceSell;
    }
}
