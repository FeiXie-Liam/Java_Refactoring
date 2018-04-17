package practice2;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    private BigDecimal price;
    private BigDecimal availableDiscounts;
    private long code;

    public Product(long code, double price, double discount) {
        this.code = code;
        this.price = new BigDecimal(price);
        this.availableDiscounts = new BigDecimal(discount);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getDiscountRate() {
        return availableDiscounts;
    }

    public long getCode() {
        return code;
    }

    public OrderItem findOrderItemByProduct(List<OrderItem> items) {
        OrderItem curItem = null;
        for (OrderItem item : items) {
            if (item.getCode() == this.getCode()) {
                curItem = item;
                break;
            }
        }
        return curItem;
    }
}
