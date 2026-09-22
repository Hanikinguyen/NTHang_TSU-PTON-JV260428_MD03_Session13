package Ex08;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private int customerId;
    private Date orderDate;
    private double totalAmount;

    private List<OrderDetail> orderDetails =
            new ArrayList<>();

    public Order() {
    }

    public Order(
            int id,
            int customerId,
            Date orderDate,
            double totalAmount) {

        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public Order(
            int customerId,
            Date orderDate,
            List<OrderDetail> orderDetails) {

        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderDetails = orderDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(
            List<OrderDetail> orderDetails) {

        this.orderDetails = orderDetails;
    }
}
