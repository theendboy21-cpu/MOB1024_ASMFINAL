package model;

public enum Unit {
    PIECE("Cái"),
    BOX("Hộp"),
    REAM("Ram"),
    PACK("Gói");

    private final String label;

    Unit(String label) {
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