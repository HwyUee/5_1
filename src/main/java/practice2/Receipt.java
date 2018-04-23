package practice2;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

    private BigDecimal tax;

    public Receipt() {
        tax = new BigDecimal(0.1).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {

        return calculateSubtotal(products, items).
                multiply(tax.add(new BigDecimal(1))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    private OrderItem findOrderItemByProduct(List<OrderItem> items, Product product) {
        OrderItem curItem = null;
        for (OrderItem item : items) {
            if (item.getCode() == product.getCode()) {
                curItem = item;
                break;
            }
        }
        return curItem;
    }

    private BigDecimal calculateSubtotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = new BigDecimal(0);
        for (Product product : products) {
            OrderItem item = findOrderItemByProduct(items, product);
            BigDecimal totalPrice = product.getPrice()
                    .multiply(new BigDecimal(item.getCount()));
            subTotal = subTotal.add(totalPrice.subtract(totalPrice.multiply(product.getDiscountRate())));
        }
        return subTotal;
    }
}
