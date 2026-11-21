package model;

import java.io.Serializable;

public class VoucherLine implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String productId;
    private int quantity;
    private double unitPrice;

    public VoucherLine(String productId, int quantity, double unitPrice) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Thiếu mã hàng");
        }
        this.productId = productId.trim();

        setQuantity(quantity);
        setUnitPrice(unitPrice);
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
       
        if (quantity == 0) {
            throw new IllegalArgumentException("Số lượng phải khác 0");
        }
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Đơn giá không âm");
        }
        this.unitPrice = unitPrice;
    }

    // Thành tiền = số lượng x đơn giá
    public double amount() {
        return this.quantity * this.unitPrice;
    }

    @Override
    public String toString() {
        return String.format("%-6s | SL: %5d | Đơn giá: %10,.0f | Thành tiền: %12,.0f",
                productId, quantity, unitPrice, amount());
    }
}