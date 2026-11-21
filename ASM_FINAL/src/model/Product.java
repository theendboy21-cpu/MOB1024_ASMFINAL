package model;

import java.io.Serializable;

public class Product implements Identifiable<String>, Serializable {
    // Khuyến nghị khai báo serialVersionUID khi dùng Serializable để tránh lỗi lệch phiên bản lúc đọc file .dat
    private static final long serialVersionUID = 1L;

    private final String id;
    private String name;
    private String group;
    private Unit unit;
    private int minStock;
    private int stock;
    private double lastImportPrice;

    public Product(String id, String name, String group, Unit unit, int minStock, int stock, double lastImportPrice) {
        // Kiểm tra id (final nên kiểm tra ngay tại constructor)
        if (id == null || !id.trim().matches("^HH\\d{3}$")) {
            throw new IllegalArgumentException("Mã hàng hóa phải có định dạng HH001");
        }
        this.id = id.trim();

        setName(name);
        setGroup(group);
        setUnit(unit);
        setMinStock(minStock);
        setStock(stock);
        setLastImportPrice(lastImportPrice);
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên hàng không được rỗng");
        }
        this.name = name.trim();
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        if (group == null || group.trim().isEmpty()) {
            throw new IllegalArgumentException("Nhóm hàng không được rỗng");
        }
        this.group = group.trim();
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Chưa chọn đơn vị tính");
        }
        this.unit = unit;
    }

    public int getMinStock() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        if (minStock < 0) {
            throw new IllegalArgumentException("Tồn tối thiểu không được âm");
        }
        this.minStock = minStock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Tồn kho không được âm");
        }
        this.stock = stock;
    }

    public double getLastImportPrice() {
        return lastImportPrice;
    }

    public void setLastImportPrice(double lastImportPrice) {
        if (lastImportPrice < 0) {
            throw new IllegalArgumentException("Giá nhập không được âm");
        }
        this.lastImportPrice = lastImportPrice;
    }

    public double stockValue() {
        return this.stock * this.lastImportPrice;
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-25s | %-15s | %-5s | Tồn: %4d (Min: %3d) | Giá nhập: %,.0f",
                id, name, group, unit, stock, minStock, lastImportPrice);
    }
}