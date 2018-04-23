package practice3;

import java.math.BigDecimal;
import java.util.List;

public class PriceCaculator {
    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;
    private BigDecimal SubTotal;

    public PriceCaculator(Order order)
    {
        this.orderLineItemList = order.orderLineItemList;
        this.discounts = order.discounts;
        this.tax = order.tax;
        SubTotal = new BigDecimal(0);
    }

    public BigDecimal Calculate()
    {
        CalculateSubTotal();

        SubtractDiscounts();

        CalculateTax();

        return SubTotal;
    }

    private void CalculateSubTotal()
    {
        // Total up line items
        for (OrderLineItem lineItem : orderLineItemList) {
            SubTotal = SubTotal.add(lineItem.getPrice());
        }
    }

    private void SubtractDiscounts()
    {
        // Subtract Discounts
        for (BigDecimal discount : discounts) {
            SubTotal = SubTotal.subtract(discount);
        }
    }

    private void CalculateTax()
    {
        // Calculate Tax
        SubTotal = SubTotal.add(SubTotal.multiply(tax));
    }
}
