package model;

public enum VoucherStatus {
    DRAFT("Nháp"),
    COMPLETED("Hoàn tất"),
    CANCELLED("Đã hủy");

    private final String label;

    VoucherStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}