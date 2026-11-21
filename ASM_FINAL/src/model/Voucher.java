package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Voucher implements Identifiable<String>, Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private final VoucherType type;
    private final String supplierId;
    private final String customerName;
    private final String createdBy;
    private final LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private VoucherStatus status;
    private String note;
    private final List<VoucherLine> lines;

    public Voucher(String id, VoucherType type, String supplierId, String customerName,
                   String createdBy, LocalDateTime createdAt, LocalDateTime completedAt,
                   VoucherStatus status, String note) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Thiếu mã phiếu");
        }
        if (type == null) {
            throw new IllegalArgumentException("Thiếu loại phiếu");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("Thiếu thời gian tạo phiếu");
        }
        if (createdBy == null || createdBy.trim().isEmpty()) {
            throw new IllegalArgumentException("Thiếu người lập phiếu");
        }
        // BR-04: Phiếu nhập bắt buộc phải có nhà cung cấp
        if (type == VoucherType.IMPORT && (supplierId == null || supplierId.trim().isEmpty())) {
            throw new IllegalArgumentException("Phiếu nhập bắt buộc có nhà cung cấp");
        }

        this.id = id.trim();
        this.type = type;
        this.supplierId = (supplierId == null || supplierId.trim().isEmpty()) ? null : supplierId.trim();
        this.customerName = (customerName == null || customerName.trim().isEmpty()) ? null : customerName.trim();
        this.createdBy = createdBy.trim();
        this.createdAt = createdAt;
        this.lines = new ArrayList<>();

        setCompletedAt(completedAt);
        setStatus(status == null ? VoucherStatus.DRAFT : status);
        setNote(note);
    }

    @Override
    public String getId() {
        return id;
    }

    public VoucherType getType() {
        return type;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public VoucherStatus getStatus() {
        return status;
    }

    public void setStatus(VoucherStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Trạng thái phiếu không được rỗng");
        }
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = (note == null) ? "" : note.trim();
    }

    public List<VoucherLine> getLines() {
        return lines;
    }

    // Tìm dòng phiếu theo mã hàng (trả về Optional)
    public Optional<VoucherLine> line(String productId) {
        if (productId == null) {
            return Optional.empty();
        }
        return lines.stream()
                .filter(l -> l.getProductId().equalsIgnoreCase(productId.trim()))
                .findFirst();
    }

    public void addLine(String productId, int qty, double price) {
        if (type != VoucherType.ADJUST && qty <= 0) {
            throw new IllegalArgumentException("Số lượng nhập/xuất phải lớn hơn 0");
        }
        Optional<VoucherLine> existing = line(productId);
        if (existing.isPresent()) {
            VoucherLine l = existing.get();
            l.setQuantity(l.getQuantity() + qty);
            l.setUnitPrice(price);
        } else {
            lines.add(new VoucherLine(productId, qty, price));
        }
    }

    public double total() {
        return lines.stream()
                .mapToDouble(VoucherLine::amount)
                .sum();
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String partner = (type == VoucherType.IMPORT) ? ("NCC: " + supplierId)
                : (type == VoucherType.EXPORT) ? ("Khách: " + customerName)
                : "Kiểm kê kho";
        return String.format("%-8s | %-8s | %-16s | %-18s | %-10s | Tổng: %12,.0f",
                id, type, createdAt.format(fmt), partner, status, total());
    }
}