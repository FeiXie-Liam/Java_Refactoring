package practice3;

import java.math.BigDecimal;

public class PriceCaculator {
    private final Order _order;
    private BigDecimal subTotal;
    private BigDecimal tax;

    public PriceCaculator(Order order) {
        _order = order;
    }

    public BigDecimal calculate() {
        subTotal = new BigDecimal(0);

        sumOfLineItems();

        subDiscounts();

        calcTax();

        // calculate GrandTotal

        return subTotal.add(tax);
    }

    private void calcTax() {
        // calculate tax
        tax = subTotal.multiply(_order.getTax());
    }

    private void subDiscounts() {
        // Subtract discounts
        for (BigDecimal discount : _order.getDiscounts()) {
            subTotal = subTotal.subtract(discount);
        }
    }

    private void sumOfLineItems() {
        // Total up line items
        for (OrderLineItem lineItem : _order.getOrderLineItemList()) {
            subTotal = subTotal.add(lineItem.getPrice());
        }
    }
}
