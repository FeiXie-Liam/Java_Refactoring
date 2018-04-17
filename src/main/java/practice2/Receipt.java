package practice2;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

    public Receipt() {
        tax = new BigDecimal(0.1);
        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal tax;

    public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = calculateSubtotal(products, items);

        for (Product product : products) {
            OrderItem curItem = product.findOrderItemByProduct(items);

            subTotal = subTotal.subtract(getReducedPrice(product, curItem));
        }

        return addTaxPrice(subTotal).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private BigDecimal addTaxPrice(BigDecimal subTotal) {
        BigDecimal taxTotal = subTotal.multiply(tax);
        return subTotal.add(taxTotal);
    }

    private BigDecimal getReducedPrice(Product product, OrderItem curItem) {
        return product.getPrice()
                        .multiply(product.getDiscountRate())
                        .multiply(new BigDecimal(curItem.getCount()));
    }

    private BigDecimal calculateSubtotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = new BigDecimal(0);
        for (Product product : products) {
            OrderItem item = product.findOrderItemByProduct(items);
            BigDecimal itemTotal = product.getPrice().multiply(new BigDecimal(item.getCount()));
            subTotal = subTotal.add(itemTotal);
        }
        return subTotal;
    }
}
