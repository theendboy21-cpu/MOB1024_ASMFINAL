package model;

public enum VoucherType {
    IMPORT("Nhập", "PN", 1),
    EXPORT("Xuất", "PX", -1),
    ADJUST("Kiểm kê", "KK", 1);

    private final String label;
    private final String prefix;
    private final int sign;

    VoucherType(String label, String prefix, int sign) {
        this.label = label;
        this.prefix = prefix;
        this.sign = sign;
    }

    public String getLabel() {
        return label;
    }

    public String getPrefix() {
        return prefix;
    }

    public int sign() {
        return sign;
    }

    @Override
    public String toString() {
        return label;
    }
}