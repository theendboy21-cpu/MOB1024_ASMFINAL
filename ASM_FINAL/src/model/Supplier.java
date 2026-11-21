package model;

import java.io.Serializable;

public class Supplier implements Identifiable<String>, Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private String name;
    private String phone;
    private String address;

    public Supplier(String id, String name, String phone, String address) {
        if (id == null || !id.trim().matches("^NCC\\d{2}$")) {
            throw new IllegalArgumentException("Mã nhà cung cấp phải có định dạng NCC01");
        }
        this.id = id.trim();

        setName(name);
        setPhone(phone);
        setAddress(address);
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
            throw new IllegalArgumentException("Tên NCC không được rỗng");
        }
        this.name = name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || !phone.trim().matches("^0\\d{9,10}$")) {
            throw new IllegalArgumentException("SĐT phải 10-11 số, bắt đầu bằng 0");
        }
        this.phone = phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = (address == null) ? "" : address.trim();
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-25s | %-12s | %s", id, name, phone, address);
    }
}