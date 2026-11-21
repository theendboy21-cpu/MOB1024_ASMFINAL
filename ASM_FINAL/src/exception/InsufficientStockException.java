package exception;

public class InsufficientStockException extends BusinessException {
    private static final long serialVersionUID = 1L;

    private final String productId;
    private final int stock;
    private final int needed;

    public InsufficientStockException(String productId, int stock, int needed) {
        super(String.format("Không đủ tồn kho cho mặt hàng [%s]: hiện có %d, cần %d",
                productId, stock, needed));
        this.productId = productId;
        this.stock = stock;
        this.needed = needed;
    }

    public String getProductId() {
        return productId;
    }

    public int getStock() {
        return stock;
    }

    public int getNeeded() {
        return needed;
    }
}